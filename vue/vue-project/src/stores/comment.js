import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';
import { useformatDate } from '@/util/dateFormat';
import { useBoardStore } from './board';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_COMMENT_API = 'http://localhost:8080/comment';

export const useCommentStore = defineStore('comment', () => {
  const comment = ref('');
  const comments = ref(null);
  const userStore = useUserStore();

  function getComments(board) {
    console.log(board)
    axiosInstance.get(`http://localhost:8080/comment/board/${board.num}`)
      .then((res) => {
        const list = [];
        for(let i = 0; i < res.data.length ; i ++){
          const comment = res.data[i];
          let created = new Date(res.data[i].createdAt);
          let updated = new Date(res.data[i].updatedAt);
          comment.createdAtFormat = useformatDate(created);
          comment.updatedAtFormat = useformatDate(updated);
          list.push(comment);
       };

        comments.value = list;
      })
      .catch((err) => {
        console.log(err);
      })
  };

  function deleteComment(id) {
    comments.value = comments.value.filter((comment) => comment.num !== id);
  }

  function editComment(editedComment) {
    console.log(editedComment);
    axiosInstanceWithToken.put(`http://localhost:8080/comment`, editedComment)
      .then((response) => {
        const index = comments.value.findIndex((comment) => comment.num === editedComment.num);
        if (index !== -1) {
          comments.value.splice(index, 1, { ...editedComment });
        }
      })
      .catch((error) => {
        console.error("댓글 수정 실패:", error);
      });
  }

  function submitComment(commentValue) {
    commentValue.user = userStore.loginUser;

    return new Promise((resolve, reject) => {
      if (commentValue) {
        axios.post(REST_COMMENT_API, commentValue)
          .then((response) => {
            comments.value.push(response.data);
            resolve();
          })
          .catch((error) => {
            reject(error);
          });
      } else {
        reject(new Error('댓글 내용을 입력하세요'));
      }
    });
  }

  const filteredComments = computed(() => {
    return comments.value;
  });

  
  // // 세션 스토리지에 댓글 저장
  // function saveCommentsToSessionStorage() {
  //   sessionStorage.setItem('comments', JSON.stringify(comments.value));
  // }

  // // 세션 스토리지에서 댓글 가져오기
  // function loadCommentsFromSessionStorage() {
  //   const storedComments = sessionStorage.getItem('comments');
  //   if (storedComments) {
  //     comments.value = JSON.parse(storedComments);
  //   }
  // }

  // 페이지 로드 시 세션 스토리지에서 댓글 가져오기
  // loadCommentsFromSessionStorage();

  return {
    comment,
    comments,
    submitComment,
    editComment,
    deleteComment,
    filteredComments,
    getComments,
  }
});