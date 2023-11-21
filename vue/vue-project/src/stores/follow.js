import { defineStore } from "pinia";
import { ref } from "vue";
import { axiosInstance, axiosInstanceWithToken } from '@/util/http-common'

export const useFollowStore = defineStore('follow', () => {

    const followList = ref(null);
    const API_URL = "http://localhost:8080/auth/follow"

    const startFollow = function(user) {
      axiosInstanceWithToken
      .post(API_URL, user)
      .then((res) => {
        console.log(res.data);
        getFollowList;
      })
      .catch((err) => {
        console.log(err);
      })
    }

    const getFollowList = function() {
      axiosInstanceWithToken
      .get(API_URL)
      .then((res) => {
        console.log(res.data);
        followList.value = res.data;
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
        getFollowList;
      })
      .catch((err) => {
        console.log(err);
      })
    }


      return { 
        startFollow,
        getFollowList,
        deleteFollow }
    },
)