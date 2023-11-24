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
          <p class="video-title">{{ video.title }}</p>
        </div>
        <iframe :src="video.url" frameborder="0" allowfullscreen></iframe>
        <div class="comment-container"></div>
        <div class="like-container">
          <span @click="copyVideoUrl(video)">
            <i class="bi bi-clipboard" v-if="!video.isCopied"></i>
            <span v-else><span id="copy-message">copied to clipboard</span><i class="bi bi-clipboard-check"></i></span>
          </span>
          <a @click="clickLike(video)">
            <span>{{ videoLikeCount(video.num) }}</span>
            <span v-if="userStore.loginUser && hasLike(video)">
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
import { useUserStore } from "@/stores/user";
import { computed, onBeforeMount, ref } from "vue";

const store = useVideoStore();
const userStore = useUserStore();
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

  if (userStore.loginUser) {
    store.likeCheck(userStore.loginUser.id);
  }
});

const copyVideoUrl = function (video) {
  try {
    navigator.clipboard.writeText(video.url)
      .then(() => {
        video.isCopied = true;
        setTimeout(() => {
          video.isCopied = false;
        }, 1000);
      })
      .catch((error) => {
        console.error('Unable to copy video URL to clipboard', error);
      });
  } catch (error) {
    console.error('Clipboard API not supported', error);
  }
};

const clickLike = async function (video) {
  if (!userStore.loginUser) {

    alert('로그인이 필요합니다')
    return;
  }

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
<style src="@/assets/video.css" scoped></style>
