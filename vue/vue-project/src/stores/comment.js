import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';
import { useBoardStore } from './board';

const REST_COMMENT_API = 'http://localhost:8080/comment';

export const useCommentStore = defineStore('comment', () => {
  const comment = ref(''); // comment 변수를 ref로 선언합니다.
  const comments = ref([]); 

  function submitComment(commentValue) {
    return new Promise((resolve, reject) => {
      console.log(commentValue);
      if (commentValue) {
        axios.post(REST_COMMENT_API, commentValue )
          .then((response) => {
            comments.value.push(response.data); 
            resolve(); 
          })
          .catch((error) => {
            reject(error); // 실패 시 reject 호출
          });
      } else {
        reject(new Error('댓글 내용을 입력하세요')); // 내용이 없으면 reject 호출
      }
    });
  }

  return {
    comment,
    comments,
    submitComment,
  };
});
