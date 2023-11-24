import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';
import { useformatDate } from '@/util/dateFormat';
import { useBoardStore } from './board';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const lApi = "http://localhost:8080";
const D_COMMENT_API = `https://healthpanda.site/comment`
const D_COMMENT_API_AUTH = `https://healthpanda.site/auth/comment`

export const useCommentStore = defineStore('comment', () => {
  const comment = ref('');
  const comments = ref(null);
  const userStore = useUserStore();
  const boardStore = useBoardStore();

  function getComments(boardNum) {
    axiosInstance.get(`${D_COMMENT_API}/board/${boardNum}`)
      .then((res) => {
        if (res.data.length === 0) {
          comments.value = null;
          return;
        }

        const list = [];
        for (let i = 0; i < res.data.length; i++) {
          let comment = res.data[i];
          comment = setCommentDate(comment);
          list.push(comment);
        };

        list.sort((a, b) => new Date(b.updatedAt) - new Date(a.updatedAt));
        comments.value = list;

      })
      .catch((err) => {
        console.log(err);
      })
  };

  function deleteComment(commentId) {
    try {
      axiosInstance.delete(`${D_COMMENT_API}/${commentId}`);
    } catch (error) {
      console.error(error.message);
    }
    getComments(boardStore.board.num)
  }

  function editComment(editedComment) {
    axiosInstance.put(`${D_COMMENT_API}`, editedComment)
      .then((response) => {
        getComments(boardStore.board.num);
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
        axiosInstanceWithToken.post(`${D_COMMENT_API_AUTH}`, commentValue)
          .then((response) => {
            getComments(boardStore.board.num);
            resolve();s
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