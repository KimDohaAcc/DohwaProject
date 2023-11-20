<template>
	<div class="container">
		<form @submit.prevent="submitForm">
			<div>
				<label for="name">닉네임</label>
				<input type="text" id="name" v-model="name" />
			</div>
			<div>
				<label for="email">email</label>
				<input type="text" id="email" v-model="email" />
			</div>
			<div>
				<label for="password">비밀번호</label>
				<input type="password" id="password" v-model="password" />
			</div>
			<div>
				<label for="passwordConfirm">비밀번호 확인</label>
				<input type="password" id="passwordConfirm" v-model="passwordConfirm" />
			</div>
			<button type="submit">회원가입</button>
		</form>
	</div>
</template>

<script setup>
import { ref } from 'vue';
import { useUserStore } from '@/stores/user.js'; // 실제 사용하는 경로로 변경해주세요

const userStore = useUserStore();
const name = ref('');
const email = ref('');
const password = ref('');
const passwordConfirm = ref('');
const iskakao = ref(false); // 임시로 false로 설정

const submitForm = () => {
  const newUser = {
    nickname: name.value,
    account: email.value,
    password: password.value,
    iskakao: iskakao.value,
  };
  
  userStore.submitNewUser(newUser); // useUserStore에 새로운 사용자 정보를 전달
};
</script>


<style scoped></style>
