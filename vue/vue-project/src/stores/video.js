import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_BOARD_API = `http://localhost:8080/video`
const REST_BOARD_API_Like = `http://localhost:8080/auth/like`

export const useVideoStore = defineStore('video', () => {
  const videoList = ref(null)
  const likeList = ref(null);

  
  const getVideoList = function () {
    axiosInstance.get(REST_BOARD_API)
      .then((response) => {
        videoList.value = response.data;
      })
  }

  const clickLike = async function(video) {
    const user = await JSON.parse(sessionStorage.getItem('loginUser'));
    console.log(user);

    axiosInstanceWithToken
    .post(REST_BOARD_API_Like,  {
      user: user,
      video: video
    })
    .then((res) => {
      console.log("ok")
    })
    .catch((err) => {
      console.log(err);
    });
  }

  
  const likeCheck = async function() {
    const user = await JSON.parse(sessionStorage.getItem('loginUser'));

    axiosInstanceWithToken
    .post(REST_BOARD_API_Like + "/get")
    .then((res) => {
      likeList.value = res.data;
    })
    .catch((err) => {
      console.log(err);
    });
  }

  return { getVideoList, videoList, clickLike, likeCheck, likeList }
})
