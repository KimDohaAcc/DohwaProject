<template>
  <header>
    <div>
      <b-navbar toggleable="lg" type="light" variant="faded" class="navbar-custom">
        <b-navbar-brand id="logo">
          <img src="@/assets/logo.jpg" class="d-inline-block align-top" alt="logo" width="50">
          <RouterLink to="/" tag="span" class="nav-link">healthPanda</RouterLink>
        </b-navbar-brand>

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item><RouterLink to="/store" tag="span" class="nav-link">지점 소개</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/board" tag="span" class="nav-link">이용 후기</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/video" tag="span" class="nav-link">운동 추천 영상</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/reserve" tag="span" class="nav-link">전화상담예약</RouterLink></b-nav-item>

            <b-navbar-nav v-if="!isLogin">
              <b-nav-item><RouterLink to="/login" tag="span" class="nav-link">로그인</RouterLink></b-nav-item>
              <b-nav-item><RouterLink to="/regist" tag="span" class="nav-link">회원가입</RouterLink></b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav right v-else>
              <b-nav-item-dropdown text="User">
                <b-dropdown-item><RouterLink to="/myPage" tag="span" class="nav-link">마이페이지</RouterLink></b-dropdown-item>
                <b-dropdown-item @click="kakaoLogout" class="nav-link">로그아웃</b-dropdown-item>
              </b-nav-item-dropdown>
            </b-navbar-nav>
          </b-navbar-nav>
        </b-collapse>
      </b-navbar>
    </div>
  </header>
  <main></main>
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
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600&display=swap');

* {
  font-family: 'IBM Plex Sans KR', sans-serif;
  font-weight: 400;
}

.navbar-custom {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}
.nav-link {
  text-decoration: none;
}

#logo {
  display: flex;
  flex-flow: wrap row;
  width: 10vw;
}

@media (max-width: 768px) {
  .navbar-custom {
    box-shadow: none;
  }

  .navbar-nav {
    width: 100%;
    justify-content: space-around;
  }
}

header {
  margin-bottom: 10vh;
}

</style>