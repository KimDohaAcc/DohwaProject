<template>
    <div v-if="userStore.neededUser">
        <h1>{{ userStore.neededUser.nickname }}님이 좋아요 한 영상</h1>
        <div v-if="!videoStore.likeList || videoStore.likeList.length == 0">
            <p>좋아요 한 영상이 없습니다</p>
        </div>
        <ul v-else>
            <li v-for="like in videoStore.likeList" :key="like.num">
                <div class="video-content">
                    <p class="video-title">
                        {{ like.video.title }}
                    </p>
                </div>
                <iframe :src="like.video.url" frameborder="0" allowfullscreen></iframe>
                <div class="like-container">
                    <a @click="clickLike(like.video)">
                        <span>{{ videoLikeCount(like.video.num) }}</span>
                        <span v-if="hasLike(like.video)">
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