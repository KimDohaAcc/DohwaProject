import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_BOARD_API = `http://localhost:8080/board`
const REST_BOARD_API_AUTH = `http://localhost:8080/auth/board`

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([])
  const board = ref(null)

  const getBoardList = function () {
    axiosInstance.get(REST_BOARD_API)
      .then((response) => {
        const list = [];
        for(let i = 0; i < response.data.length ; i ++){
          const board = response.data[i];
          let created = new Date(response.data[i].createdAt);
          let updated = new Date(response.data[i].updatedAt);
          board.createdAtFormat = created.toISOString().split('T')[0];
          board.updatedAtFormat = updated.toISOString().split('T')[0];
          list.push(board);
       };

       boardList.value = list;
      })
  }

  function deleteBoard(boardNum){
    axiosInstanceWithToken.delete(`http://localhost:8080/board/${boardNum}`)
    .then((res) => {
      getBoardList();
    })
  }

  //게시글 한개
  const getBoard = function (id) {
    axiosInstance.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        board.value = response.data;
        board.value = setBoardDate(board.value);
    })
  }

  function setBoardDate(board) {
        let created = new Date(board.createdAt);
        let updated = new Date(board.updatedAt);
        board.createdAtFormat = created.toISOString().split('T')[0];
        board.updatedAtFormat = updated.toISOString().split('T')[0];
        return board;
  }

  const createBoard = function (board) {
    axiosInstanceWithToken.post(REST_BOARD_API_AUTH, board)
      .then((response) => {
        boardList.value.push(response.data);
        router.push({ name: 'boardList' });
      })
      .catch((err) => {
        console.log(err);
      });
  };
  

  const updateBoard = function () {
    axiosInstanceWithToken.put(REST_BOARD_API, board.value)
      .then(() => {
        getBoard(board.value.num);
    })
  }

  return { boardList, getBoardList, board, getBoard, createBoard, updateBoard, deleteBoard }
},
{
  persist: {
    storage: sessionStorage,
  }
})
