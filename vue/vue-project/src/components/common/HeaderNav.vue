<template>
    <header>
        <div>
            <b-navbar toggleable="lg" type="light" variant="faded">
                <b-navbar-brand href="#">
                    <img src="logo.jpg" class="d-inline-block align-top" alt="logo" width="50">
                    <RouterLink to="/">
                        healthPanda
                    </RouterLink>
                </b-navbar-brand>
                <b-collapse id="nav-collapse" is-nav>
                    <b-navbar-nav>
                        <b-nav-item href="#">
                            <RouterLink to="/board">이용 후기</RouterLink>
                        </b-nav-item>
                    </b-navbar-nav>
                    <b-navbar-nav v-if="!isLogin">
                        <b-nav-item href="#">
                            <RouterLink to="/login">
                                로그인
                            </RouterLink>
                        </b-nav-item>
                        <b-nav-item href="#">
                            <RouterLink to="/regist">회원가입</RouterLink>
                        </b-nav-item>
                    </b-navbar-nav>
                    <b-navbar-nav right v-else>
                        <b-nav-item-dropdown>
                            <template #button-content>
                                <em>User</em>
                            </template>
                            <b-nav-item href="#">
                                <RouterLink to="/myPage">
                                    마이페이지
                                </RouterLink>
                            </b-nav-item>
                            <b-nav-item href="#">
                                <b-dropdown-item href="#" @click="kakaoLogout">
                                    로그아웃
                                </b-dropdown-item>
                            </b-nav-item>
                        </b-nav-item-dropdown>
                    </b-navbar-nav>
                </b-collapse>
            </b-navbar>
        </div>
    </header>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { computed } from 'vue'

const userStore = useUserStore();
const isLogin = computed(() => !!userStore.loginUser);

const kakaoLogout = function () {
    userStore.logoutUser();
}

</script>
<style scoped>
#nav-collapse {
    list-style: none;
}
</style>