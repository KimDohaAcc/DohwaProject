import { defineStore } from "pinia";
import { ref } from "vue";

export const useUserStore = defineStore('user', () => {
    const loginUser = ref({});
    const userEmail = ref("");

    const getKakaoAccount = function(){

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
    
              loginUser.value = matchedUser;
              userEmail.value=email
            },
            fail: (error) => {
              console.log(error);
            },
          });
    }

    const logoutUser = function(){
        loginUser.value = {};

         const API_URL = `http://localhost:8080/kakao/logout`;

        // axios 요청
        axios
        .get(API_URL)
        .then((res) => {
            alert("로그아웃 성공");
        })
        .catch((err) => {
            console.log(err);
            console.log(useUserStore.loginUser);
            alert("로그아웃 실패: 서버 에러");
        });
    }

    return { loginUser, getKakaoAccount, userEmail, logoutUser}
})