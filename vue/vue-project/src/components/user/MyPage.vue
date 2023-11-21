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
      <h1>예약 내역</h1>
      <div class="user-info">
        <div v-for="(reservation, index) in reservations" :key="index">
          <div><span class="info-name">지점</span><span class="info-value">{{ reservation.store }}</span></div>
          <div><span class="info-name">예약날짜</span><span class="info-value">{{ formatDate(reservation.time) }}</span></div>
          <div><span class="info-name">전화 번호</span><span>{{ reservation.phone }}</span></div>
          <button @click="deleteReservation(index)">삭제</button>
          
        </div>
      </div>
      <button @click="deleteAccount">계정 탈퇴</button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, ref } from 'vue';
import axios from 'axios';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'
const userStore = useUserStore();

const reservations = ref([]);
console.log(reservations);
const formatDate = (dateString) => {
  const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
  const date = new Date(dateString);
  return date.toLocaleDateString('ko-KR', options);
};

onMounted(() => {
// 사용자의 예약 정보 가져오기
  let user = sessionStorage.getItem("loginUser"); // sessionStorage는 키, 밸류가 모두 문자열
  user = JSON.parse(user);

  const url = `http://localhost:8080/reserve/get/${user.id}`;
  console.log(url);
  console.log(user);
  axiosInstanceWithToken.get(`http://localhost:8080/auth/reserve/get/${user.id}`)
      .then(response => {
        reservations.value = response.data; // 백엔드에서 반환된 예약 목록 저장
        console.log("정보를 가져왔습니다");
        console.log(reservations.value);
      })
      .catch(error => {
        console.error('예약 정보를 가져오는 중 에러 발생:', error);
        console.log(reservations);
      });
});

const deleteReservation = (index) => {
  const reservationToDelete = reservations.value[index];
  console.log(reservationToDelete.num); // 예약의 ID 확인
  axiosInstanceWithToken.delete(`http://localhost:8080/auth/reserve/delete/${reservationToDelete.num}`)
      .then(response =>{
        reservations.value.splice(index, 1);
        console.log('예약이 삭제되었습니다.');
      })
      .catch(error =>{
        console.error('예약 삭제 중 에러 발생:', error);
      })
}
const deleteAccount = () => {
  if (confirm('정말 계정을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
    const userId = userStore.loginUser.id;
    const token = sessionStorage.getItem('jwtToken');
    if (!token) {
      alert('토큰이 없습니다.'); // 토큰이 없으면 처리 중단
      return;
    }
    if (token) {
      alert('토큰이 있습니다.');
      console.log(token) // 토큰이 없으면 처리 중단
      return;
    }
    axiosInstanceWithToken.delete(`http://localhost:8080/auth/unregister/${userId}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    })
      .then(() => {
        alert('계정이 삭제되었습니다.');
      })
      .catch(error => {
        console.error('계정 삭제 중 에러 발생:', error);
        alert('계정 삭제 중 오류가 발생했습니다.');
      });
  }
}
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
