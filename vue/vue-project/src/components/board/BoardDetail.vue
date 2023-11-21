<template>
  <div class="board-post" v-if="store.board">
    <div class="post-header">
      <div class="title">{{ store.board.title }}</div>
      <div class="actions">
        <div class="timestamp">
          {{ store.board.updatedAtFormat }}
          <span>
            {{ store.board.createdAtFormat !== store.board.updatedAtFormat ? "(수정됨)" : "" }}
          </span>
        </div>
        <button @click="deleteBoard" class="action-button delete-button">삭제</button>
        <button @click="updateBoard" class="action-button update-button">수정</button>
      </div>
    </div>

    <div class="user-info">
        {{ store.board.user.nickname }}
      <span>
          <i class="bi bi-person-add" v-if="isFollowed" @click="followUser(store.board.user)"></i>
          <i class="bi bi-person-fill-dash" v-else @click="deleteFollow(store.board.user)"></i>
      </span>
    </div>

    <div class="post-content">
      <div class="content">{{ store.board.content }}</div>
    </div>

    <BoardCommentCreate></BoardCommentCreate>
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { useFollowStore } from "@/stores/follow";
import { onBeforeMount, ref, computed, watch } from "vue";
import axios from 'axios'
import BoardCommentCreate from './BoardCommentCreate.vue';
const store = useBoardStore();
const followStore = useFollowStore();
const route = useRoute();
const router = useRouter();
const followList = computed(()=> followStore.followList)
const isFollowed = computed(()=> checkFollow)

onBeforeMount(() => {
  store.getBoard(route.params.id)
  followStore.getFollowList();
})

const checkFollow = function () {
  return followList.value.some(follow => store.board.user.id === follow.follower.id);
};

const deleteBoard = function () {
  axios.delete(`http://localhost:8080/board/${route.params.id}`)
    .then(() => {
      router.push({ name: 'boardList' })
    })
}

const updateBoard = function () {
  router.push({ name: 'boardUpdate' })
}

const followUser = function (user) {
  followStore.startFollow(user);
  isFollowed.value = checkFollow;
}

const deleteFollow = function(user){
  followStore.deleteFollow(user);
  isFollowed.value = checkFollow;
}

</script>

<style scoped src="@/assets/boardDetail.css"></style>