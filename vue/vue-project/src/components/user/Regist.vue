<template>
    <div class="joinMain">
      <div v-show="value" class="success-message">회원가입을 축하드립니다.</div>
      <div class="header">healthpanda</div>
  
      <div class="joinText">회원가입</div>
      <div>
        <label for="name">닉네임</label>
        <input
          type="text"
          placeholder="닉네임"
          v-model="nickname"
          @blur="checkNickname"
          :class="{ 'input-error': errorNickname }"
          maxlength="20"
        />
        <p v-show="errorNickname" class="input-error">닉네임이 올바르지 않습니다.</p>
      </div>
  
      <div>
        <label for="password">비밀번호</label>
        <input
          type="password"
          placeholder="비밀번호"
          v-model="password"
          @blur="checkPassword"
          :class="{ 'input-error': errorPassword }"
        />
        <p v-show="errorPassword" class="input-error">비밀번호는 영문+숫자를 포함한 4자 이상 12자 이내이여야 합니다.</p>
      </div>
  
      <div>
        <label for="password">이메일</label>
        <input
          type="email"
          placeholder="이메일"
          v-model="account"
          @blur="checkAccount"
          :class="{ 'input-error': errorAccount }"
        />
        <p v-show="errorAccount" class="input-error">이메일 형식이 올바르지 않습니다. 다시 한 번 확인 해주세요.</p>
      </div>
  
      <div>
        <button class="checkButton" @click="accountCheck">중복체크</button>
      </div>
      <button
        type="button"
        :disabled="errorNickname || errorPassword || errorAccount || !nickname || !password || !account"
        @click="join"
      >가입하기</button>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  import { useUserStore } from '@/stores/user.js'; // 실제 사용하는 경로로 변경해주세요
  
  export default {
    setup() {
      const userStore = useUserStore();
      const nickname = ref('');
      const account = ref('');
      const password = ref('');
      const errorNickname = ref(false);
      const errorPassword = ref(false);
      const errorAccount = ref(false);
  
      const join = () => {
        checkNickname();
        checkPassword();
        checkAccount();
  
        if (!errorNickname.value && !errorPassword.value && !errorAccount.value && nickname.value !== '' && password.value !== '' && account.value !== '') {
          const newUser = {
            nickname: nickname.value,
            account: account.value,
            password: password.value,
            iskakao: false,
          };
  
          userStore.submitNewUser(newUser);
        }
      };
  
      const checkNickname = () => {
        const validateNickname = /^[A-Za-z가-힣]{1,20}$/;
        errorNickname.value = !validateNickname.test(nickname.value) || !nickname.value;
      };
  
      const checkPassword = () => {
        const validatePassword = /^[A-Za-z0-9]{4,12}$/;
        errorPassword.value = !validatePassword.test(password.value) || !password.value;
      };
  
      const checkAccount = () => {
        const validateAccount = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        errorAccount.value = !validateAccount.test(account.value) || !account.value;
      };
  
      return {
        nickname,
        account,
        password,
        errorNickname,
        errorPassword,
        errorAccount,
        join,
        checkNickname,
        checkPassword,
        checkAccount,
      };
    },
  };
  </script>
  
  <style scoped>
  .input-error {
    color: red;
  }
  </style>