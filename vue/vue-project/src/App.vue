
<template>
  <div id="outer-container">
    <div id="front-container">
      <SideNav class="SideNav" />
      <div class="content-wrapper">
        <HeaderNav class="HeaderNav" />
        <main>
          <RouterView class="RouterView" />
        </main>
        <FooterView />
      </div>
    </div>
  </div>
</template>

<script setup>
import HeaderNav from '@/components/common/HeaderNav.vue'
import SideNav from '@/components/common/SideNav.vue'
import { RouterView } from 'vue-router'
import FooterView from '@/views/FooterView.vue';
</script>


<style>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR:wght@100;200;300;400;500;600&display=swap');

* {
  font-family: 'IBM Plex Sans KR', sans-serif;
}

#front-container {
  background-color: white;
  /* 내용 부분 배경색 */
  display: flex;
  flex-flow: column;
  margin: 0 5%;
  width: 80vw;




}

#outer-container {
  display: flex;
  justify-content: center;
  background-color: #1A2A5F;
  /* 전체 배경색 */
  padding: 0 5%;

}



main>div {
  min-height: 80vh;
}</style>