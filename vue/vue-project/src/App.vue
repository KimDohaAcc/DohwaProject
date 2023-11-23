
<template>
   <div id="outer-container">
  <div id="front-container">
    <SideNav class="SideNav" />
    <div class="content-wrapper">
      <HeaderNav class="HeaderNav" />
      <main>
        <RouterView class="RouterView" @login-user="loginUser" />
      </main>
      <FooterView />
    </div>
  </div>
</div>
</template>

<script setup>
import HeaderNav from '@/components/common/HeaderNav.vue'
import SideNav from '@/components/common/SideNav.vue'
import { RouterView } from 'vue-router'
import { ref, onMounted } from 'vue'
import axios from "axios"
import { useRouter } from "vue-router"
import { useUserStore } from '@/stores/user.js'
import FooterView from '@/views/FooterView.vue';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const router = useRouter();
const lApi = "http://localhost:8080";
const dApi = "https://healthpanda.site";

onMounted(() => {

  const savedUser = useUserStore.loginUser;
  if (savedUser) {
    user.value = JSON.parse(savedUser);
  }

});

const loginUser = (loginUser) => {
  // user 정보 요청 api 주소


  // axios 요청
  axiosInstance
    .get(`${lApi}/user`)
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


<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600&display=swap');

* {
  font-family: 'IBM Plex Sans KR', sans-serif;
}

#front-container {
  background-color: white; /* 내용 부분 배경색 */
  display: flex;
  flex-flow: column;
  margin: 0 5%;
  width: 80vw;
  
  


}
#outer-container {
  display: flex;
  justify-content: center;
  background-color: #1A2A5F; /* 전체 배경색 */
  padding: 0 5%;
  
}



main>div {
  min-height: 80vh;
}
</style>