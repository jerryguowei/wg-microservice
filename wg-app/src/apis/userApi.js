import axios from 'axios';

const userAxios = axios.create({
    baseURL: 'http://localhost:8080'
    
});

export const userApi= {
    userAxios
}