import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  transpileDependencies: true,
  filenameHashing: false, // build 파일 해싱 제거
  productionSourceMap: true, // js source map 생성
  css: {sourceMap: true} ,
  devServer: {
    proxy: 'http://localhost:8080' //서버 프로젝트 포트번호와 동일할 것
  },
})
