<template>
  <div>
    <h2>댓글 입력</h2>
    <div class="comment-form">
      <textarea v-model="content" placeholder="댓글을 작성하세요"></textarea>
      <button @click="submitComment" class="comment-button">댓글 작성</button>
    </div>

    <div class="comment-list">
      <h3>댓글 목록</h3>
      <div v-if="commentStore.comments">
        <ul v-for="(comment, index) in commentStore.comments" :key="comment.num" class="comment-item">
          <li>
            <div class="comment-content" v-if="!comment.editing">
              <div>
                <span id="user-name">{{ comment.user ? comment.user.nickname : "(탈퇴한 사용자)" }}</span>
                <span v-if="comment.user && userStore.loginUser && comment.user.id !== userStore.loginUser.id">
                  <button class="follow-button"
                    v-if="!followStore.followList.some(follow => comment.user.id === follow.followee.id)"
                    @click="followUser(comment.user)">
                    follow<i class="bi bi-person-add"></i>
                  </button>
                  <button class="follow-button" v-else @click="deleteFollow(comment.user)">
                    following<i class="bi bi-person-fill-dash"></i>
                  </button>
                </span>
              </div>
              <span>{{ comment.content }}</span>
              <div>
                <span>{{ comment.updatedAtFormat }}</span>
                <span>{{ comment.createdAtFormat !== comment.updatedAtFormat ? "(수정됨)" : "" }}</span>
              </div>
              <div id="comment-button-container"
                v-if="comment.user && userStore.loginUser && userStore.loginUser.id === comment.user.id">
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
      <div v-else>
        <p>존재하는 댓글이 없습니다</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useCommentStore } from "@/stores/comment";
import { useFollowStore } from "@/stores/follow";
import { useRoute } from 'vue-router';

const userStore = useUserStore();
const boardStore = useBoardStore();
const commentStore = useCommentStore();
const followStore = useFollowStore();
const route = useRoute();
const content = ref('');

onBeforeMount(() => {
  commentStore.getComments(route.params.id);
  boardStore.getBoard(route.params.id)
  followStore.getFollowList();
});

const submitComment = () => {
  if (!userStore.loginUser) {
    alert('로그인이 필요합니다')
    content.value = '';
    return;
  }

  if (content.value == '') {
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
  if (comment.updateContent == '') {
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

const followUser = function (user) {
  if (!userStore.loginUser) {
    alert('로그인이 필요합니다')
    return;
  }

  followStore.startFollow(user);
}

const deleteFollow = function (user) {
  followStore.deleteFollow(user);
}

</script>
<style scoped src="@/assets/boardCommentCreate.css" ></style>
<style scoped src="@/assets/follow.css" ></style>