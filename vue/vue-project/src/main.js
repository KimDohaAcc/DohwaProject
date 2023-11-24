import { createApp } from 'vue';
import { createPinia } from 'pinia';
import App from './App.vue';
import router from './router';
import axios from "axios";

import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css';
import BootstrapVue3 from 'bootstrap-vue-3';
import { axiosInstance } from '@/util/http-common'

const lApi = "http://localhost:8080";
const dApi = "https://healthpanda.site";
const app = createApp(App);

app.config.globalProperties.$axios = axios;
const pinia = createPinia();
app.use(pinia);
app.use(router);
app.use(BootstrapVue3);

const checkTokenValidity = () => {
  const token = sessionStorage.getItem('jwtToken');

  if (token) {
    axiosInstance.get(`${dApi}/token-check`, {
      params: {
        token: token
      }
    })
      .then(res => {
      })
      .catch(error => {
        sessionStorage.removeItem('jwtToken');
        sessionStorage.removeItem('loginUser');

      });
  }
};

let intervalId;

router.beforeEach((to, from, next) => {
  checkTokenValidity();
  intervalId = setInterval(checkTokenValidity, 60000); // 1분 간격으로 호출
  next();
});

router.afterEach(() => {
  clearInterval(intervalId);
});


window.Kakao.init('41f86733b132d1fba2949b424d9de05e');

app.mount('#app');
