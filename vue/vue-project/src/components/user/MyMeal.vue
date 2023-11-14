<template>
    <div>
        <div>
            <h1 v-if="userMeal">기록한 식단이 없습니다</h1>
            <h1 v-else>{{ userStore.loginUser.nickname }}님의 식단 기록</h1>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";


const userStore = useUserStore();
const userMeal = userStore.getUserMeal();
</script>

<style scoped></style>