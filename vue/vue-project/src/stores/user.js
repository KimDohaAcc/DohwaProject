import { defineStore } from "pinia";
import { ref, computed } from "vue";
import axios from "axios"

export const useUserStore = defineStore('user', () => {
  const userEmail = ref("");
  const loginUser = ref(localStorage.getItem("loginUser"));

  const getKakaoAccount = function () {
    window.Kakao.API.request({
      url: "/v2/user/me",
      success: (res) => {
        const kakao_account = res.kakao_account;
        const nickname = kakao_account.profile.nickname;
        const email = kakao_account.email;
        console.log("kakao_account", kakao_account);
        console.log("nickname", nickname);
        console.log("email", email);


        const matchedUser = {
          nickname: nickname,
          email: email,
        }

        localStorage.setItem("loginUser", matchedUser);
        loginUser.value = matchedUser;
        userEmail.value = email
      },
      fail: (error) => {
        console.log(error);
      },
    });
  }

  const logoutUser = function () {
    localStorage.removeItem("loginUser");
    loginUser.value = null;

    const API_URL = `http://localhost:8080/kakao/logout`;

    // axios 요청
    axios
      .get(API_URL)
      .then((res) => {
        alert("로그아웃 성공");
      })
      .catch((err) => {
        console.log(err);
        alert("로그아웃 실패: 서버 에러\n긴 한데 나중에 서버 통일하면 ㄱㅊ");
      });
  }

  const getUserMeal = function () {
    console.log("들어옴")
    const API_URL = `http://localhost:8080/meal`;
    console.log(API_URL)

    axios
    axios.get(API_URL, {
      params: userEmail
    })
      .then((res) => {
        console.log(res);
        return res.data;
      })
      .catch((err) => {
        console.log(err);
        return false;
      });
  }

  return { loginUser, getKakaoAccount, userEmail, logoutUser, getUserMeal }
})