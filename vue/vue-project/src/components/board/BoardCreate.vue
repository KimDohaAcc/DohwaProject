
<template>
  <div>
    <h4>헬스판다 이용후기</h4>
    <fieldset>
      <legend>등록</legend>
      <div class="form-group">
        <label for="title">제목 : </label>
        <input type="text" id="title" v-model="board.title" class="input-field">
      </div>
      <div class="form-group">
        <label for="user">작성자 : </label>
        <span class="user-nickname">{{ nickname }}</span>
      </div>
      <div class="form-group">
        <label for="content">내용 : </label>
        <textarea id="content" cols="30" rows="10" v-model="board.content" class="input-field"></textarea>
      </div>
      <div class="form-group">
        <button @click="createBoard" class="submit-button">등록</button>
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
  if (board.value.title == '') {
    alert('제목을 입력하세요');
    return;
  }

  if (board.value.content == '') {
    alert('내용을 입력하세요');
    return;
  }

  store.createBoard(board.value)
}
</script>
<style scoped>
.form-group {
  margin-bottom: 20px;
}

.input-field {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  resize: vertical;
}

.user-nickname {
  font-weight: bold;
}

.submit-button {
  cursor: pointer;
  background-color: #1A2A5F;
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 3px;
  transition: background-color 0.3s ease;
}

.submit-button:hover {
  background-color: #1A2A5F;
}
</style>