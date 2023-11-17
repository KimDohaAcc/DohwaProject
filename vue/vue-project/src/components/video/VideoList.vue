<template>
    <div>
        <h5>추천 영상 목록</h5>
        <ul>
      <li v-for="video in videoList" :key="video.num">
        <p>
          {{ video.title }}
          <a @click="clickLike(video)">
            <!-- <span v-if="hasLike(video)">❤</span>
            <span v-else>[좋아요 하기]</span> -->
          </a>
        </p>
        <iframe width="640" height="360" :src="video.url" frameborder="0" allowfullscreen></iframe>
      </li>
    </ul>
    </div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { onMounted, computed } from "vue";


const store = useVideoStore();
const videoList = computed(() => store.videoList );

onMounted(() => {
    store.getVideoList();
    store.likeCheck();
})

const clickLike = function(video) {
  store.clickLike(video);
}

const likeList = computed(()=> store.likeList);
console.log(likeList)

// const hasLike = function (video) {
//   return likeList.some((like) => like.video === video);
// };

</script>

<style scoped></style>