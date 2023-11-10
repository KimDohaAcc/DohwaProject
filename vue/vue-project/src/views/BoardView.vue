<template>
    <div>
        <h2>이곳은 보드입니다</h2>
        <RouterLink :to="{ name: 'boardCreate' }">보드 작성</RouterLink>
        <RouterView/>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>