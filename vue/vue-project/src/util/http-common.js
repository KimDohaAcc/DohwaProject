import axios from 'axios';

const axiosInstance = axios.create({
  headers: {
    'Content-Type': 'application/json',
  },
});

const axiosInstanceWithToken = axios.create({
  headers: {
    'Content-Type': 'application/json',
  },
});

axiosInstanceWithToken.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem('jwtToken');
    console.log(token);
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export { axiosInstance, axiosInstanceWithToken };