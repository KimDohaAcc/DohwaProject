import { createRouter, createWebHistory } from 'vue-router'
import { computed } from 'vue'

import MainView from '@/views/MainView.vue'
import BoardView from '@/views/BoardView.vue'
import VideoView from '@/views/VideoView.vue'


import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

import Login from '@/components/user/Login.vue'
import MyPage from '@/components/user/MyPage.vue'
import MyFollow from '@/components/user/MyFollow.vue'
import Regist from '@/components/user/Regist.vue'

const checkLogin = (to, from, next) => {
  const isLoggedIn = computed(() => !!JSON.parse(sessionStorage.getItem('loginUser')));

  if (!isLoggedIn.value) {
    next('/login');
  } else {
    next();
  }
};

const checkLogout = (to, from, next) => {
  const isLoggedIn = computed(() => !!JSON.parse(sessionStorage.getItem('loginUser')));

  if (isLoggedIn.value) {
    next('/');
  } else {
    next();
  }
};

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'main',
      component: MainView,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
      beforeEnter: checkLogout,
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPage,
      beforeEnter: checkLogin,
    },
    {
      path: "/myFollow",
      name: "myFollow",
      component: MyFollow,
      beforeEnter: checkLogin,
    },
    {
      path: "/regist",
      name: "regist",
      component: Regist,
      beforeEnter: checkLogout,
    },
    {
      path: "/video",
      name: "video",
      component: VideoView,
    },
    {
      path: '/board',
      name: 'board',
      component: BoardView,
      children: [
        {
          path: "",
          name: "boardList",
          component: BoardList
        },
        {
          path: "create",
          name: "boardCreate",
          component: BoardCreate,
          beforeEnter: checkLogin,
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate,
          beforeEnter: checkLogin,
        },
      ]
    },
  ]
})

export default router
