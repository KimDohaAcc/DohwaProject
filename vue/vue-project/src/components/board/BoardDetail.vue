<template>
  <div class="board-post" v-if="store.board">
    <div class="post-header">
      <div class="title">{{ store.board.title }}</div>
      <div class="actions">
        <div class="timestamp">
          {{ store.board.updatedAtFormat }}
          <span>
            {{ store.board.createdAt !== store.board.updatedAt ? "(수정됨)" : "" }}
          </span>
        </div>
        <div v-if="userStore.loginUser && store.board.user && userStore.loginUser.id === store.board.user.id">
          <button @click="updateBoard" class="action-button update-button">수정</button>
          <button @click="deleteBoard" class="action-button delete-button">삭제</button>
        </div>
      </div>
    </div>
    <div>
      <span id="user-name">{{ store.board.user ? store.board.user.nickname : "(탈퇴한 사용자)" }}</span>
      <span v-if="store.board.user && userStore.loginUser && store.board.user.id !== userStore.loginUser.id">
        <button class="follow-button" v-if="!followStore.checkFollow" @click="followUser(store.board.user)">
          follow<i class="bi bi-person-add"></i>
        </button>
        <button class="follow-button" v-else @click="deleteFollow(store.board.user)">
          following<i class="bi bi-person-fill-dash"></i>
        </button>
      </span>
    </div>
    <div class="post-content">
      <div class="content">{{ store.board.content }}</div>
    </div>
    <BoardCommentCreate></BoardCommentCreate>
    <button @click="goToBoardList" class="action-button">목록으로</button>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useFollowStore } from "@/stores/follow";
import { onBeforeMount, ref } from "vue";
import BoardCommentCreate from './BoardCommentCreate.vue';
const store = useBoardStore();
const userStore = useUserStore();
const followStore = useFollowStore();
const route = useRoute();
const router = useRouter();

onBeforeMount(() => {
  store.getBoard(route.params.id)
  followStore.getFollowList();
})

const deleteBoard = function () {
  store.deleteBoard(route.params.id);
  router.push("/board")
}

const goToBoardList = function() {
  router.push( "/board" )
}

const updateBoard = function () {
  router.push({ name: 'boardUpdate' })
}

const followUser = function (user) {
  if (!userStore.loginUser) {

    alert('로그인이 필요합니다')
    return;
  }

  followStore.startFollow(user);
}

const deleteFollow = function (user) {
  followStore.deleteFollow(user);
}

</script>

<style scoped src="@/assets/boardDetail.css"></style>
<style scoped src="@/assets/follow.css" ></style>