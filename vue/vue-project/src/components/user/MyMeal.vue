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
import { ref, onMounted } from 'vue';

const userStore = useUserStore();
const userMeal = ref(null);

onMounted(() => {
    userStore.getUserMeal();
    userMeal.value = userStore.mealList;
});

</script>

<style scoped></style>