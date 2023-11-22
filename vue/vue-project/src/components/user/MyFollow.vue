<template>
    <div>
      <h1>{{ userStore.loginUser.nickname }}님의 친구</h1>
      <div>
        <h2>Follower</h2>
        <div v-if="!followStore.followerList">
          <p>팔로워가 없습니다.</p>
        </div>
        <ul v-else class="follower-list">
          <li v-for="follow in followStore.followerList" :key="follow.num" class="follower-item">
            <p>{{ follow.follower.nickname }}</p>
          </li>
        </ul>
      </div>
      <div>
        <h2>Followee</h2>
        <div v-if="!followStore.followList">
          <p>팔로잉 중인 사용자가 없습니다.</p>
        </div>
        <ul v-else class="following-list">
          <li v-for="follow in followStore.followList" :key="follow.num" class="following-item">
            <p>{{ follow.followee.nickname }}</p>
          </li>
        </ul>
      </div>
    </div>
  </template>

<script setup>
import { useFollowStore } from "@/stores/follow";
import { useUserStore } from "@/stores/user";
import { onMounted, computed } from 'vue';

const followStore = useFollowStore();
const userStore = useUserStore();

onMounted(() => {
    followStore.getFolloweeList();
    followStore.getFollowerList();
});
</script>

<style scoped>
h1 {
  font-size: 1.5em;
  margin-bottom: 10px;
}

h2 {
  font-size: 1.2em;
  margin-top: 20px;
  margin-bottom: 10px;
}

ul {
  list-style: none;
  padding: 0;
}

.follower-list,
.following-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(120px, 1fr));
  gap: 10px;
}

.follower-item,
.following-item {
  border: 1px solid #ddd;
  padding: 10px;
  text-align: center;
  border-radius: 5px;
}

.follower-item p,
.following-item p {
  margin: 0;
  font-size: 1em;
}
</style>