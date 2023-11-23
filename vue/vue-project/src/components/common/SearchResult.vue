<template>
    <div v-if="!searchStore.searchList || searchStore.searchList.length === 0">
        <h2 class="not-found">검색 결과가 없습니다.</h2>
    </div>
    <div v-else>
        <div class="board-list" v-if="searchStore.type === 'board'">
            <hr class="divider">
            <table class="board-table">
                <thead>
                    <tr>
                        <th id="number">번호</th>
                        <th id="title">제목</th>
                        <th id="nickname">작성자</th>
                        <th>등록일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(board, index) in searchStore.searchList" :key="board.num">
                        <td>{{ searchStore.searchList.length - index }}</td>
                        <td>
                            <RouterLink :to="`/board/${board.num}`" class="link">{{ board.title }}</RouterLink>
                        </td>
                        <td>{{ board.user ? board.user.nickname : "(탈퇴한 사용자)" }}</td>
                        <td>{{ board.updatedAtFormat }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="video-list" v-if="searchStore.type === 'video'">
            <ul class="video-list">
                <li v-for="video in searchStore.searchList" :key="video.num" class="video-item">
                    <div class="video-content">
                        <p class="video-title">{{ video.title }}</p>
                    </div>
                    <iframe :src="video.url" frameborder="0" allowfullscreen></iframe>
                    <div class="comment-container"></div>
                    <div class="like-container">
                        <span @click="copyVideoUrl(video)">
                            <i class="bi bi-clipboard" v-if="!video.isCopied"></i>
                            <span v-else><span id="copy-message">copied to clipboard</span><i
                                    class="bi bi-clipboard-check"></i></span>
                        </span>
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
    </div>
</template>
  
<script setup>
import { useSearchStore } from "@/stores/search";
import { useVideoStore } from "@/stores/video";
import { useUserStore } from "@/stores/user";
import { onMounted, computed } from "vue";
const searchStore = useSearchStore()
const videoStore = useVideoStore()
const userStore = useUserStore()

onMounted(() => {
    videoStore.getVideoList();
    videoStore.likeCheck();
    searchStore.loadSession();
})

const videoLikeCount = computed(() => {
    return (videoNum) => {
        return videoStore.videoLikeCountList[videoNum] || 0;
    };
})

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

    await videoStore.clickLike(video);
    videoStore.likeCheck();
};

const hasLike = function (video) {
    return videoStore.likeList !== null && videoStore.likeList.some(like => {
        return like.video.num === video.num;
    });
};
</script>
<style scoped>
.board-list {
    max-width: 70vw;
    margin: 0 auto;
    border: 1px solid #ddd;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

.board-title {
    text-align: center;
    margin-bottom: 10px;
}

.divider {
    margin: 10px 0;
    border: none;
    border-top: 1px solid #ddd;
}

.board-table {
    width: 100%;
    border-collapse: collapse;
}

.board-table th,
.board-table td {
    padding: 12px;
    text-align: center;
    border: 1px solid #ddd;
}

.board-table th {
    background-color: #f0f0f0;
}

.button-container {
    margin-top: 20px;
    text-align: right;
}

button {
    cursor: pointer;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    background-color: #1A2A5F;
    color: white;
    font-weight: bold;
    transition: background-color 0.3s ease;
}

.board-cotent {
    margin-bottom: 50px;
    text-align: center;
}

button:hover {
    background-color: #1A2A5F;
}

.create-board-link {
    text-decoration: none;
    color: white;
}

.link {
    text-decoration: none;
    color: #1A2A5F;
    font-weight: bold;
}

#title {
    width: 50%;
}

#number {
    width: 10%;
}

#nickname {
    width: 20%;
}

#date {
    width: 20%;
}

.video-item {
  display: flex;
  flex-flow: wrap column;
  justify-content: center;
  align-items: center;
  width: 30vw;
  height: fit-content;
  margin-bottom: 20px;
  padding: 5px;
  margin : 10px;
  border: 1px solid #ddd;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}



.bi-clipboard,
.bi-clipboard-check {
  font-size: 1.1rem;
  margin-right: 15px;
}

.video-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.bi-heart-fill,
.bi-heart {
  font-size: 1.1rem;
  margin-left: 5px;
  color: red;
}


iframe {
  width: 530px;
  height: 300px;
}

.video-title {
  font-size: 12pt;
  font-weight: 200;
  color: #333;
  font-family: 'IBM Plex Sans KR', sans-serif;
  margin: 5px 25px;
  font-weight: 500;
}

.like-container {
  align-self: flex-end;
  margin-right: 25px;
  margin-top: 5px;
}

#copy-message {
  background-color: #333;
  border: none;
  border-radius: 4px;
  color: white;
  font-size: 10pt;
  padding: 0 5px;
  margin-right: 5px;
}

.liked-videos {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  margin-left: 15px;
}
.username {
  margin-left: 15px;
}

.not-found {
    text-align: center;
}

.video-list {
  list-style: none;
  padding: 0;
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  overflow: hidden;
}
</style>