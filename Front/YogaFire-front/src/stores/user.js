import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

// axios.defaults.headers['Access-Control-Allow-Origin'] = 'http://localhost:8080';

// axios.defaults.withCredentials = true;

const REST_USER_API = `http://localhost:8080/user/signup`

export const useUserStore = defineStore('user', () => {

    const createAccount = function (user) {
        axios({
            url: REST_USER_API,
            method: 'POST',
            data: user
        })
            .then(() => {
                // 회원가입 하면 바로 로그인 페이지로 이동.
                router.push({ name: 'loginDetail' })
            })
            .catch((err) => {
                console.log(err)
            })
    }

    const loginUser = ref('');

    const login = function (credentials) {
        console.log(credentials.userId)
        axios({
            url: `http://localhost:8080/user/signin`,
            method: 'POST',
            data: credentials,
        })
        .then((response) => {
            console.log(response.data)
            if (response.data) {
                console.log('로그인 성공');
                loginUser.value = credentials.userId
                console.log(credentials.userId)
                sessionStorage.setItem("loggedInUser", JSON.stringify(loginUser.value))
                console.log(sessionStorage.getItem("loggedInUser"))
                router.push({ name: 'home' })
                } else {
                    console.error('로그인 실패');
                }
            })
            .catch((err) => {
                console.log("test error")
                console.error('로그인 실패:', err);
            })
    }

    const logout = function () {

        loginUser.value = ''

        axios({
            url: `http://localhost:8080/user/signout`,
            method: 'GET',
            
        })
            .then(() => {
                sessionStorage.removeItem('loggedInUser')
                router.push({ name: 'home' })
            })
            .catch((err) => {
                console.log(err)
            })
    }

    const user = ref({})
    return { createAccount, user, login, logout, loginUser }
},{persist:true});