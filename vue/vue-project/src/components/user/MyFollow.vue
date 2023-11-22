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
          <div class="dropdown">
            <p @click="toggleDropdown(follow.num)">{{ follow.follower.nickname }}</p>
            <div v-if="follow.dropdownOpen" class="dropdown-content">
              <RouterLink :to="`/like/${follow.follower.id}`">좋아하는 비디오</RouterLink>
              <RouterLink :to="`/board/write/${follow.follower.id}`">작성한 글</RouterLink>
            </div>
          </div>
        </li>
      </ul>
    </div>
    <div>
      <h2>Followee</h2>
      <div v-if="!followStore.followList">
        <span>팔로잉 중인 사용자가 없습니다.</span>
      </div>
      <ul v-else class="following-list">
        <li v-for="follow in followStore.followList" :key="follow.num" class="following-item">
          <div class="dropdown">
            <p @click="toggleDropdown(follow.num)">{{ follow.followee.nickname }}</p>
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
  padding: 10px 30px;
  width: max-content;
  text-align: center;
  border-radius: 5px;
}

.follower-item p,
.following-item p {
  margin: 0;
  font-size: 1em;
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