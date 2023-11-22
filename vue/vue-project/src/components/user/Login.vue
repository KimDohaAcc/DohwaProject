<template>
  <div class="login-container">
    <h2>Login</h2>
    <form @submit.prevent="handleLogin">
      <div class="input-group">
        <label for="username">Username</label>
        <input type="text" id="username" v-model="username" placeholder="Enter your username" />
      </div>
      <div class="input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="password" placeholder="Enter your password" />
      </div>
      <button type="submit">Login</button>
    </form>
    <div class="social-login">
      <p>Or log in with:</p>
      <button @click="kakaoLogin">
        <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="222" alt="Kakao login" />
      </button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

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
};

const handleLogin = () => {
  store.originalLogin(username.value, password.value);
  router.push('/');
};
</script>

<style scoped>
.login-container {
  max-width: 400px;
  margin: auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 5px;
  background-color: #f9f9f9;
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
}

input[type="text"],
input[type="password"] {
  width: 100%;
  margin-top: 5px;
}

button {
  background-color: #85C18B;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s;
}

button:hover {
  background-color: #5a856f;
}
</style>