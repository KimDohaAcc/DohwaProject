import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_BOARD_API = `http://localhost:8080/board`
const REST_BOARD_API_AUTH = `http://localhost:8080/auth/board`
const D_BOARD_API = `https://healthpanda.site/board`
const D_BOARD_API_AUTH = `https://healthpanda.site/auth/board`

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([])
  const board = ref(null)
  const writeList = ref(null)

  const getBoardList = function () {
    axiosInstance.get(D_BOARD_API)
      .then((response) => {
        const list = [];
        for (let i = 0; i < response.data.length; i++) {
          const board = response.data[i];
          let created = new Date(response.data[i].createdAt);
          let updated = new Date(response.data[i].updatedAt);
          board.createdAtFormat = created.toISOString().split('T')[0];
          board.updatedAtFormat = updated.toISOString().split('T')[0];
          list.push(board);
        };

        list.sort((a, b) => new Date(b.updatedAt) - new Date(a.updatedAt));
        boardList.value = list;
      })
  }

  function deleteBoard(boardNum) {
    axiosInstanceWithToken.delete(`${D_BOARD_API_AUTH}/${boardNum}`)
      .then((res) => {
        getBoardList();
      })
  }

  //게시글 한개
  const getBoard = function (id) {
    axiosInstance.get(`${D_BOARD_API}/${id}`)
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
    axiosInstanceWithToken.post(D_BOARD_API_AUTH, board)
      .then((response) => {
        boardList.value.push(response.data);
        router.push({ name: 'boardList' });
      })
      .catch((err) => {
        console.log(err);
      });
  };


  const updateBoard = function () {
    axiosInstanceWithToken.put(D_BOARD_API, board.value)
      .then(() => {
        getBoard(board.value.num);
      })
      .catch((err) => {
        console.log(err);
      })
  }

  const getWriteList = function (id) {
    axiosInstanceWithToken
      .get(D_BOARD_API_AUTH + `/${id}`)
      .then((response) => {
        const list = [];
        for (let i = 0; i < response.data.length; i++) {
          const board = response.data[i];
          let created = new Date(response.data[i].createdAt);
          let updated = new Date(response.data[i].updatedAt);
          board.createdAtFormat = created.toISOString().split('T')[0];
          board.updatedAtFormat = updated.toISOString().split('T')[0];
          list.push(board);
        };

        list.sort((a, b) => new Date(b.updatedAt) - new Date(a.updatedAt));
        writeList.value = list;
      })
      .catch((err) => {
        console.log(err);
      })
  }

  return { boardList, getBoardList, board, getBoard, createBoard, updateBoard, deleteBoard, writeList, getWriteList }
},
  {
    persist: {
      storage: sessionStorage,
    }
  })
