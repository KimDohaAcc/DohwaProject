import { defineStore } from "pinia";
import { ref, onBeforeMount } from "vue";
import axios from "axios"

export const useUserStore = defineStore('user', () => {
  const loginUser = ref(null);
  let user = {};
  let mealList = ref(null);
  let followerList = ref(null);
  let followeeList = ref(null);

  const REST_API_URL = `http://localhost:8080`;


 onBeforeMount(() => {
    loginUser.value = JSON.parse(localStorage.getItem('loginUser'));
 })

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

        localStorage.setItem("loginUser", JSON.stringify(user));
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
    // axios 요청
    axios
      .post(API_URL, user)
      .then((res) => {
        localStorage.setItem('jwtToken', res.data['access-token']);
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 실패");
      });
  }


  const logoutUser = function () {
    localStorage.removeItem("jwtToken");
    localStorage.removeItem("loginUser");
    loginUser.value = null;
  }

  const getUserMeal = function () {
    const API_URL = `http://healthpanda.site/meal`;
    const user = loginUser.value;

    console.log(user);

    axios.post(REST_API_URL, user)
      .then((res) => {
        mealList.value = res.data.map(item => {
          return {
            ...item,
            time: new Date(item.time).toLocaleDateString() + " " + new Date(item.time).toLocaleTimeString(),
          };
        });
      })
      .catch((err) => {
        console.log(err);
      });
};

  const getUserFollow = function (type) {
    let API_URL = REST_API_URL;
    if(type == "follower"){
      API_URL += "/follower";
    } else {API_URL += "/followee"}

    const user = loginUser.value; 

    axios
      .post(API_URL, user)
      .then((res) => {
        if(type == "follower"){
          followerList.value = res.data;
        } else {
          followeeList.value = res.data;
        }
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return { loginUser, getKakaoAccount, logoutUser, mealList, getUserMeal, getUserFollow, followerList, followeeList }
})