
import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from "axios"

import "bootstrap-icons/font/bootstrap-icons.css";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import BootstrapVue3 from 'bootstrap-vue-3'

const app = createApp(App)

app.config.globalProperties.$axios = axios;

app.use(createPinia())
app.use(router)
app.use(BootstrapVue3)

window.Kakao.init('41f86733b132d1fba2949b424d9de05e')

app.mount('#app')
