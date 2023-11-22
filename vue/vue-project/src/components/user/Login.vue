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
      <button type="submit" @click="handleLogin">로그인</button>
    </form>
    <a id="custom-login-btn" @click="kakaoLogin()">
      <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="카카오 로그인 버튼" />
    </a>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'
import { useRouter } from 'vue-router';

const store = useUserStore();
const router = useRouter();

const kakaoLogin = function () {
  window.Kakao.Auth.login({
    scope: "profile_nickname, profile_image, account_email",
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


const handleLogin = () => {
  store.originalLogin(username.value, password.value);
};
</script>

<style scoped></style>
