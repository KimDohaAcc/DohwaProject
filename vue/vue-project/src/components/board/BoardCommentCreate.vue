<template>
  <div>
    <h2>댓글 입력</h2>
    <div class="comment-form">
      <textarea v-model="content" placeholder="댓글을 작성하세요"></textarea>
      <button @click="submitComment1" class="comment-button">댓글 작성</button>
    </div>

    <div v-if="commentList" class="comment-list">
      <h3>댓글 목록</h3>
      <ul v-for="(comment, index) in commentList" :key="comment.num" class="comment-item">
        <li>
          <div class="comment-content" v-if="!comment.editing">
            <span>{{ comment.user.nickname }}</span>
            <span>{{ comment.content }}</span>
            <div>
              <span>{{ comment.updatedAtFormat }}</span>
              <span>{{ comment.createdAtFormat !== comment.updatedAtFormat ? "(수정됨)" : "" }}</span>
            </div>
            <div id="comment-button-container">
              <button @click="startEditing(index)" class="edit-button">수정</button>
              <button @click="deleteComment(comment.num)" class="delete-button">삭제</button>
            </div>
          </div>
          <div class="editing-section" v-else>
            <textarea v-model="comment.updateContent"></textarea>
            <button @click="saveEditedComment(comment)" class="save-button">저장</button>
            <button @click="cancelEditing(index)" class="cancel-button">취소</button>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onBeforeMount } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useCommentStore } from "@/stores/comment";
import axios from "axios";

const userStore = useUserStore();
const boardStore = useBoardStore();
const commentStore = useCommentStore();
const content = ref('');
const commentList = computed(() => commentStore.comments);

onBeforeMount(() => {
  commentStore.getComments(boardStore.board);
});

const submitComment1 = () => {
  const comment = {
    board: boardStore.board,
    user: userStore.loginUser,
    content: content.value,
  };

  commentStore.submitComment(comment)
    .then(() => {
      content.value = '';
    })
    .catch((error) => {
      console.error(error.message); // 오류 메시지 출력
    });
};

const startEditing = (index) => {
  commentList.value[index].editing = true;
  commentList.value[index].updateContent = commentList.value[index].content;
};

const saveEditedComment = function (comment) {
  comment.content = comment.updateContent;
  commentStore.editComment(comment);
  comment.updateContent = '';
  comment.editing = false;
};

const cancelEditing = (index) => {
  commentList.value[index].editing = false;
};

const deleteComment = async (commentId) => {
  try {
    await axios.delete(`http://localhost:8080/comment/${commentId}`);
    await commentStore.deleteComment(commentId);
  } catch (error) {
    console.error(error.message);
  }
};
</script>

<style scoped>
.comment-list {
  margin-top: 20px;
}

.comment-item {
  list-style: none;
  margin: 0;
  padding: 10px;
}

.edit-button,
.delete-button,
.save-button,
.cancel-button {
  margin-left: 10px;
  cursor: pointer;
  background-color: #fff;
  padding: 5px 10px;
  transition: background-color 0.3s ease;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.edit-button:hover,
.delete-button:hover,
.save-button:hover,
.cancel-button:hover {
  background-color: #2980b9;
}

.editing-section {
  margin-top: 10px;
}

.comment-form {
  margin-bottom: 20px;
}

textarea {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ddd;
  border-radius: 5px;
  resize: vertical;
}

.comment-button {
  cursor: pointer;
  background-color: #3498db;
  color: #fff;
  border: none;
  padding: 8px 15px;
  border-radius: 3px;
  transition: background-color 0.3s ease;
}

.comment-button:hover {
  background-color: #2980b9;
}

.comment-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 100%;
}

.comment-content span {
  margin-right: 10px;
}

.comment-content span.username {
  font-weight: bold;
}

.comment-content span.timestamp {
  color: #777;
}

.comment-content span.modified {
  color: #e74c3c;
  font-style: italic;
}

.comment-content span.content {
  flex-grow: 1;
}

.comment-content .edit-button,
.comment-content .delete-button {
  cursor: pointer;
  background-color: #fff;
  padding: 5px 10px;
  border-radius: 3px;
  transition: background-color 0.3s ease;
  border: 1px solid #ddd;
  border-radius: 4px;
  margin-left: auto;
}

.comment-content .edit-button:hover,
.comment-content .delete-button:hover {
  background-color: #2980b9;
}
</style>