<template>
  <div id="user-container" v-if="userStore.loginUser">
    <div id="myPage" class="sns-page">
      <h1>사용자 정보</h1>
      <p id="info">식단 관리 및 출석 설정은 카카오 채널에서 가능합니다</p>
      <div class="user-info" v-if="userStore.loginUser.iskakao">
        <div class="changable-info"><span class="info-name">이메일</span><span class="info-value">{{
          userStore.loginUser.account }}</span><span>(카카오 계정)</span></div>
        <div class="changable-info"><span class="info-name">닉네임</span><span class="info-value">{{
          userStore.loginUser.nickname }}</span><span>(카카오 계정)</span></div>
        <div><span class="info-name">카카오 연동</span><span>사용 중</span></div>
      </div>
      <div class="user-info" v-if="!userStore.loginUser.iskakao">
        <div class="changable-info" @click="changeEmail()"><span class="info-name">이메일</span><span class="info-value">{{
          userStore.loginUser.account }}</span><i class="bi bi-arrow-right enter"></i></div>
        <div v-if="editEmail">
          <span class="info-name">수정 이메일</span>
          <input type="text" id="account" v-model="updatedUser.account">
          <button @click="checkValidEmail()">저장</button>
        </div>
        <div class="changable-info" @click="changeNickname()"><span class="info-name">닉네임</span><span
            class="info-value">{{
              userStore.loginUser.nickname }}</span><i class="bi bi-arrow-right enter"></i></div>
        <div v-if="editNickname">
          <span class="info-name">수정 닉네임</span>
          <input type="text" id="nickname" v-model="updatedUser.nickname">
          <button @click="checkValidNickname()">저장</button>
        </div>
        <div><span class="info-name">카카오 연동</span><span>사용 안 함</span></div>
        <div class="changable-info" @click="changePassword()"><span class="info-name">비밀번호</span><span>새 비밀번호 설정</span><i
            class="bi bi-arrow-right enter"></i></div>
        <div v-if="editPassword">
          <span class="info-name">비밀번호 수정</span>
          <input type="password" id="password" v-model="updatedUser.password">
          <span class="info-name">비밀번호 확인</span>
          <input type="password" v-model="checkPassword">
          <button @click="checkPasswordEqual()">저장</button>
        </div>
      </div>
      <div class="user-info" v-if="reservations">
        <h1>예약 내역</h1>
        <div v-if="reservations.length == 0">상담 예약 내역이 없습니다</div>
        <div v-for="(reservation, index) in reservations" :key="index" v-else>
          <div><span class="info-name">지점</span><span class="info-value">{{ reservation.store }}</span></div>
          <div><span class="info-name">예약날짜</span><span class="info-value">{{ formatDate(reservation.time) }}</span></div>
          <div><span class="info-name">전화 번호</span><span>{{ reservation.phone }}</span></div>
          <button @click="deleteReservation(index)">삭제</button>
        </div>
      </div>
      <div class="box1">
        <button @click="deleteAccount" class="deleteAccount">계정 탈퇴</button>
      </div>
    </div>
    
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'
const userStore = useUserStore();
const router = useRouter();
const reservations = ref([]);
const editEmail = ref(false);
const editNickname = ref(false);
const editPassword = ref(false);
const updatedUser = ref(null);
const checkPassword = ref('');

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

const changeEmail = function () {
  editEmail.value = !editEmail.value;
  updatedUser.value = { ...userStore.loginUser };
}

const changeNickname = function () {
  editNickname.value = !editNickname.value;
  updatedUser.value = { ...userStore.loginUser };
}


const changePassword = function () {
  editPassword.value = !editPassword.value;
  updatedUser.value = { ...userStore.loginUser };
}

function checkValidNickname() {
  const validateNickname = /^[A-Za-z가-힣]{1,20}$/;
  if (!validateNickname.test(updatedUser.value.nickname) || !updatedUser.value.nickname) {
    alert('닉네임이 올바르지 않습니다.');
    updatedUser.value.nickname = '';
    return;
  }

  updateUser();
}

function checkValidEmail() {
  if(userStore.loginUser.account === updatedUser.value.account){
    editEmail.value = false;
    return;
  }

  const validateAccount = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
  if (!validateAccount.test(updatedUser.value.account) || !updatedUser.value.account) {
    alert('이메일 형식이 올바르지 않습니다. 다시 한 번 확인 해주세요.');
    updatedUser.value.account = '';
    return;
  }

  axiosInstance
    .get(`http://localhost:8080/user/dupCheck/${updatedUser.value.account}`)
    .then((res) => {
      console.log(res)
      console.log(userStore.loginUser.account)
      console.log(updatedUser.value.account)
      if (res.status === 200) {
        alert('중복된 계정이 존재합니다');
        updatedUser.value.account = '';
        return;
      }

      else {
        updateUser();
      }
    })

}

function checkPasswordEqual() {
  const validatePassword = /^[A-Za-z0-9]{4,12}$/;
  if (!validatePassword.test(updatedUser.value.password) || !updatedUser.value.password) {
    alert('비밀번호는 영문+숫자를 포함한 4자 이상 12자 이내이여야 합니다');
    checkPassword.value = '';
    updatedUser.value.password = '';
    return;
  }

  if (updatedUser.value.password !== checkPassword.value) {
    alert('비밀번호 확인이 일치하지 않습니다');
    checkPassword.value = '';
    updatedUser.value.password = '';
    return;
  }

  updateUser();
}

const updateUser = function () {
  userStore.loginUser = updatedUser.value;
  userStore.updateUser();
  editEmail.value = false;
  editNickname.value = false;
  editPassword.value = false;
}

const deleteReservation = (index) => {
  const reservationToDelete = reservations.value[index];
  console.log(reservationToDelete.num); // 예약의 ID 확인
  axiosInstanceWithToken.delete(`http://localhost:8080/auth/reserve/delete/${reservationToDelete.num}`)
    .then(response => {
      reservations.value.splice(index, 1);
      console.log('예약이 삭제되었습니다.');
    })
    .catch(error => {
      console.error('예약 삭제 중 에러 발생:', error);
    })
}
const deleteAccount = () => {
  if (confirm('정말 계정을 삭제하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
    axiosInstanceWithToken.delete(`http://localhost:8080/auth/unregister`)
      .then(() => {
        userStore.logoutUser();
        alert('계정이 삭제되었습니다.');
        // 로그아웃 실행
        // 메인 화면으로 이동
        router.push('/');
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
  margin-bottom: 200px;
  margin-top: 100px;
  height: 100vh;
  /* background-color: aqua; */
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

.info-name {
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

.deleteAccount {
  /* position: fixed; */
  bottom: 20px;
  right: 20px;
  padding: 10px 20px;
  border: 1px solid #1A2A5F;
  border-radius: 5px;
  background-color: transparent;
  color: #1A2A5F;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s, color 0.3s, border-color 0.3s;
}

.deleteAccount:hover {
  background-color: #1A2A5F;
  color: white;
}
.box1{
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
</style>