<template>
    <div>
        <h4>게시글 수정</h4>
        <fieldset>
            <legend>수정</legend>
            <div>
                <label for="title">제목 : </label>
                <input type="text" id="title" v-model="store.board.title">
            </div>
            <div>
                <label for="user">작성자 : </label>
                <span>{{ store.board.user.nickname }}</span>
            </div>
            <div>
                <label for="content">내용 : </label>
                <textarea id="content" cols="30" rows="10" v-model="store.board.content"></textarea>
            </div>
            <div>
                <button @click="updateBoard">등록</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useBoardStore } from "@/stores/board";
import { useRouter } from 'vue-router'
const store = useBoardStore();
const router = useRouter();

const updateBoard = function () {
    if(store.board.title == ''){
    alert('제목을 입력하세요');
    return;
  }

  if(store.board.content == ''){
    alert('내용을 입력하세요');
    return;
  }

    store.updateBoard();
    router.push(`/board/${store.board.num}`)
}
</script>

<style scoped></style>