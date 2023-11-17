<template>
    <div>
        <div>
            <h1>{{ userStore.loginUser.nickname }}님의 친구</h1>
            <h2>follower</h2>
            <div v-if="!followers">
                <p>팔로워가 없습니다.</p>
            </div>
            <div v-else v-for="follower in followers" :key="follower.num">
                <p>---------------</p>
                <p>{{ follower.followee.nickname }}</p>
                <p>---------------</p>
            </div>
            <h2>followee</h2>
            <div v-if="!followees">
                <p>팔로잉 중인 사용자가 없습니다.</p>
            </div>
            <div v-else v-for="followee in followees" :key="followee.num">
                <p>---------------</p>
                <p>{{ followee.follower.nickname }}</p>
                <p>---------------</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { onMounted, computed } from 'vue';

const userStore = useUserStore();
const followers = computed(() => userStore.follwerList);
const followees = computed(() => userStore.follweeList);

onMounted(() => {
    userStore.getUserFollow("follower");
    userStore.getUserFollow("followee");
});

</script>

<style scoped></style>