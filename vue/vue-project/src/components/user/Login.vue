<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div class="input-group">
        <label for="username">E-mail</label>
        <input type="text" id="username" v-model="username" placeholder="Enter your username" />
      </div>
      <div class="input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" placeholder="Enter your password" />
      </div>
      <button type="submit" class="login-button">Login</button>
    </form>
    <div class="social-login">
      <h6>or</h6>
      <p class="social-login-text">소셜계정으로 로그인하기</p>
      <button @click="kakaoLogin" class="kakao-button">
        <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="Kakao login" />
      </button>
    </div>
    <div class="register-link">
    <p>Don't have an account?</p>
    <button @click="goToSignup" class="signup-button">Sign Up</button>
  </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import Regist from './Regist.vue';

const store = useUserStore();
const router = useRouter();

const username = ref('');
const password = ref('');

const kakaoLogin = () => {
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
  window.scroll({
			top: 0, 
			left: 0, 
		});
};

const handleLogin = () => {
  store.originalLogin(username.value, password.value);
  router.push('/');
};

const goToSignup = () => {
  router.push('/regist'); 
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 40px 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
  margin-top: 250px;
  margin-bottom: 250px;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

form {
  display: flex;
  flex-direction: column;
}

.input-group {
  margin-bottom: 15px;
}

label {
  margin-bottom: 5px;
  font-weight: bold;
}

input[type="text"],
input[type="password"],
button {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  width: 100%;
  margin-top: 5px;
}

button {
  color: white;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

button:hover {
  background-color: #1a2a5f;
}

.social-login {
  text-align: center;
  margin-bottom: 10px;
}

.register-link {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.register-link p {
  margin-bottom: 5px;
}

.register-link button {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 3px;
  background-color: #1a2a5f;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
  width: 100%;
}

.register-link button:hover {
  background-color: #1a2a5f;
}

.signup-button {
  margin-bottom: 25px;
  margin-top: 0;
}

.login-button{
  margin-bottom: 40px;
}
.h6{
  text-align: center;
}
</style>