import { defineStore } from "pinia";
import { ref } from "vue";
import { useBoardStore } from "@/stores/board";
import { useUserStore } from "@/stores/user";
import { axiosInstanceWithToken } from '@/util/http-common'

export const useFollowStore = defineStore('follow', () => {

  const followList = ref(null);
  const followerList = ref(null);
  const API_URL = "http://localhost:8080/auth/follow"
  const checkFollow = ref(false);
  const boardStore = useBoardStore();
  const userStore = useUserStore();

  const D_FOLLOW_API_AUTH = `https://healthpanda.site/auth/follow`

  const startFollow = function (user) {
    axiosInstanceWithToken
      .post(D_FOLLOW_API_AUTH, user)
      .then((res) => {
        if (res.status === 204) {
          alert(res.data);
        }

        getFollowList();
      })
      .catch((err) => {
        console.log(err)
      })
  }

  const getFollowList = function () {
    if (!userStore.loginUser) {
      return;
    }

    axiosInstanceWithToken
      .get(D_FOLLOW_API_AUTH)
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
      .get(D_FOLLOW_API_AUTH)
      .then((res) => {
        if (res.data.length > 0) {
          followList.value = res.data;
        }

      })
      .catch((err) => {
        console.log(err);
      })
  }

  const getFollowerList = function () {
    axiosInstanceWithToken
      .get(`${D_FOLLOW_API_AUTH}/followee`)
      .then((res) => {
        if (res.data.length > 0) {
          followerList.value = res.data;
        }
      })
      .catch((err) => {
        console.log(err);
      })
  }

  const deleteFollow = function (user) {
    axiosInstanceWithToken
      .post(D_FOLLOW_API_AUTH + '/delete', user)
      .then((res) => {
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