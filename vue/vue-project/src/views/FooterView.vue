
<template>
  <footer>
      <div class="container py-5">
        <div class="row g-5">
          <div class="col-lg-3 col-md-6">
            <h4 class="mb-4">헬스 판다</h4>
            <p class="mb-2"><i class="fa fa-map-marker-alt text-primary me-3"></i>테헤란로212, 강남구, 서울특별시</p>
            <p class="mb-2"><i class="fa fa-phone-alt text-primary me-3"></i>010 7270 6318</p>
            <p class="mb-2"><i class="fa fa-envelope text-primary me-3"></i>healthPanda@gmail.com</p>
  
          </div>
          <div class="col-lg-3 col-md-6">
            <h4 class="mb-4">Quick Links</h4>
            <div class="btn-group">
                <button @click="goToMain" class="btn btn-link">맨 위로</button>
                <button @click="goToStoreIntroduce" class="btn btn-link">지점 소개</button>
                <button @click="goToBoard" class="btn btn-link">이용 후기</button>
                <button @click="goToReserve" class="btn btn-link">상담 예약</button>
                
            </div>
  
          </div>
          <div class="col-lg-3 col-md-6">
            <h4 class="mb-4">영업 시간</h4>
            <p class="mb-1">Monday - Friday</p>
            <h6 class="text-light">05:00 am - 12:00 am</h6>
            <p class="mb-1">Saturday</p>
            <h6 class="text-light">05:00 am - 22:00 pm</h6>
            <p class="mb-1">Sunday</p>
            <h6 class="text-light">05:00 am - 22:00 pm</h6>
          </div>
          <div class="col-lg-3 col-md-6">
            <h4 class="mb-4">가맹 문의</h4>
            <p class="mb-1">열렬히 환영합니다.</p>
            <p class="mb-1">대표 김도하</p>
            <p class="mb-1">팀장 오화석</p>
          </div>
        </div>
      </div>
    </footer>
  </template>
  <script setup>
    import { useRoute, useRouter } from 'vue-router'



    const router = useRouter();
    const goToMain = function() {
    window.scroll({
        top:0,
        left:0,
    })}
    const goToStoreIntroduce = function() {
    router.push( "/store" )
    window.scroll({
        top:0,
        left:0,
    })}
    const goToBoard = function() {
    router.push( "/board" )
    window.scroll({
        top:0,
        left:0,
    })}
    const goToReserve = function() {
    router.push( "/reserve" )
    window.scroll({
        top:0,
        left:0,
    })}
  </script>
  
  <style scoped>
  footer .btn.btn-link {
    color: white;
    text-align: left;
  width: auto;
   
  }
  
  .text-primary {
    color: white;
  }
  
  #nav-collapse {
    list-style: none;
  }
  
  footer {
  bottom: 0;
  background-color: #1A2A5F; /* 원하는 배경색으로 설정 */
  color: white;
  z-index: 9999;
  flex-flow: column;
  width: 100%;
}
.btn-group {
  display: flex;
  flex-direction: column; /* 버튼을 세로로 배치 */
  gap: 10px; /* 버튼 사이의 간격 조정 */
}
  
  
  </style>