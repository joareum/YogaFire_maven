<template>
  <div class="signup-container">
    <fieldset class="text-center">
      <div class="fieldset-content">
        <RouterLink to="/" class="goHome"><h4>WELCOME TO JOIN US</h4></RouterLink>
        <!-- <label for="user_id">ID</label> -->
        <input type="text" id="user_id" v-model="user_id" class="view" placeholder="ID" /><br />

        <!-- <label for="password">PW</label> -->
        <input type="password" id="password" v-model="password" class="view" placeholder="PW" /><br />

        <!-- <label for="name">이름</label> -->
        <input type="text" id="name" v-model="name" class="view" placeholder="NAME" /><br />

        <!-- <label for="email">이메일</label> -->
        <input type="email" id="email" v-model="email" class="view" placeholder="EMAIL" /><br />

        <!-- <label for="birth">생일</label> -->
        <!-- <div class="view" id="info__birth"> -->
        <select class="box" id="birth-year" v-model="selectedYear" @focus="generateYears" placeholder="YEAR">
          <option disabled selected hidden>출생 연도</option>
          <option value="" class="first" selected>  YEAR</option>
          <option v-for="year in birthYears" :key="year" :value="year">{{ year }}</option>
        </select>
        <select class="box" id="birth-month" v-model="selectedMonth" @focus="generateMonths">
          <option value="" class="first" selected>  MONTH</option>
          <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
        </select>
        <select class="box" id="birth-day" v-model="selectedDay" @focus="generateDays">
          <option value="" class="first" selected>  DAY</option>
          <!-- <option disabled selected>일</option> -->
          <option v-for="day in days" :key="day" :value="day">{{ day }}</option>
        </select>
        <!-- </div> -->
        <br />

        <!-- <label for="age">나이</label> -->
        <input type="age" id="age" v-model="age" class="view-age" placeholder="AGE" />

        <!-- <label for="sex">성별</label> -->
        <!-- <input type="sex" id="sex" v-model="sex" class="view-gender" placeholder="GENDER" /><br /> -->
        <select class="gender" id="gender" v-model="sex" @focus="generateGender">
          <option value="" class="first" selected>  GENDER</option>
          <option value="1">남</option>
          <option value="2">여</option>
        </select>

        <!-- <label for="phone">핸드폰 번호</label> -->
        <input type="phone" id="phone" v-model="phone" class="view" placeholder="PHONE" /><br />

        <!-- <label for="nickname">닉네임</label> -->
        <input type="nickname" id="nickname" v-model="nickname" class="view" placeholder="NICKNAME" /><br />

        <!-- <label for="height">키</label> -->
        <input type="height" id="height" v-model="height" class="view" placeholder="HEIGHT" /><br />

        <!-- <label for="weight">몸무게</label> -->
        <input type="weight" id="weight" v-model="weight" class="view" placeholder="WEIGHT" /><br />


        <button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" @click="createAccount">🔥</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const userStore = useUserStore();

const user_id = ref("");
const password = ref("");
const name = ref("");

const birthYears = ref([]);
const months = ref([]);
const days = ref([]);
const selectedYear = ref(null);
const selectedMonth = ref(null);
const selectedDay = ref(null);

const age = ref("");
const email = ref("");
const phone = ref("");
const nickname = ref("");
const height = ref("");
const weight = ref("");
const sex = ref("");

const generateYears = () => {
  if (birthYears.value.length === 0) {
    for (let i = 1940; i <= 2022; i++) {
      birthYears.value.push(i);
    }
  }
};

const generateMonths = () => {
  if (months.value.length === 0) {
    for (let i = 1; i <= 12; i++) {
      months.value.push(i);
    }
  }
};

const generateDays = () => {
  if (days.value.length === 0) {
    for (let i = 1; i <= 31; i++) {
      days.value.push(i);
    }
  }
};

const createAccount = () => {
  if (!selectedYear.value || !selectedMonth.value || !selectedDay.value) {
    alert('생년월일을 모두 선택해주세요.');
    return;
  }

  let birthDate = `${selectedYear.value}-${String(selectedMonth.value).padStart(2, '0')}-${String(selectedDay.value).padStart(2, '0')}`;

  let user = {
    userId: user_id.value,
    password: password.value,
    name: name.value,
    birthday: birthDate,
    age: age.value,
    email: email.value,
    phone: phone.value,
    nickname: nickname.value,
    height: height.value,
    weight: weight.value,
    sex: sex.value,
  };

  console.log("usertest : " + user.userId)

  userStore.createAccount(user);
};
</script>

<style scoped>
body, html {
  margin: 0;
  padding: 0;
  border: none;
}
fieldset {
  border: none; /* 테두리 없애기 */
  padding-top: 50px;
}
.first{
  text-align: left;
  margin-left: 100px !important;
  display: inline-block;
}

#birth-year {
  padding-right: -50px;
  margin-left: 10px;
  width: 130px;
}

.box {
  width: 195px;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
  margin-right: 10px;
  margin-bottom: 10px;
  color: #555;
  /* 텍스트 색상 회색으로 변경 */
  border: 50px;
}

.gender{
  width: 24%;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
  margin-right: 10px;
  margin-bottom: 10px;
  color: #555;
  /* 텍스트 색상 회색으로 변경 */
  border: 50px;
}

.gender {
  width: 30.7%;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
  margin-bottom: 10px;
  margin-right: 10px;
}

.box:focus {
  outline: none;
  /* 포커스 시 아웃라인 제거 */
}

.box::placeholder {
  color: #555;
  /* 플레이스홀더 색상 회색으로 변경 */
}

.text-center {
  text-align: center;
  padding-top: 75px;
}

label {
  display: inline-block;
  width: 130px;
}

.view {
  width: 65%;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
  margin-bottom: 10px;
}

.view-age {
  width: 33%;
  margin-left: 10px;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
  margin-bottom: 10px;
  margin-right: 10px;
}

/* SECTION - BIRTH */
.info#info__birth {
  display: flex;
}

.info#info__birth select {
  margin-left: 7px;
}

.info#info__birth select:first-child {
  margin-left: 0px;
}

.info#info__birth select::-webkit-scrollbar {
  width: 10px;
}

.info#info__birth select::-webkit-scrollbar-thumb {
  background-color: #b6b6b6;
  border-radius: 3px;
}

.info#info__birth select::-webkit-scrollbar-track {
  background-color: #ebe9e9;
  border-radius: 6px;
}

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
  padding-top: 30px;
  padding-bottom: 35px;
  width: 50%;
  margin: 0 auto;
}

.insert {
  width: 70%;
  height: 40px;
  border: none;
  background-color: rgba(230, 221, 221, 0.891);
  text-align: center;
}

.goHome, h4, .a {
  text-align: center;
  font-size: 50px;
  color: #ff7c7c;
  font-weight: bold;
  text-decoration: none;
}

h4{
  margin-bottom: 15px;
  margin-top:25px
}

.text-align {
  text-align: center;
}

.text-input {
  padding-top: 17px;
}

button {
  size: 200%;
  height: 50px;
  width: 98px;
  position: relative;
  border: none;
  display: inline-block;
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  /* box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2); */
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  font-size: 33px;
  background: transparent;
  /* background: linear-gradient(-45deg, #f58969, #e45c90, #f27b66, #ff2c2c); */
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

input::placeholder {
  text-align: left;
  padding-left: 20px;
  /* 원하는 만큼의 왼쪽 간격 조정 */
}</style>
