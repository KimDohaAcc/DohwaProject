import { defineStore } from "pinia";
import { ref } from "vue";
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

export const useUserStore = defineStore('user', () => {
  const loginUser = ref(null);
  let user = {};
  let mealList = ref(null);
  let followerList = ref(null);
  let followeeList = ref(null);

  const REST_API_URL = `http://localhost:8080`;

  loginUser.value = JSON.parse(sessionStorage.getItem('loginUser'));

  const getKakaoAccount = function () {
    window.Kakao.API.request({
      url: "/v2/user/me",
      success: (res) => {
        const id = res.id;
        const kakao_account = res.kakao_account;
        const nickname = kakao_account.profile.nickname;
        const email = kakao_account.email;
        console.log(id);
        console.log("kakao_account", kakao_account);
        console.log("nickname", nickname);
        console.log("email", email);

        user = {
          id: id,
          nickname: nickname,
          account: email,
          iskakao: true,
        }

        sessionStorage.setItem("loginUser", JSON.stringify(user));
        loginUser.value = user;
        login();
      },
      fail: (error) => {
        console.log(error);
      },
    });
  }

  const login = function () {
    const API_URL = REST_API_URL + `/login`
    console.log(user)

    axiosInstance
      .post(API_URL, user)
      .then((res) => {
        sessionStorage.setItem('jwtToken', res.data['access-token']);
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 실패");
      });
  }


  const logoutUser = function () {
    sessionStorage.removeItem("jwtToken");
    sessionStorage.removeItem("loginUser");
    loginUser.value = null;
  }

  const getUserFollow = function (type) {
    let API_URL = REST_API_URL;
    if (type == "follower") {
      API_URL += "/follower";
    } else { API_URL += "/followee" }

    const user = loginUser.value;

    axiosInstanceWithToken
      .post(API_URL, user)
      .then((res) => {
        if (type == "follower") {
          followerList.value = res.data;
        } else {
          followeeList.value = res.data;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const checkLoginAndRedirect = function (next, loginPath = '/login') {
    const isLoggedIn = computed(() => !!loginUser);
    if (!isLoggedIn.value) {
      next(loginPath);
    } else {
      next();
    }
  }



  return { loginUser, checkLoginAndRedirect, getKakaoAccount, logoutUser, mealList, getUserFollow, followerList, followeeList }
},
  {
    persist: {
      storage: sessionStorage,
    }
  },
)