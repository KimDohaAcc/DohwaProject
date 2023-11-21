<template>
    <div>
        <h5>이용 후기 목록</h5>
        <hr>
        <table>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일</th>
                <th>수정일</th>
            </tr>
            <tr v-for="(board, index) in store.boardList" :key="board.num">
                <td>{{ index + 1 }}</td>
                <td>
                    <RouterLink :to="`/board/${board.num}`">{{ board.title }}</RouterLink>
                </td>
                <td>{{ board.user.nickname }}</td>
                <td>{{ board.createdAt }}</td>
            </tr>
        </table>
        <button>
        <RouterLink :to="{ name: 'boardCreate' }" class="create-board-link">보드 작성</RouterLink>
      </button>
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
button {
  cursor: pointer;
  padding: 10px 15px;
  background-color: #3498db;
  color: #fff;
  border: none;
  border-radius: 3px;
}

button:hover {
  background-color: #2980b9;
}

.create-board-link {
  text-decoration: none;
  color: #fff;
  font-weight: bold;
}
</style>