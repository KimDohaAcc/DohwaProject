<template>
  <div class="board-list">
    <h5>이용 후기</h5>
    <hr>
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
        <tr v-for="(board, index) in store.boardList" :key="board.num">
          <td>{{ index + 1 }}</td>
          <td>
            <RouterLink :to="`/board/${board.num}`" class="link">{{ board.title }}</RouterLink>
          </td>
          <td>{{ board.user ? board.user.nickname : "(탈퇴한 사용자)" }}</td>
          <td>{{ board.createdAtFormat }}</td>
        </tr>
      </tbody>
    </table>
    <div class="button-container">
      <button>
        <RouterLink :to="{ name: 'boardCreate' }" class="create-board-link">보드 작성</RouterLink>
      </button>
    </div>
    <!-- <BoardSearchInput /> -->
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted, computed } from "vue";
import BoardSearchInput from "./BoardSearchInput.vue";
const store = useBoardStore()

onMounted(() => {
  store.getBoardList();
})

</script>

<style scoped>
.board-list {
  max-width: 70vw;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}

.board-table {
  width: 100%;
  margin-top: 10px;
  border-collapse: collapse;
}

.board-table th,
.board-table td {
  padding: 8px;
  text-align: left;
  border: 1px solid #ddd;
}

.board-table th {
  background-color: #f0f0f0;
}

.button-container {
  margin-top: 10px;
  text-align: right;
}

button {
  cursor: pointer;
  padding: 8px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  background-color: white;
}

button:hover {
  background-color: #f0f0f0;
  transition: background-color 0.3s ease;
}

.create-board-link {
  text-decoration: none;
  color: black;
}

.link {
  text-decoration: none;
  color: black;
  font-weight: 300;
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
</style>