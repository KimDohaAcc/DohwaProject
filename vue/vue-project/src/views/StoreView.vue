

<template>
  <div>

    <StoreIntroduce></StoreIntroduce>
    <h2 class="title">헬스판다 지점</h2>
    <div class="container">
      <StoreMap></StoreMap>
    </div>
  </div>
</template>

<script setup>
import StoreIntroduce from '@/components/store/StoreIntroduce.vue';
import StoreMap from '@/components/store/StoreMap.vue';

</script>

<style scoped>
.container {
  margin-left: 25%;
  align-items: center;
  /* 수직 가운데 정렬 */
  justify-content: center;
  /* 수평 가운데 정렬 */

}

footer .btn.btn-link {
  color: white;
}

.text-primary {
  color: white;
}

#nav-collapse {
  list-style: none;
}

footer {

  bottom: 0;
  left: 0;
  width: 100%;
  background-color: #1A2A5F;
  color: white;
  z-index: 9999;
}

.title {
  font-size: 70px;
  text-align: center;
  margin-top: 80px;
  margin-bottom: 80px;
}</style>