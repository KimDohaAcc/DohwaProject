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
  const boardStore = useBoardStore();

  function getComments(board) {
    axiosInstance.get(`http://localhost:8080/comment/board/${board.num}`)
      .then((res) => {
        console.log(res.data)
        if(res.data.length === 0){
          comments.value = null;
          return;
        }

        const list = [];
        for (let i = 0; i < res.data.length; i++) {
          let comment = res.data[i];
          comment = setCommentDate(comment);
          list.push(comment);
        };

        comments.value = list;
      })
      .catch((err) => {
        console.log(err);
      })
  };

  function deleteComment(commentId) {
    try {
      axiosInstanceWithToken.delete(`http://localhost:8080/comment/${commentId}`);
    } catch (error) {
      console.error(error.message);
    }
    getComments(boardStore.board)
  }

  function editComment(editedComment) {
    axiosInstanceWithToken.put(`http://localhost:8080/comment`, editedComment)
      .then((response) => {
        getComments(boardStore.board);
      })
      .catch((error) => {
        console.error("댓글 수정 실패:", error);
      });
  }

  function setCommentDate(comment) {
    let created = new Date(comment.createdAt);
    let updated = new Date(comment.updatedAt);
    comment.createdAtFormat = useformatDate(created);
    comment.updatedAtFormat = useformatDate(updated);

    return comment;
  }

  function submitComment(commentValue) {
    commentValue.user = userStore.loginUser;

    return new Promise((resolve, reject) => {
      if (commentValue) {
        axiosInstanceWithToken.post(REST_COMMENT_API, commentValue)
          .then((response) => {
            getComments(boardStore.board);
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