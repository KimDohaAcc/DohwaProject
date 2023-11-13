<template>
    <header>
        <div>
            <RouterLink to="/">HealthPanda</RouterLink>
        </div>
        <div>
            <a href="#" v-if="userStore.loginUser.email" @click="kakaoLogout">로그아웃</a>
            <RouterLink to="/login" v-else>로그인</RouterLink>
            <RouterLink to="/regist">회원가입</RouterLink>
            <RouterLink to="/board">이용 후기</RouterLink>
        </div>
    </header>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();

const kakaoLogout = function(){
    userStore.logoutUser();
}


</script>
<style scoped>

</style>