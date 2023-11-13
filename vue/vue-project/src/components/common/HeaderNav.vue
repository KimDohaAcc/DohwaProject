<template>
    <header>
        <nav class="navbar navbar-expand-lg bg-body-tertiary bg-primary" style="background-color: #e3f2fd;" data-bs-theme="light">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">
                    <img src="logo.jpg" alt="Logo" width="30" height="24" class="d-inline-block align-text-top">
                    HealthPanda
                </a>
            </div>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#" v-if="userStore.loginUser.email"
                            @click="kakaoLogout">
                            로그아웃
                        </a>
                        <RouterLink to="/login" v-else>로그인</RouterLink>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">
                            <RouterLink to="/regist">회원가입</RouterLink>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();

const kakaoLogout = function () {
    userStore.logoutUser();
}


</script>
<style scoped></style>