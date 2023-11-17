import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';
import { useBoardStore } from './board';

const REST_COMMENT_API = 'http://localhost:8080/comment';

export const useCommentStore = defineStore('comment', () => {
  const comment = ref('');
  const comments = ref([]);

  function deleteComment(id) {
    comments.value = comments.value.filter((v) => v.num !== id);
    console.log("댓글리스트 : "+comments);
  }

  function submitComment(commentValue) {
    return new Promise((resolve, reject) => {
      console.log(commentValue);
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

  return {
    comment,
    comments,
    submitComment,
    deleteComment,
    filteredComments, // computed로 생성한 속성을 반환합니다.
  };
  
}
);
