<template>
  <div class="container">
    <h2>사용자 등록</h2>
    <fieldset class="text-center">

      <label for="user_id">ID</label>
      <input type="text" id="user_id" v-model="user_id" class="view" /><br />

      <label for="password">PW</label>
      <input type="password" id="password" v-model="password" class="view" /><br />

      <label for="name">이름</label>
      <input type="text" id="name" v-model="name" class="view" /><br />

      <label for="birth">생일</label>
      <div class="view" id="info__birth">
        <select class="box" id="birth-year" v-model="selectedYear" @focus="generateYears">
          <option disabled selected>출생 연도</option>
          <option v-for="year in birthYears" :key="year" :value="year">{{ year }}</option>
        </select>
        <select class="box" id="birth-month" v-model="selectedMonth" @focus="generateMonths">
          <option disabled selected>월</option>
          <option v-for="month in months" :key="month" :value="month">{{ month }}</option>
        </select>
        <select class="box" id="birth-day" v-model="selectedDay" @focus="generateDays">
          <option disabled selected>일</option>
          <option v-for="day in days" :key="day" :value="day">{{ day }}</option>
        </select>
      </div>

      <label for="age">나이</label>
      <input type="age" id="age" v-model="age" class="view" /><br />

      <label for="email">이메일</label>
      <input type="email" id="email" v-model="email" class="view" /><br />

      <label for="phone">핸드폰 번호</label>
      <input type="phone" id="phone" v-model="phone" class="view" /><br />

      <label for="nickname">닉네임</label>
      <input type="nickname" id="nickname" v-model="nickname" class="view" /><br />

      <label for="height">키</label>
      <input type="height" id="height" v-model="height" class="view" /><br />

      <label for="weight">몸무게</label>
      <input type="weight" id="weight" v-model="weight" class="view" /><br />

      <label for="sex">성별</label>
      <input type="sex" id="sex" v-model="sex" class="view" /><br />

      <button class="btn" @click="createAccount">등록</button>
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

<style>
/* content CSS */

.container {
  margin: 0px 30px;
}

label {
  display: inline-block;
  width: 130px;
}

.text-center {
  text-align: center;
}

.btn {
  width: 8%;
  background-color: #d0d3d0;
  color: rgb(80, 82, 79);
  padding: 14px 20px;
  margin: 8px 0;
  border: 1px solid #787878;
  border-radius: 4px;
  font-size: large;
  cursor: pointer;
}

/* SECTION - BIRTH */
.info#info__birth {
  display: flex;
}

.info#info__birth select {
  margin-left : 7px;
}

.info#info__birth select:first-child {
  margin-left : 0px;
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
</style>
