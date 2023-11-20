import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { useUserStore } from './user';
import { useBoardStore } from './board';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_COMMENT_API = 'http://localhost:8080/comment';

export const useCommentStore = defineStore('comment', () => {
  const comment = ref('');
  const comments = ref([]);

  const userStore = useUserStore();
  const boardStore = useBoardStore();

  function startEditing(num) {

  }

  const getComments = (board) => {
    axiosInstanceWithToken.get(`http://localhost:8080/comment/${board.num}`)
      .then((res) => {
        comments.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      })
  };

  function deleteComment(id) {
    // 댓글 목록에서 해당 ID를 가진 댓글을 필터링하여 삭제합니다.
    comments.value = comments.value.filter((comment) => comment.num !== id);

  }

  function editComment(comment, board) {
    //카카오계정 로그인하고싶어->카카오로그인,비밀번호 서버에 보내->nickname, account, id
      console.log(comment);
        // 해당 댓글의 내용을 수정합니다.
        // 서버에 댓글 수정 API 호출
        axios.put(`http://localhost:8080/comment`, comment)
          .then((response) => {
            alert("댓글이 성공적으로 수정되었습니다.");
            // 리스트 안에 있는 댓글을 업데이트
            const newComment = comments.value.find((findComment) => findComment.num === comment.id);
          })
          .catch((error) => {
            console.error("댓글 수정 실패:", error);
            alert("댓글 수정에 실패했습니다.");
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
  },
  {
    persist: {
      storage: sessionStorage,
    }
  }
});