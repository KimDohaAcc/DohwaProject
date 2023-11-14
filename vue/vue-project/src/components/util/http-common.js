import axios from 'axios'

// 구현한 spring boot 서버에 맞게 수정합니다
export default axios.create({
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Headers' : 'Content-Type',
        'Content-Type' : 'application/json',
    },

    baseURL: 'http://localhost:8080',
})
