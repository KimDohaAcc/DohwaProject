import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import axios from "axios"

const app = createApp(App)

app.config.globalProperties.$axios = axios;

app.use(createPinia())
app.use(router)

window.Kakao.init('41f86733b132d1fba2949b424d9de05e')
// console.log(Kakao.isInitialized());

app.mount('#app')
