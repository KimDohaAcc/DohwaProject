<template>
    <div>
            <h1>{{ userStore.loginUser.nickname }}님의 친구</h1>
            <h2>follower</h2>
            <div v-if="!followStore.followerList">
                <p>팔로워가 없습니다.</p>
            </div>
            <ul v-else>
                <li v-for="follow in followStore.followerList" :key="follow.num">
                    <p>---------------</p>
                    <p>{{ follow.follower.nickname }}</p>
                    <p>---------------</p>
                </li>
            </ul>
            <h2>followee</h2>
            <div v-if="!followStore.followList">
                <p>팔로잉 중인 사용자가 없습니다.</p>
            </div>
            <ul v-else>
            <li v-for="follow in followStore.followList" :key="follow.num">
                <p>---------------</p>
                <p>{{ follow.followee.nickname }}</p>
                <p>---------------</p>
            </li>
        </ul>
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

<style scoped></style>