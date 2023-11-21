<template>
    <div class="reviews-page">
      <h2 class="page-title">이용 후기</h2>
      <RouterView />
    </div>
  </template>
<script setup>

</script>

<style scoped>
.reviews-page {
  max-width: 800px;
  margin: 20px auto;
  padding: 20px;
  background-color: #fff;
  border: 1px solid #e6e6e6;
  border-radius: 8px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
}

.page-title {
  font-size: 1.8rem;
  margin-bottom: 20px;
}

</style>