<template>
  <div>
    <h2>로그인 페이지</h2>
    <a id="custom-login-btn" @click="kakaoLogin()">
      <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
    </a>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';

const store = useUserStore();
const router = useRouter();
const kakaoLogin = function () {
  window.Kakao.Auth.login({
    scope: "profile_nickname, account_email",
    success: () => {
      store.getKakaoAccount();
    },
    fail: function(err) {
      console.log(err);
    }
  });

  router.push('/');
}
</script>

<!-- <script>
import { useRoute, useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';


export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const userStore = useUserStore();
    return {
      router, userStore, route
    };
  },

  methods: {
    kakaoLogin() {
      console.log(window.Kakao)
      window.Kakao.Auth.login({
        scope: "profile_nickname, account_email",
        success: this.getKakaoAccount,
      });

      this.router.push('/');
    }

    ,
    getKakaoAccount() {
      this.userStore.getKakaoAccount();
      // window.Kakao.API.request({
      //   url: "/v2/user/me",
      //   success: (res) => {
      //     const kakao_account = res.kakao_account;
      //     const nickname = kakao_account.profile.nickname;
      //     const email = kakao_account.email;
      //     console.log("kakao_account", kakao_account);
      //     console.log("nickname", nickname);
      //     console.log("email", email);


      //     const matchedUser = {
      //       nickname: nickname,
      //       email: email,
      //     }

      //     useUserStore.loginUser = matchedUser;
      //   },
      //   fail: (error) => {
      //     console.log(error);
      //   },
      // });
    },
  },
};
</script> -->

<style scoped></style>