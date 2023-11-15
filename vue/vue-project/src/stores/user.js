import { defineStore } from "pinia";
import { ref, computed, onMounted } from "vue";
import axios from "axios"
// import jwt_decode from 'jwt-decode';

export const useUserStore = defineStore('user', () => {
  const loginUser = ref(null);
  let user = {};
  let mealList = ref(null);

  onMounted(() => {
    tokenCheck();
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
        localStorage.setItem('jwtToken', res.data['access-token']);
        
      })
      .catch((err) => {
        console.log(err);
        alert("로그인 실패");
      });
  }

  const tokenCheck = function isTokenValid() {
    const token = localStorage.getItem('jwtToken');

    if (token) {
      try {
          const decodedToken = jwt_decode(token);

          // 대부분의 토큰 시스템에서는 만료 시간을 초 단위로 표시하므로, 현재 시간을 초 단위로 변환하기 위해 / 1000을 사용
          const currentTimestamp = Date.now() / 1000;
          if (decodedToken.exp && decodedToken.exp < currentTimestamp) {
              // 토큰이 만료되었음
              return false;
          }

          // 다른 유효성 검사 로직 추가
          // ...
          loginUser.value = localStorage.getItem('loginUser')
          return true;
      } catch (error) {
          // 토큰 디코딩 오류 발생
          console.error('Error decoding token:', error);
          return false;
      }
  } else {
      loginUser.value = null;
      localStorage.removeItem('loginUser');
      return false;
    }
}

  const logoutUser = function () {
    localStorage.removeItem("jwtToken");
    loginUser.value = null;
  }

  const getUserMeal = function () {
    const API_URL = `http://localhost:8080/meal`;
    const user = JSON.parse(loginUser.value); // user 변수를 const로 선언
    
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
  
  return { loginUser, getKakaoAccount, logoutUser, mealList, getUserMeal, tokenCheck }
})