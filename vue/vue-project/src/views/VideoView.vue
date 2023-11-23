
<template>
  <div class="video-recommendation">
    <h2 class="title">운동 추천 영상</h2>
    <VideoList />
  </div>
</template>

<script setup>
import VideoList from '@/components/video/VideoList.vue';
</script>

<style scoped>
.video-recommendation {
  max-width: 1200px;
  margin: 0 auto;
  padding: 10px;
  margin-bottom: 20%;
}

.title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 20px;
  text-align: center;
}
</style>