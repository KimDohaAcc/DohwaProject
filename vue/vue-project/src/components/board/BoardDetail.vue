<template>
    <div class="post" v-if="store.board">
      <div class="post-header">
        <div class="user-avatar"></div>
        <div class="user-info">
          <div class="user-nickname">{{ store.board.user.nickname }}</div>
          <div class="timestamp">{{ store.board.createdAt }}</div>
        </div>
        <div class="actions">
          <button @click="deleteBoard" class="action-button delete-button">삭제</button>
          <button @click="updateBoard" class="action-button update-button">수정</button>
        </div>
      </div>
  
      <div class="post-content">
        <div class="title">{{ store.board.title }}</div>
        <div class="content">{{ store.board.content }}</div>
      </div>
      <BoardCommentCreate></BoardCommentCreate>
    </div>
  </template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useBoardStore } from "@/stores/board";
import { onBeforeMount } from "vue";
import axios from 'axios'
import BoardCommentCreate from './BoardCommentCreate.vue';
const store = useBoardStore()
const route = useRoute();
const router = useRouter();

onBeforeMount(() => {
    store.getBoard(route.params.id)
})

const deleteBoard = function () {
    axios.delete(`http://localhost:8080/board/${route.params.id}`)
        .then(() => {
            router.push({ name: 'boardList' })
        })
}

const updateBoard = function () {
    router.push({ name: 'boardUpdate' })
}

</script>

<style scoped>
.post {
  background-color: #fff;
  border: 1px solid #e6e6e6;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  margin: 20px;
  max-width: 600px;
  margin: 0 auto;
}

.post-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border-bottom: 1px solid #e6e6e6;
}

.user-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #ddd; /* Placeholder color */
}

.user-info {
  margin-left: 10px;
}

.user-nickname {
  font-weight: bold;
}

.timestamp {
  color: #999;
}

.actions {
  display: flex;
}

.action-button {
  cursor: pointer;
  margin-left: 10px;
  padding: 8px 15px;
  border: none;
  border-radius: 3px;
}

.delete-button {
  background-color: #ed4956;
  color: #fff;
}

.update-button {
  background-color: #3897f0;
  color: #fff;
}

.post-content {
  padding: 15px;
}

.title {
  font-size: 1.2rem;
  font-weight: bold;
  margin-bottom: 10px;
}

.content {
  margin-top: 15px;
}
</style>