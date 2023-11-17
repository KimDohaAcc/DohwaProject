<template>
    <div>
        <h2>이용 후기</h2>
        <RouterView/>
        <button><RouterLink :to="{ name: 'boardCreate' }">보드 작성</RouterLink></button>
    </div>
</template>

<script setup>

</script>

<style scoped>

</style>