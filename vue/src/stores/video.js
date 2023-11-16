import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const REST_BOARD_API = `http://localhost:8080/video`

export const useVideoStore = defineStore('video', () => {
  const videoList = ref(null)
  
  const getVideoList = function () {
    axios.get(REST_BOARD_API)
      .then((response) => {
        videoList.value = response.data;
      })
  }

  return { getVideoList, videoList }
})
