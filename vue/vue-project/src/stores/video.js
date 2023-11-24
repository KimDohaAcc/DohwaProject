import { ref } from 'vue'
import { defineStore } from 'pinia'
import { useUserStore } from '@/stores/user';
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

const REST_BOARD_API = `http://localhost:8080/video`
const REST_BOARD_API_Like = `http://localhost:8080/auth/like`
const lApi = "http://localhost:8080";
const dApi = "https://healthpanda.site";
export const useVideoStore = defineStore('video', () => {
  const videoList = ref(null)
  const videoLikeCountList = ref({});
  const likeList = ref(null);
  const userStore = useUserStore();

  const selectedSort = ref('전체');

  const getVideoList = async function () {
    await axiosInstance.get(`${dApi}/video`)
      .then(async function (res) {
        videoList.value = res.data;
        await Promise.all(videoList.value.map(video => getLikeCount(video.num)));

        videoList.value.sort((a, b) => {
          const likeCountA = videoLikeCountList.value[a.num] || 0;
          const likeCountB = videoLikeCountList.value[b.num] || 0;
          return likeCountB - likeCountA;
        });
      })
      .catch((err) => {
        console.log(err);
      });
  }


  const clickLike = function (video) {
    axiosInstanceWithToken
      .post(`${dApi}/auth/like`, video)
      .then((res) => {
        getVideoList();
        likeCheck(userStore.loginUser.id);
      })
      .catch((err) => {
        console.log(err);
      });
  }


  const likeCheck = function (id) {
    if (!userStore.loginUser) {
      return;
    }

    axiosInstanceWithToken
      .get(`${dApi}/auth/like/${id}`)
      .then((res) => {
        likeList.value = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
  }

  const getLikeCount = async function (videoNum) {
    await axiosInstance.get(`${dApi}/like/${videoNum}`)
      .then((res) => {
        videoLikeCountList.value[videoNum] = res.data
      })
      .catch((err) => {
        console.log(err);
      });
  }

  return { getVideoList, videoList, clickLike, likeCheck, likeList, selectedSort, getLikeCount, videoLikeCountList }
})
