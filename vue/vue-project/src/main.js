import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from "axios"
import * as bootstrap from 'bootstrap'

import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

const app = createApp(App)

app.config.globalProperties.$axios = axios;

app.use(createPinia())
app.use(router)
app.use(BootstrapVue3)

window.Kakao.init('41f86733b132d1fba2949b424d9de05e')
// console.log(Kakao.isInitialized());

app.mount('#app')
