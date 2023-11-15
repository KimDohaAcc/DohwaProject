import axios from 'axios'

const axios = require('axios');
const token = localStorage.getItem('jwtToken');

export default axios.create({
    headers: {
        'Access-Control-Allow-Origin' : '*',
        'Access-Control-Allow-Headers' : 'Content-Type',
        'Content-Type' : 'application/json',
        'Authorization' : `Bearer ${token}`,
    },

    baseURL: 'http://localhost:8080',
})
