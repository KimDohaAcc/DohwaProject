import { defineStore } from "pinia";
import { ref, computed, onMounted } from "vue";
import axios from "axios"

export const useUserStore = defineStore('user', () => {
  const loginUser = ref(null);
  let user = {};
  let mealList = ref(null);

  onMounted(() => {
    loginUser.value = JSON.parse(localStorage.getItem("loginUser"));
  });

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
    const API_URL = `http://localhost:8080/login`
    console.log(user)
    // axios 요청
    axios.
      post(API_URL, user)
      .then((res) => {
        alert(".로그인 성공");
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 실패");
      });
  }

  const logoutUser = function () {
    localStorage.removeItem("loginUser");
    loginUser.value = null;
  }

  const getUserMeal = function () {
    const API_URL = `http://localhost:8080/meal`;
    const user = JSON.parse(localStorage.getItem("loginUser")); // user 변수를 const로 선언
    
    console.log(user);
    
    axios
    .get(API_URL, { params: user }) // 올바른 방식으로 파라미터 전달
    .then((res) => {
      mealList.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    });
  };
  
  return { loginUser, getKakaoAccount, logoutUser, mealList, getUserMeal }
})