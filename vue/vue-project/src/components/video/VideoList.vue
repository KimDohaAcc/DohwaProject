<template>
  <div>
    <div class="filter-buttons">
      <button :class="{ active: store.selectedSort === '전체' }" @click="filterVideos('전체')">전체</button>
      <button :class="{ active: store.selectedSort === '전신' }" @click="filterVideos('전신')">전신</button>
      <button :class="{ active: store.selectedSort === '하체' }" @click="filterVideos('하체')">하체</button>
      <button :class="{ active: store.selectedSort === '상체' }" @click="filterVideos('상체')">상체</button>
      <button :class="{ active: store.selectedSort === '복부' }" @click="filterVideos('복부')">복부</button>
    </div>

    <ul class="video-list">
      <li v-for="video in filteredVideoList" :key="video.num" class="video-item">
        <div class="video-content">
          <p class="video-title">
            {{ video.title }}
          </p>
        </div>
        <iframe :src="video.url" frameborder="0" allowfullscreen></iframe>
        <div class="like-container">
        <a @click="clickLike(video)">
          <span>{{ videoLikeCount(video.num) }}</span>
              <span v-if="hasLike(video)">
                <i class="bi bi-heart-fill"></i>
              </span>
              <span v-else>
                <i class="bi bi-heart"></i>
              </span>
            </a>
          </div>
      </li>
    </ul>
  </div>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { computed, onBeforeMount } from "vue";

const store = useVideoStore();
const videoList = computed(() => store.videoList);
const likeList = computed(() => store.likeList);
const videoLikeCountList = computed(() => store.videoLikeCountList);
const videoLikeCount = computed(() => {
  return (videoNum) => {
    return videoLikeCountList.value[videoNum] || 0;
  };
});

onBeforeMount(() => {
  store.getVideoList();
  store.likeCheck();
});

const clickLike = async function (video) {
  await store.clickLike(video);
  store.likeCheck();
};

const hasLike = function (video) {
  return likeList.value !== null && Object.values(likeList.value).some(like => {
    return like.video.num === video.num;
  });
};

const filteredVideoList = computed(() => {
  const selectedSort = store.selectedSort;
  if (selectedSort === '전체') {
    return videoList.value;
  }

  return videoList.value.filter(video => video.sort === selectedSort);
});

const filterVideos = function (sort) {
  store.selectedSort = sort;
};
</script>
<style src="@/assets/video.css" scoped>
</style>
