import { defineStore } from "pinia";
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

export const useFollowStore = defineStore('follow', () => {

    const followList = ref(null);
    const API_URL = "http://localhost:8080/auth/follow"
    const checkFollow = ref(false);
    const boardStore = useBoardStore();

    const startFollow = function(user) {
      axiosInstanceWithToken
      .post(API_URL, user)
      .then((res) => {
        console.log(res.data);
        getFollowList();
        console.log(checkFollow.value)
      })
      .catch((err) => {
        console.log(err);
      })
    }

    const getFollowList = function() {
      axiosInstanceWithToken
      .get(API_URL)
      .then((res) => {
        followList.value = res.data;
        console.log(followList.value)
        checkFollow.value = followList.value.some(follow => boardStore.board.user.id === follow.follower.id);
      })
      .catch((err) => {
        console.log(err);
      })
    }

    const deleteFollow = function(user) {
      console.log(user)
      axiosInstanceWithToken
      .post(API_URL + '/delete', user)
      .then((res) => {
        console.log(res);
        getFollowList();
      })
      .catch((err) => {
        console.log(err);
      })
    }


      return { 
        startFollow,
        getFollowList,
        deleteFollow,
        checkFollow }
    },
)