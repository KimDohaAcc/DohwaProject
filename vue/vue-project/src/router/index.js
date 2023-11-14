import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import BoardView from '@/views/BoardView.vue'


import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

import Login from '@/components/user/Login.vue'
import MyPage from '@/components/user/MyPage.vue'
import MyMeal from '@/components/user/MyMeal.vue'
import Regist from '@/components/user/Regist.vue'


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
    },
    {
      path: "/myPage",
      name: "myPage",
      component: MyPage,
    },
    {
      path: "/myMeal",
      name: "myMeal",
      component: MyMeal,
    },
    {
      path: "/regist",
      name: "regist",
      component: Regist,
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
          component: BoardCreate
        },
        {
          path: ":id",
          name: "boardDetail",
          component: BoardDetail
        },
        {
          path: "update",
          name: "boardUpdate",
          component: BoardUpdate
        },
      ]
    },
  ]
})

export default router
