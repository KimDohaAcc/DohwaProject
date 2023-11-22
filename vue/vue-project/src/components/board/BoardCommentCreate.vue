<template>
  <div>
    <h2>댓글 입력</h2>
    <div class="comment-form">
      <textarea v-model="content" placeholder="댓글을 작성하세요"></textarea>
      <button @click="submitComment" class="comment-button">댓글 작성</button>
    </div>

    <div v-if="commentStore.comments" class="comment-list">
      <h3>댓글 목록</h3>
      <ul v-for="(comment, index) in commentStore.comments" :key="comment.num" class="comment-item">
        <li>
          <div class="comment-content" v-if="!comment.editing">
            <span>{{ comment.user.nickname }}</span>
            <span>{{ comment.content }}</span>
            <div>
              <span>{{ comment.updatedAtFormat }}</span>
              <span>{{ comment.createdAt !== comment.updatedAt ? "(수정됨)" : "" }}</span>
            </div>
            <div id="comment-button-container" v-if="userStore.loginUser.id === comment.user.id">
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
import { ref, onBeforeMount } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useCommentStore } from "@/stores/comment";

const userStore = useUserStore();
const boardStore = useBoardStore();
const commentStore = useCommentStore();
const content = ref('');

onBeforeMount(() => {
  commentStore.getComments(boardStore.board);
});

const submitComment = () => {
  if(content.value == ''){
    alert('내용을 입력하세요');
    return;
  }

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
  commentStore.comments[index].editing = true;
  commentStore.comments[index].updateContent = commentStore.comments[index].content;
};

const saveEditedComment = function (comment) {
  if(comment.updateContent == ''){
    alert('내용을 입력하세요');
    return;
  }

  comment.content = comment.updateContent;
  commentStore.editComment(comment);
 
};

const cancelEditing = (index) => {
  commentStore.comments[index].editing = false;
};

const deleteComment = (commentId) => {
 commentStore.deleteComment(commentId);
};
</script>
<style scoped src="@/assets/boardCommentCreate.css" >
</style>