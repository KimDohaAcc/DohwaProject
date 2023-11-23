

<template>
  <div class="container">
    <h2>보다 빠르고</h2>
    <h2>자세한 상담이 가능합니다.</h2>
    <h3>전화상담 예약을 통해 상담 희망시간을 알려주시면</h3>
    <h3 class="text">예약하신 시간에 입력하신 번호로 전화드리겠습니다.</h3>
    <ReserveRegist></ReserveRegist>
  </div>
</template>

<script setup>
import ReserveRegist from '@/components/reserve/ReserveRegist.vue';
</script>

<style scoped>
.container {
  text-align: center;
  padding: 20px;
}

h2 {
  font-size: 28px;
  margin-bottom: 10px;
  color: #333;
}

h3 {
  font-size: 18px;
  margin-bottom: 15px;
  color: #666;
}

.text{
  margin-bottom: 80px;
}

/* ReserveRegist 컴포넌트 스타일링 */
/* .reserve 클래스를 사용하여 ReserveRegist 컴포넌트의 스타일을 추가하세요 */

</style>
