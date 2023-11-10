<template>
    <header>
        <div>
            <RouterLink to="/">HealthPanda</RouterLink>
        </div>
        <div>
            <a href="#" v-if="userStore.loginUser.email" @click="kakaoLogout">로그아웃</a>
            <RouterLink to="/login" v-else>로그인</RouterLink>
            <RouterLink to="/regist">회원가입</RouterLink>
            <RouterLink to="/board">이용 후기</RouterLink>
        </div>
    </header>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
const userStore = useUserStore();

const kakaoLogout = function(){
    userStore.logoutUser();
}


</script>

<!--<script>
// import { computed, defineEmits } from "vue";
// import {mapStores, mapActions} from 'pinia';
// import { useUserStore } from "@/stores/user";


// export default {
//     computed:{
//         ...mapStores(useUserStore)
//     },
//     setup() {
//         const emits = defineEmits(["logout"]);
//         // const getUser = computed(() => !!userStore.loginUser);


//         const logout = () => {
//             emits("logout");
//             kakaoLogout();
//         };



//     },

//     methods: {
//         ...mapActions(useUserStore, ['logoutUser']),
//         kakaoLogout() {
//             this.logoutUser();
//             window.Kakao.Auth.logout((res) => {
//                 console.log(res);
//             });
//         },
//     },
// };
// </script> -->

<style scoped></style>