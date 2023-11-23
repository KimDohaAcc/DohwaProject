<template>
    <div class="friend-list">
      <h1>{{ userStore.loginUser.nickname }}님의 친구</h1>
      <div class="friend-section">
        <h2>Follower</h2>
        <div v-if="!followStore.followerList">
          <p>팔로워가 없습니다.</p>
        </div>
        <ul v-else class="follower-list">
          <li v-for="follow in followStore.followerList" :key="follow.num" class="follower-item">
            <div class="dropdown">
              <div  @click="toggleDropdown(follow.num)" class="follower-content">
                <img src="@\assets\사람.png" alt="Follower Profile" class="follower-profile">
                <p>{{ follow.follower.nickname }}</p>
              </div>
              <div v-if="follow.dropdownOpen" class="dropdown-content">
                <RouterLink :to="`/like/${follow.follower.id}`">좋아하는 비디오</RouterLink>
                <RouterLink :to="`/board/write/${follow.follower.id}`">작성한 글</RouterLink>
              </div>
            </div>
          </li>
        </ul>
      </div>
      <div class="friend-section">
        <h2>Followee</h2>
        <div v-if="!followStore.followList">
          <span>팔로잉 중인 사용자가 없습니다.</span>
        </div>
        <ul v-else class="following-list">
          <li v-for="follow in followStore.followList" :key="follow.num" class="following-item">
            <div class="dropdown">
              <div class="following-content" @click="toggleDropdown(follow.num)">
                <img src="@\assets\사람.png" alt="Followee Profile" class="following-profile">
                <p>{{ follow.followee.nickname }}</p>
              </div>
              <div v-if="follow.dropdownOpen" class="dropdown-content">
                <RouterLink :to="`/like/${follow.followee.id}`">좋아하는 비디오</RouterLink>
                <RouterLink :to="`/board/write/${follow.followee.id}`">작성한 글</RouterLink>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </template>

<script setup>
import { useFollowStore } from "@/stores/follow";
import { useUserStore } from "@/stores/user";
import { onMounted, reactive } from 'vue';

const followStore = useFollowStore();
const userStore = useUserStore();

onMounted(() => {
  followStore.getFolloweeList();
  followStore.getFollowerList();
});

const toggleDropdown = (followNum) => {
  if(followStore.followerList){
    const follower = followStore.followerList.find(follow => follow.num === followNum);
    if (follower) {
      follower.dropdownOpen = !follower.dropdownOpen;
    }
  }

  if(followStore.followList){
    const followee = followStore.followList.find(follow => follow.num === followNum);
    if (followee) {
      followee.dropdownOpen = !followee.dropdownOpen;
    }
  }
};
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

.dropdown {
  position: relative;
  display: inline-block;
  cursor: pointer;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 140px;
  box-shadow: 0 4px 7px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  cursor: pointer;
  border-bottom: solid 1px #ddd;
}

.dropdown:hover .dropdown-content {
  display: block;
}
</style>
