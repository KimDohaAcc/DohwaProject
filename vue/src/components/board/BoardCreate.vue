<template>
    <div>
        <h4>게시글 작성</h4>
        <fieldset>
            <legend>등록</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="board.title">
            </div>
            <div>
                <label for="user">작성자 : </label>
                <span>{{ nickname }}</span>
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="board.content"></textarea>
            </div>
            <div>
                <button @click="createBoard">등록</button>
            </div>
            
        </fieldset>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();
const store = useBoardStore();
const nickname = ref(userStore.loginUser.nickname);
const board = ref({
    title: '',
    
    user: userStore.loginUser,
    content: ''
})

const createBoard = function () {
    store.createBoard(board.value)
}
</script>

<style scoped></style>