<template>
  <div v-if="userStore.neededUser">
    <h1>{{ userStore.neededUser.nickname }}님이 작성한 게시글</h1>
    <hr>
    <div v-if="!boardStore.writeList || boardStore.writeList.length === 0">
      <p>작성한 게시글이 없습니다</p>
    </div>
    <ul v-else class="board-list">
      <li class="board-header">
        <span class="index header-index">Index</span>
        <span class="title header-title">Title</span>
      </li>
      <li v-for="(board, index) in boardStore.writeList" :key="board.num" class="board-item">
        <span class="index">{{ boardStore.writeList.length - index }}</span>
        <div class="title-container">
          <RouterLink :to="`/board/${board.num}`" class="link">{{ board.title }}</RouterLink>
        </div>
      </li>
    </ul>
  </div>
</template>
  
<script setup>
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { onMounted } from "vue";
import { useRoute } from 'vue-router'
const boardStore = useBoardStore();
const userStore = useUserStore();
const route = useRoute();

onMounted(() => {
  userStore.getUserById(route.params.id);
  boardStore.getWriteList(route.params.id);
})

</script>
<style scoped>
.board-list {
  max-width: 70vw;
  margin: 0 auto;
  list-style: none;
  padding: 0;
}

.board-header {
  display: grid;
  grid-template-columns: 1fr 10fr;
  background-color: #f0f0f0;
  padding: 10px;
  font-weight: bold;
  border-bottom: 1px solid #ddd;
}

.board-header>span {
  font-weight: 300;
}

.board-item {
  border: 1px solid #ddd;
  border-radius: 5px;
  margin-top: 10px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  align-items: center;
  border-bottom: 1px solid #ddd;
}

.board-item:hover {
  background-color: #f0f0f0;
}

.index,
.title {
  padding: 10px;
}

.index {
  margin-left: 40px;
}

.header-index {
  margin-left: 15px;
  text-align: center;
}

.header-title {
  margin-left: 27vw;
}

.title-container {
  flex-grow: 1;
}

.link {
  text-decoration: none;
  color: black;
  font-weight: 300;
}
</style>