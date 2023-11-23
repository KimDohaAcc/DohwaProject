<template>
  <div class="board-list">
    <h5 class="board-title">이용 후기</h5>
    <h6 class="board-cotent">헬스판다를 이용한 회원님들의 솔직한 후기를 보실 수 있습니다.</h6>
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
        <tr v-for="(board, index) in store.boardList" :key="board.num">
          <td>{{ store.boardList.length - index }}</td>
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
        <RouterLink :to="{ name: 'boardCreate' }" class="create-board-link">후기 남기기</RouterLink>
      </button>
    </div>
    <!-- <BoardSearchInput /> -->
  </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { onMounted } from "vue";
const store = useBoardStore()

onMounted(() => {
  store.getBoardList();
})

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
.board-cotent{
 margin-bottom: 50px;
 text-align: center;
}
button:hover {
  background-color:#1A2A5F;
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
</style>