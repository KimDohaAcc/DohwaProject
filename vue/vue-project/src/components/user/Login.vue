<template>
  <div>
    <h2>로그인 페이지</h2>
    <form @submit.prevent="login">
      <div>
        <label for="username">아이디</label>
        <input type="text" id="username" v-model="username" />
      </div>
      <div>
        <label for="password">비밀번호</label>
        <input type="password" id="password" v-model="password" />
      </div>
      <button type="submit">로그인</button>
    </form>
    <a id="custom-login-btn" @click="kakaoLogin()">
      <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
    </a>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';

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

const username = ref('');
const password = ref('');

const login = () => {
  // 여기에 로그인 처리 로직을 추가하세요 (예: 서버에 아이디와 비밀번호 전송, 인증 등)
  console.log('아이디:', username.value);
  console.log('비밀번호:', password.value);

  // 로그인 성공 후의 작업을 진행하세요 (예: 성공 시 페이지 이동)
  // router.push('/dashboard'); // 대시보드 페이지로 이동하는 예시
};
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