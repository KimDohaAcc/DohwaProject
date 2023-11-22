import { defineStore } from "pinia";
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

export const useFollowStore = defineStore('follow', () => {

  const followList = ref(null);
  const followerList = ref(null);
  const API_URL = "http://localhost:8080/auth/follow"
  const checkFollow = ref(false);
  const boardStore = useBoardStore();

  const startFollow = function (user) {
    axiosInstanceWithToken
      .post(API_URL, user)
      .then((res) => {
          if(res.status === 204){
            alert(res.data);
          }

        console.log(res);
        getFollowList();
        console.log(checkFollow.value)
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const getFollowList = function () {
    axiosInstanceWithToken
      .get(API_URL)
      .then((res) => {
        followList.value = res.data;
        checkFollow.value = followList.value.some(follow => boardStore.board.user.id === follow.followee.id);
      })
      .catch((err) => {
        console.log(err);
      })
  }

  const getFolloweeList = function () {
    axiosInstanceWithToken
      .get(API_URL)
      .then((res) => {
        if (res.data.length > 0) {
          followList.value = res.data;
        }
        console.log(followList.value)
        console.log(res.data)

      })
      .catch((err) => {
        console.log(err);
      })
  }

  const getFollowerList = function () {
    axiosInstanceWithToken
      .get("http://localhost:8080/auth/followee")
      .then((res) => {
        if (res.data.length > 0) {
          followerList.value = res.data;
        }
        console.log(followerList.value)
        console.log(res.data)
      })
      .catch((err) => {
        console.log(err);
      })
  }

  const deleteFollow = function (user) {
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
    checkFollow,
    followerList,
    followList,
    getFollowerList,
    getFolloweeList,
  }
},
)