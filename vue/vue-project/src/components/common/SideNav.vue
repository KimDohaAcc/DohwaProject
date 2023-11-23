<template>
    <div>
        <nav>
            <RouterLink class="link" to="/"><i class="bi bi-house"></i></RouterLink>
            <img class="kakao" @click="kakaoLink()" src="https://cnpmall.com/img/right_quick_icon05.png">
            <RouterLink class="link" :to="`/like/${userStore.loginUser ? userStore.loginUser.id : 0}`"><i class="bi bi-box2-heart"></i></RouterLink>
            <RouterLink class="link" to="/board/create"><i class="bi bi-pencil-square"></i></RouterLink>
        </nav>
    </div>
</template>
  
<script setup>
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();

function kakaoLink() {
    window.open("http://pf.kakao.com/_iZQTG");
}
</script>
<style scoped>
nav {
    display: flex;
    flex-flow: wrap column;
    justify-content: center;
    align-items: center;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 8px;
    width: 70px;
    position: fixed;
    top: 50%;
    right: 2%;
    transform: translateY(-50%);
    z-index: 1000;
    background-color: white;
}

.link, .kakao {
    text-decoration: none;
    color: black;
    padding: 8px;
    display: block;
    margin-bottom: 5px;
    border-radius: 5px;
    transition: background-color 0.3s;
}

nav>a {
    border-bottom: 5px solid #ddd;
}

.kakao {
    width: 45px;
    height: auto;
    cursor: pointer;
    border-bottom: 1px solid #ddd;
}

link:hover {
    background-color: #555;
    /* Change background color on hover */
}</style>