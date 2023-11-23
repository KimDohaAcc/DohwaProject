<template>
    <div class="liked-videos" v-if="userStore.neededUser">
        <h1>{{ userStore.neededUser.nickname }}님이 좋아요 한 영상</h1>
        <div v-if="!videoStore.likeList || videoStore.likeList.length == 0">
            <p>좋아요 한 영상이 없습니다</p>
        </div>
        <ul class="video-list">
      <li v-for="like in videoStore.likeList" :key="like.num" class="video-item">
        <div class="video-content">
          <p class="video-title">{{ like.video.title }}</p>
        </div>
        <div class="video-frame">
          <iframe :src="like.video.url" frameborder="0" allowfullscreen></iframe>
        </div>
        <div class="comment-container"></div>
                <div class="like-container">
                    <a @click="clickLike(like.video)">
                        <span class="like-count">{{ videoLikeCount(like.video.num) }}</span>
                        <span v-if="hasLike(like.video)" class="liked">
                            <i class="bi bi-heart-fill"></i>
                        </span>
                        <span v-else class="not-liked">
                            <i class="bi bi-heart"></i>
                        </span>
                    </a>
                </div>
            </li>
        </ul>
    </div>
    <li v-for="follow in followStore.followerList" :key="follow.num" class="follower-item">
  <div class="dropdown">
    <div @click="toggleDropdown(follow.num)" class="follower-content">
      <img src="@\assets\사람.png" alt="Follower Profile" class="follower-profile">
      <p>{{ follow.follower.nickname }}</p>
    </div>
    <div v-if="follow.dropdownOpen" class="dropdown-content">
      <RouterLink :to="`/like/${follow.follower.id}`">좋아하는 비디오</RouterLink>
      <RouterLink :to="`/board/write/${follow.follower.id}`">작성한 글</RouterLink>
    </div>
  </div>
</li>
</template>

<script setup>
import { useVideoStore } from "@/stores/video";
import { useUserStore } from "@/stores/user";
import { onMounted, computed, ref } from 'vue';
import { useRoute } from 'vue-router'

const videoStore = useVideoStore();
const userStore = useUserStore();
const route = useRoute();
const videoLikeCountList = computed(() => videoStore.videoLikeCountList);
const videoLikeCount = computed(() => {
    return (videoNum) => {
        return videoLikeCountList.value[videoNum] || 0;
    };
});

onMounted(() => {
    userStore.getUserById(route.params.id);
    videoStore.likeCheck(route.params.id);
    videoStore.getVideoList();
});


const clickLike = async function (video) {
    await videoStore.clickLike(video);
    videoStore.likeCheck(route.params.id);
};

const hasLike = function (video) {
    return videoStore.likeList !== null && videoStore.likeList.some(like => {
        return like.video.num === video.num;
    });
};


</script>

<style scoped src="@/assets/video.css"></style>