<template>
    <div>
        <fieldset class="custom-fieldset">
            <div class="fieldset-content">
                <RouterLink to="/" class="goHome"><h4>LOG IN</h4></RouterLink>
                <div class = "text-input">
                    <div class="text-align">
                        <!-- <label for="userId">아이디 : </label> -->
                        <input class="insert" type="text" id="userId" v-model="user.userId" @focus="addFocusClass" @blur="removeFocusClass" placeholder="ID">
                    </div>
                    <br>
                    <div class="text-align">
                        <!-- <label for="password">비밀번호 : </label> -->
                        <input class="insert" type="password" id="password" v-model="user.password" @keyup.enter="login" placeholder="PW">
                    </div>
                    <br>
                    <div class="text-align">
                        <button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" @click="login">LOG IN</button>
                        <!-- <button @click="findId">아이디 찾기</button> -->
                    </div>
                </div>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();
const store = useUserStore();

const user = ref({
    userId: '',
    password: '',
})

const login = function() {
        console.log(user.value)
        store.login(user.value)
}

const addFocusClass = function(event) {
    event.target.classList.add('focused');
}

const removeFocusClass = function(event) {
    event.target.classList.remove('focused');
}

</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap");

/* fieldset의 기본 패딩을 없앱니다 */
.custom-fieldset {
    padding-top: 200px;
}

.insert.focused {
  border-color: #ff7c7c;
}

.fieldset-content {
  border: 2px solid #ff7c7c;
  border-radius: 10px;
  background-color: #f9f9f9;
  padding-top: 60px;
  padding-bottom: 35px;
  width: 65%; /* 원하는 너비(예: 80%)로 조정합니다 */
  margin: 0 auto; /* 가운데 정렬을 위해 좌우 마진을 auto로 설정합니다 */

}

.insert{
    width: 500px;
    height: 50px;
    border: none;
    background-color: rgba(230, 221, 221, 0.891);
    text-align: center; 
}

fieldset{
    padding-top: 300px;
    padding-left : 300px;
    padding-right: 300px;
    border: none; 
}

.goHome, h4, .a{
    text-align: center;
    font-size:60px;
    color:#ff7c7c ;
    font-weight: bold;
    text-decoration: none;
    padding-bottom: 15px;
}

.text-align{
    text-align: center;
}

.text-input{
    padding-top: 17px;
}

/* 버튼 디자인 */

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    text-align: center;
    padding: 100px;
    background: whitesmoke;
    display: table-cell;
}

button {
    margin: 20px;
}

.input-box{
    width: 500px;
    height: 46px;
    margin-left: 20px;
    border-color: gray;
}

.w-btn {
    height: 50px;
    width: 98px;
    position: relative;
    border: none;
    display: inline-block;
    /* padding: 15px 30px; */
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
    font-size: 18px;
}

.w-btn-gra2 {
    background: linear-gradient(-45deg, #f58969, #e45c90, #f27b66, #ff2c2c);
    color: white;
}

.w-btn-gra-anim {
    background-size: 400% 400%;
    animation: gradient1 5s ease infinite;
}

@keyframes gradient1 {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}

/* 버튼 끝 */

search-bar {
  width: 220px;
  height: 27px;
  border-radius: 5px;
  border: solid 1px rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
  opacity: 1;
}
.search-bar__input {
  width: 400px;
  height: 45px;
  /* border: none; */
  -webkit-appearance: none;
  text-align: center;
  margin-left: 10px;
  overflow: auto;
  z-index: -1;
  font-size: 15px;
}

.fa-search {
  font-size: 15px;
}

input::placeholder {
    text-align: left;
    padding-left: 20px; /* 원하는 만큼의 왼쪽 간격 조정 */
}


</style>