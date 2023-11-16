<template>
    <div>
        <div>
            <h1>{{ userStore.loginUser.nickname }}님의 식단 기록</h1>
            <div v-for="meal in userMeal" :key="meal.num">
                <p>---------------</p>
                <p>{{ meal.time }}</p>
                <p>{{ meal.food }}</p>
                <p>{{ meal.kcal }}kcal</p>
                <p>---------------</p>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref, onMounted, computed } from 'vue';

const userStore = useUserStore();
const userMeal = computed(() => userStore.mealList);

onMounted(() => {
    userStore.getUserMeal();
});

</script>

<style scoped></style>