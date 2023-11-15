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

<style scoped></style>