import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

// axios.defaults.headers['Access-Control-Allow-Origin'] = 'http://localhost:8080';
// axios.defaults.withCredentials = true;

const REST_USER_API = `http://localhost:8080/user/signup`

export const useUserStore = defineStore('user', () => {

    const calculatedKcal = ref(0);
    const createAccount = function (user) {
        axios({
            url: REST_USER_API,
            method: 'POST',
            data: user
        })
            .then(() => {
                alert('회원가입에 성공했습니다.')
                // 회원가입 하면 바로 로그인 페이지로 이동.
                router.push({ name: 'loginDetail' })
            })
            .catch((err) => {
                console.log(err)
                if (err.response.status === 500) {
                    console.log('500 에러')
                    alert('중복체크를 해주세요.');
                }else if(err.response.status === 400){
                    alert('형식에 맞지 않는 값을 입력했습니다.');
                }
            })
    }

    const loginUser = ref('');

    const login = function (credentials) {
        axios({
            url: `http://localhost:8080/user/signin`,
            method: 'POST',
            data: credentials,
        })
        .then((response) => {
            console.log(response.data)
            if (response.status === 200) {
                console.log('로그인 성공');
                loginUser.value = credentials.userId
                // console.log(credentials.userId)
                sessionStorage.setItem("loggedInUser", JSON.stringify(loginUser.value))
                // console.log(sessionStorage.getItem("loggedInUser"))
                router.push({ name: 'home' })
            }
        })
        .catch((error) => {
            console.error('로그인 실패:', error);
            if (error.response.status === 400) {
                alert('아이디나 비밀번호를 입력해주세요.');
            } else if (error.response.status === 401) {
                console.log('401 에러')
                alert('아이디나 비밀번호가 잘못되었습니다.');
            } 
        });
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
    return { createAccount, user, login, logout, loginUser, calculatedKcal }
}, { persist: true });