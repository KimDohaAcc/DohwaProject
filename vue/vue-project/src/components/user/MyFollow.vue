<template>
  <div class="friend-list">
    <h1>{{ userStore.loginUser.nickname }}님의 친구</h1>

    <!-- Followers -->
    <div class="friend-section">
      <h2>Follower</h2>
      <div v-if="!followStore.followerList">
        <p>팔로워가 없습니다.</p>
      </div>
      <ul v-else class="follower-list">
        <li v-for="follow in followStore.followerList" :key="follow.num" class="follower-item">
          <div class="follower-content">
            <img src="@\assets\사람.png" alt="Follower Profile" class="follower-profile">
            <p>{{ follow.follower.nickname }}</p>
          </div>
        
        </li>
      </ul>
    </div>

    <!-- Followees -->
    <div class="friend-section">
      <h2>Followee</h2>
      <div v-if="!followStore.followList">
        <p>팔로잉 중인 사용자가 없습니다.</p>
      </div>
      <ul v-else class="following-list">
        <li v-for="follow in followStore.followList" :key="follow.num" class="following-item">
          <div class="following-content">
            <img src="@\assets\사람.png" alt="Followee Profile" class="following-profile">
            <p>{{ follow.followee.nickname }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { useFollowStore } from "@/stores/follow";
import { useUserStore } from "@/stores/user";
import { onMounted } from 'vue';

const followStore = useFollowStore();
const userStore = useUserStore();

onMounted(() => {
  followStore.getFolloweeList();
  followStore.getFollowerList();
});
</script>

<style scoped>
.friend-list {
  max-width: 800px;
  margin: 0 auto;
}

h1 {
  font-size: 24px;
  margin-bottom: 20px;
}

h2 {
  font-size: 18px;
  margin-top: 30px;
  margin-bottom: 15px;
}

ul {
  list-style: none;
  padding: 0;
}

.friend-section {
  margin-bottom: 40px;
}

.follower-list,
.following-list {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(100px, 1fr));
  gap: 15px;
}

.follower-item,
.following-item {
  text-align: center;
  padding: 10px;
  border-radius: 10px;
  background-color: #f5f5f5;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.follower-item p,
.following-item p {
  margin: 8px 0 0;
  font-size: 14px;
}

.follower-profile,
.following-profile {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  object-fit: cover;
  margin-bottom: 10px;
}
.template{
  
  font-family: 'IBM Plex Sans KR', sans-serif;

}
</style>
