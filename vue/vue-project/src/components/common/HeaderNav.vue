<template>
  <header>
    <div class="header-wrapper">
      <div class="hwaseok-header">
        <b-navbar-nav v-if="!isLogin" class="navbar-login">
          <b-nav-item><RouterLink to="/login" tag="span" class="nav-link">로그인</RouterLink></b-nav-item>
          <b-nav-item><RouterLink to="/regist" tag="span" class="nav-link">회원가입</RouterLink></b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav right v-else>
          <b-nav-item-dropdown text="User" class="navbar-user">
            <b-dropdown-item><RouterLink to="/myPage" tag="span" class="nav-link">마이페이지</RouterLink></b-dropdown-item>
            <b-dropdown-item><RouterLink :to="`/like/${userStore.loginUser.id}`" tag="span" class="nav-link">내 좋아요</RouterLink></b-dropdown-item>
            <b-dropdown-item><RouterLink to="/myFollow" tag="span" class="nav-link">내 팔로우</RouterLink></b-dropdown-item>
            <b-dropdown-item><RouterLink :to="`/board/write/${userStore.loginUser.id}`" tag="span" class="nav-link">내 작성글</RouterLink></b-dropdown-item>
            <b-dropdown-item @click="logout" class="nav-link logout">로그아웃</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </div>
      <RouterLink to="/" tag="span" class="nav-link"><img src="@/assets/web-logo.png" class="d-inline-block align-top" alt="logo" width="50"></RouterLink>
    </div>
    <div class="nav-hwaseok">
      <b-navbar toggleable="lg" type="light" variant="faded" class="navbar-custom">
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto">
            <b-nav-item><RouterLink to="/store" tag="span" class="nav-link">지점 소개</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/board" tag="span" class="nav-link">이용 후기</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/video" tag="span" class="nav-link">운동 추천 영상</RouterLink></b-nav-item>
            <b-nav-item><RouterLink to="/reserve" tag="span" class="nav-link">전화상담예약</RouterLink></b-nav-item>
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

const logout = function () {
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
  display: flex;
  align-items: center;
  justify-content: center;
  width: 50vw;
}
.nav-link {
  text-decoration: none;
}
.nav-hwaseok{
  display: flex;
  align-items: center;
  justify-content: space-around;
  margin-bottom: 50px;
}
#logo {
  display: flex;
  flex-flow: wrap row;
  width: 10vw;
}
.navbar-login{
  display: flex;
  flex-direction: row;
  width: 150px;
  justify-content: space-between;
  margin-right: 20px;
}
.navbar-user{
  margin-right: 20px;
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
  margin-bottom: 0vh;
  margin-top: 0vh;
}
.header-wrapper {
  padding: 30px 0;
  text-align: center; /* 텍스트 가운데 정렬 */
  margin-bottom: 0px; /* 간판 아래 여백 추가 */
  margin-top: 5px; /* 위 여백을 5px로 설정 */
}
.hwaseok-header{
  display: flex;
  justify-content: flex-end;
}
.brand-sign {
  color: white; /* 헬스판다 텍스트 색상 */
  font-size: 130px; /* 헬스판다 텍스트 크기 */
  font-weight: bold; /* 헬스판다 텍스트 굵기 */
  margin-top: 0; /* 위 여백 제거 */
  word-spacing: 40px; /* 각 단어 사이 간격 설정 */
}

img {
  width: 30vw;
}

.ml-auto{
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 45vw;
}

#nav-collapse {
  display: flex;
  align-items: center;
  justify-content: center;
}

a, .logout {
  font-weight: 400;
  font-size: 15pt;
}

</style>