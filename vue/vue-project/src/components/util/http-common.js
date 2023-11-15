import axios from 'axios'

const axios = require('axios');

export default axios.create({
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Headers' : 'Content-Type',
        'Content-Type' : 'application/json',
    },

    baseURL: 'http://localhost:8080',
})
