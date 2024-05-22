<template>
  <div class="signup-container">
    <fieldset class="text-center">
      <div class="fieldset-content">
        <RouterLink to="/" class="goHome">
          <h4>WELCOME TO JOIN US</h4>
        </RouterLink>
        <input type="text" id="user_id" v-model="user_id" class="view" @input="checkDuplicateUserId" placeholder="ID" />
        <br>
        <span v-if="duplicateUserId === true">이미 존재하는 사용자 ID입니다.</span>
        <span v-if="duplicateUserId === null">아이디 중복체크가 필요합니다.</span>
        <span v-if="duplicateUserId === false">사용할 수 있는 ID입니다.</span>
        <br />

        <input type="password" id="password" v-model="password" class="view" placeholder="PW" /><br />

        <input type="text" id="name" v-model="name" class="view" placeholder="NAME" /><br />

        <input type="email" id="email" v-model="email" class="view"  @input="checkDuplicateUserEmail" placeholder="EMAIL" />
        <br>
        <span v-if="duplicateUserEmail === true">이미 존재하는 사용자 EMAIL입니다.</span>
        <span v-if="duplicateUserEmail === null">EMAIL 중복체크가 필요합니다.</span>
        <span v-if="duplicateUserEmail === false">사용할 수 있는 EMAIL입니다.</span>
        <br />

        <select class="box" id="birth-year" v-model="selectedYear" @focus="generateYears" placeholder="YEAR">
          <option disabled selected hidden>출생 연도</option>
          <option value="" class="first" selected> YEAR</option>
          <option v-for="year in birthYears" :key="year" :value="year">{{ year }}</option>
        </select>
        <select class="box" id="birth-month" v-model="selectedMonth" @focus="generateMonths">
          <option value="" class="first" selected> MONTH</option>
          <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
        </select>
        <select class="box" id="birth-day" v-model="selectedDay" @focus="generateDays">
          <option value="" class="first" selected> DAY</option>
          <option v-for="day in days" :key="day" :value="day">{{ day }}</option>
        </select>
        <br />

        <input type="age" id="age" v-model="age" class="view-age" placeholder="AGE" />

        <select class="gender" id="gender" v-model="sex" @focus="generateGender">
          <option value="" class="first" selected> GENDER</option>
          <option value="1">남</option>
          <option value="2">여</option>
        </select>

        <input type="phone" id="phone" v-model="phone" class="view" placeholder="PHONE" /><br />

        <input type="nickname" id="nickname" v-model="nickname" class="view" @input="checkDuplicateUserNickname" placeholder="NICKNAME" />
        <br>
        <span v-if="duplicateUserNickname === true">이미 존재하는 사용자 닉네임입니다.</span>
        <span v-if="duplicateUserNickname === null">닉네임 중복체크가 필요합니다.</span>
        <span v-if="duplicateUserNickname === false">사용할 수 있는 닉네임입니다.</span>
        <br />

        <input type="height" id="height" v-model="height" class="view" placeholder="HEIGHT" /><br />

        <input type="weight" id="weight" v-model="weight" class="view" placeholder="WEIGHT" /><br />


        <button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" @click="createAccount">🔥</button>
      </div>
    </fieldset>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import axios from 'axios';

const userStore = useUserStore();

const user_id = ref("");
const password = ref("");
const name = ref("");
const duplicateUserId = ref(null);
const duplicateUserEmail = ref(null);
const duplicateUserNickname = ref(null);

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
  } else if(duplicateUserId === null || duplicateUserEmail === null || duplicateUserNickname === null ){
    alert('아이디 혹은 이메일 혹은 닉네임 중복 체크를 해주세요')
    return;
  }else if(duplicateUserId === true || duplicateUserEmail === true || duplicateUserNickname === true ){
    alert('아이디 혹은 이메일 혹은 닉네임이 이미 존재합니다. 중복 체크를 해주세요')
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

// 아이디 중복 체크 함수
const checkDuplicateUserId = async () => {
  if (user_id.value) {
    try {
      const response = await axios.get(`http://localhost:8080/user/checkDuplicateUserId/${user_id.value}`);
      duplicateUserId.value = response.data; // 서버에서 중복 여부를 boolean 값으로 반환
    } catch (error) {
      console.error("아이디 중복 확인 중 오류 발생:", error);
    }
  } else {
  duplicateUserId.value = null; // 입력 필드가 비어 있을 경우
}
};

// 이메일 중복 체크 함수
const checkDuplicateUserEmail = async () => {
  if (email.value) {
    try {
      const response = await axios.get(`http://localhost:8080/user/checkDuplicateUserEmail/${email.value}`);
      duplicateUserEmail.value = response.data; // 서버에서 중복 여부를 boolean 값으로 반환
    } catch (error) {
      console.error("이메일 중복 확인 중 오류 발생:", error);
    }
  } else {
    duplicateUserEmail.value = null; // 입력 필드가 비어 있을 경우
}
};

// 닉네임 중복 체크 함수
const checkDuplicateUserNickname = async () => {
  if (nickname.value) {
    try {
      const response = await axios.get(`http://localhost:8080/user/checkDuplicateUserNickname/${nickname.value}`);
      duplicateUserNickname.value = response.data; // 서버에서 중복 여부를 boolean 값으로 반환
    } catch (error) {
      console.error("이메일 중복 확인 중 오류 발생:", error);
    }
  } else {
    duplicateUserNickname.value = null; // 입력 필드가 비어 있을 경우
}
};




</script>

<style scoped>
body,
html {
  margin: 0;
  padding: 0;
  border: none;
}

fieldset {
  border: none;
  /* 테두리 없애기 */
  padding-top: 50px;
}

.first {
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

.gender {
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

.goHome,
h4,
.a {
  text-align: center;
  font-size: 50px;
  color: #ff7c7c;
  font-weight: bold;
  text-decoration: none;
}

h4 {
  margin-bottom: 15px;
  margin-top: 25px
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
