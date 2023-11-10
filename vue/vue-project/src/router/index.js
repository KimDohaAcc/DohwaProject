import { createRouter, createWebHistory } from 'vue-router'
import MainView from '@/views/MainView.vue'
import BoardView from '@/views/BoardView.vue'
import UserView from '@/views/UserView.vue'


import BoardList from '@/components/board/BoardList.vue'
import BoardCreate from '@/components/board/BoardCreate.vue'
import BoardDetail from '@/components/board/BoardDetail.vue'
import BoardUpdate from '@/components/board/BoardUpdate.vue'

import Login from '@/components/user/Login.vue'

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
      path: "/user",
      component: UserView,
      // children: [
      //   {
      //     path: "regist",
      //     name: "Regist",
      //     component: UserRegist,
      //   },
      //   {
      //     path: "",
      //     name: "List",
      //     component: UserList,
      //   },
      //   {
      //     path: ":id",
      //     name: "Detail",
      //     component: UserDetail,
      //   },
      // ],
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
