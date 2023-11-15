<template>
    <div>
        <h5>추천 영상 목록</h5>
        <ul>
      <li v-for="video in videoList" :key="video.num">
        <p>{{ video.title }}</p>
        <iframe width="640" height="360" :src="video.url" frameborder="0" allowfullscreen></iframe>
      </li>
    </ul>
    </div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { onMounted, computed } from "vue";


const store = useVideoStore()
const videoList = computed(() => store.videoList );

onMounted(() => {
    store.getVideoList();
})

</script>

<style scoped></style>