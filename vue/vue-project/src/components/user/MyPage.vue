<template>
  <div id="myPage">
    <h1>{{ userStore.loginUser.nickname }}님의 마이페이지</h1>
    <p>이메일: {{ userStore.loginUser.account }}</p>
    <p>알림 설정 여부 : (추후 생성)</p>
    <p>알림 시간 : </p>
    <RouterLink :to="{ name: 'myMeal' }">내 식단 기록</RouterLink>
      <h3>예약 내역:</h3>
      <ul>
        <li v-for="(reservation, index) in reservations" :key="index">
          <p>지점: {{ reservation.store }}</p>
          <p>예약 날짜: {{ formatDate(reservation.time) }}</p>
          <p>전화번호: {{ reservation.phone}}</p>
          <button @click="deleteReservation(index)">삭제</button>
        </li>
      </ul>
    
  </div>
 
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, ref } from 'vue';
import axios from 'axios';

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
axios.get(`http://localhost:8080/reserve/get/${user.id}`)
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
  axios.delete(`http://localhost:8080/reserve/delete/${reservationToDelete.num}`)
    .then(response =>{
      reservations.value.splice(index, 1);
      console.log('예약이 삭제되었습니다.');
    })
    .catch(error =>{
      console.error('예약 삭제 중 에러 발생:', error);
    })
}



</script>

<style scoped>
/* 필요한 스타일 작성 */
</style>
