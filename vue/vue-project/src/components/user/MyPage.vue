<template>
  <div id="user-container">
    <div id="myPage" class="sns-page">
      <h1>사용자 정보</h1>
      <p id="info">식단 관리 및 출석 설정은 카카오 채널에서 가능합니다</p>
      <div class="user-info">
        <div class="changable-info"><span class="info-name">이메일</span><span class="info-value">{{ userStore.loginUser.account }}</span><i class="bi bi-arrow-right enter"></i></div>
        <div class="changable-info"><span class="info-name">닉네임</span><span class="info-value">{{ userStore.loginUser.nickname }}</span><i class="bi bi-arrow-right enter"></i></div>
        <div><span class="info-name">카카오 연동</span><span>{{ userStore.loginUser.iskakao ? "사용 중" : "사용 안 함" }}</span></div>
        <div class="changable-info" v-if="!userStore.loginUser.iskakao"><span class="info-name">비밀번호</span><span>새 비밀번호 설정</span><i class="bi bi-arrow-right enter"></i></div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600&display=swap');

#user-container {
  display: flex;
  flex-flow: wrap column;
  justify-content: center;
  align-items: center;
}

.sns-page {
  color: #000;
  padding: 30px;
  border-radius: 8px;
  border: 1px solid #ddd;
  width: 70%;
}

h1 {
  font-family: 'IBM Plex Sans KR', sans-serif;
  font-weight: 500;
  font-size: 30px;
  margin: 20px 10px 10px 30px;
}

#info {
  font-family: 'IBM Plex Sans KR', sans-serif;
  font-weight: 300;
  font-size: 15px;
  margin: 0 30px 20px 30px;
  color: dimgray;
}

.info-name{
  font-weight: 300;
  color: grey;
  width: 120px;
}

.user-info div {
  display: flex;
  flex-flow: wrap row;
  font-weight: 300;
  font-size: 18px;
  padding: 20px 30px 19px;
  border-bottom: 1px solid #ddd;
  width: 100%;
  margin: 0;
}

.changable-info {
  cursor: pointer;
}

.enter {
  margin-left: auto;
}

.user-info div:hover {
  background-color: #f0f0f0;
}

.hover-bg:hover {
  background-color: #f0f0f0;
}
</style>
