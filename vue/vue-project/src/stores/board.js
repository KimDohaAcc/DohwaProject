import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_BOARD_API = `http://localhost:8080/board`

export const useBoardStore = defineStore('board', () => {
  const boardList = ref([])
  const board = ref(null)

  const getBoardList = function () {
    axiosInstance.get(REST_BOARD_API)
      .then((response) => {
        const list = [];
        for(let i = 0; i < response.data.length ; i ++){
          const board = response.data[i];
          let date = new Date(response.data[i].createdAt);
          board.createdAt = date.toISOString().split('T')[0];
          list.push(board);
       };

       boardList.value = list;
      })
  }

  //게시글 한개
  const getBoard = function (id) {
    axiosInstance.get(`${REST_BOARD_API}/${id}`)
      .then((response) => {
        console.log(board.value)
         board.value = response.data
    })
  }

  //게시글 등록
  // const createBoard = function (board) {
  //   axios.post(REST_BOARD_API, board)
  //     .then(() => {
  //       router.push({ name: 'boardList'})
  //     })
  //     .catch((err) => {
  //     console.log(err)
  //   })
  // }
  const createBoard = function (board) {
    axiosInstanceWithToken.post(REST_BOARD_API, board)
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
      router.push({name: 'boardList'})
    })
  }

  // const searchBoardList = function (searchCondition) {
  //   const API = `http://localhost:8080/board/search`;
  //   axios.get(API, {
  //     params: searchCondition
  //   })
  //     .then((response) => {
  //       const list = [];
        
  //       for(let i = 0; i < response.data.length ; i ++){
  //         const board = response.data[i];
  //         let date = new Date(response.data[i].createdAt);
  //         board.createdAt = date.toISOString().split('T')[0];
  //         list.push(board);
  //      };

  //      boardList.value = list;
  //   })
  // }


  return { boardList, getBoardList, board, getBoard, createBoard, updateBoard }
},
{
  persist: {
    storage: sessionStorage,
  }
})
