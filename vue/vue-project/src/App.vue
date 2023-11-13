<template>
    <div>
      <HeaderNav />
      <RouterView @login-user="loginUser" />
    </div>
</template>

<script setup>
import HeaderNav from '@/components/common/HeaderNav.vue'
import { RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from "axios"
import { useRouter } from "vue-router"
import { useUserStore } from '@/stores/user.js'

const router = useRouter();

onMounted(() => {

  const savedUser = useUserStore.loginUser;
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }

});

const loginUser = (loginUser) => {
  // user 정보 요청 api 주소
  const API_URL = `http://localhost:8080/user`;

  // axios 요청
  axios
    .get(API_URL)
    .then((res) => {
      let matchedUser = res.data.find(
        (u) => u.id === loginUser.id && u.password === loginUser.password
      );

      if (matchedUser) {
        localStorage.setItem("loginUser", JSON.stringify(matchedUser));
        alert("로그인 성공");
        router.push("/");
      } else {
        alert("로그인 실패");
      }
    })
    .catch((err) => {
      console.log(err);
      alert("로그인 실패: 서버 에러");
    });
};

</script>


<style scoped>
* {
  margin : 5%;
}
</style>
