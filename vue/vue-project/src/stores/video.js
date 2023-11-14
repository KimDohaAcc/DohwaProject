import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/video`

export const useBoardStore = defineStore('video', () => {
  const videoList = ref([])
  const getVideoList = function () {
    axios.get(REST_BOARD_API)
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

  return { getVideoList }
})
