<template>
  <div>
    <!-- 댓글 입력 폼 -->
    <textarea v-model="content"></textarea>
    <button @click="submitComment1">댓글 작성</button>

    <!-- 댓글 목록 -->
    <div v-if="commentList.length">
      <h3>댓글 목록</h3>
      <ul>
        <template v-for="(comment, index) in commentList" :key="index">
          <li>
            <div v-show="!updateCheck(comment)">
              {{ comment.content }}
              <button @click="startEditing(comment)">수정</button>
              <button @click="deleteComment(comment.num)">삭제</button>
            </div>
            <div v-show="updateCheck(comment)">
              <textarea v-model="comment.updatedContent"></textarea>
              <button @click="saveEditedComment(comment)">저장</button>
              <button @click="cancelEditing(comment)">취소</button>
            </div>
          </li>
        </template>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { useCommentStore } from "@/stores/comment";
import axios from "axios";
import { useRouter } from "vue-router";

const userStore = useUserStore();
const boardStore = useBoardStore();
const commentStore = useCommentStore();
const router = useRouter();
const content = ref('');

const commentList = computed(() => {
  return commentStore.comments.map(comment => {
    return {
      ...comment,
      isEditing: false,
      updatedContent: comment.content,
    };
  });
});


const updateCheck = function(comment){
  const res = computed(() => comment.isEditing);
  return res.value;
}



const submitComment1 = () => {
  const comment = {
    board: boardStore.board,
    user: userStore.loginUser,
    content: content.value,
  };

  commentStore.submitComment(comment)
      .then(() => {
        alert("등록");
        content.value = ''; // 댓글 입력 필드 초기화
      })
      .catch((error) => {
        console.error(error.message); // 오류 메시지 출력
      });
};

const startEditing = (comment) => {
  comment.isEditing = true;

};

function editComment(comment, board) {
  return new Promise((resolve, reject) => {
    axios.put(`http://localhost:8080/comment`, comment)
      .then((response) => {
        alert("댓글이 성공적으로 수정되었습니다.");
        // 리스트 안에 있는 댓글을 업데이트
        const newComment = comments.value.find((findComment) => findComment.num === comment.id);
        resolve(newComment);
      })
      .catch((error) => {
        console.error("댓글 수정 실패:", error);
        alert("댓글 수정에 실패했습니다.");
        reject(error);
      });
  });
}

// saveEditedComment 함수 수정
const saveEditedComment = function(comment) {
  return new Promise((resolve, reject) => {
    commentStore.editComment(comment)
      .then((newComment) => {
        if (newComment) {
          comment.isEditing = false; // 수정 상태 해제
          resolve(); // 성공적으로 처리됨을 알림
        } else {
          console.error("댓글 수정에 실패했습니다.");
          reject(new Error("댓글 수정에 실패했습니다.")); // 실패 시 에러 처리
        }
      })
      .catch((error) => {
        console.error("댓글 수정 실패:", error);
        reject(error); // 에러 발생 시 에러 처리
      });
  });
};

const cancelEditing = (comment) => {
  comment.isEditing = false;
  comment.updatedContent = comment.content;
};

const deleteComment = async (commentId) => {
  try {
    await axios.delete(`http://localhost:8080/comment/${commentId}`);
    await commentStore.deleteComment(commentId);
    alert("삭제되었습니다.");
    console.log(commentList);
  } catch (error) {
    console.error(error.message);
  }
};
</script>

<style scoped></style>