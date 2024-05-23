<template>
<div>
  <a href="http://localhost:5173" class="logo-container">
        <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
      </a>
</div>

<div class="text-center">
</div>

<div>
  <div class="chatbot-container" v-if="isChatbotVisible">
    <button @click="getGPTResponse">당신의 적정 칼로리인 {{ kcal }} kcal 에 맞는 식단을 GPT가 알려줄 거예요!</button>
    <div class="chatbot-message">
      <div v-html="formattedResponse"></div>
    </div>
  </div>
  <div>
    <img class="chat-img" src="@/assets/yogafire_pinkC.png" style="max-width: 55px; height: auto;" @click="toggleChatbotVisibility">
  </div>
</div>
</template>

<script setup>
import { ref, watchEffect, computed } from 'vue'
import OpenAI from 'openai'

import { useUserStore } from '@/stores/user.js';

import AnotherNav from '@/components/common/AnotherNav.vue'


const userStore = useUserStore();

const kcal = ref(0); // kcal 값을 저장할 ref 변수 선언

// kcal 값이 변경될 때마다 실행되는 비동기 함수
watchEffect(async () => {
kcal.value = userStore.calculatedKcal; // 비동기적으로 kcal 값을 가져옴
});

const response = ref('')
const isChatbotVisible = ref(false) // 채팅봇 표시 여부를 저장할 ref 변수 선언


const getGPTResponse = async () => {
try {
  const openai = new OpenAI({
    apiKey: `${import.meta.env.VITE_OPENAI_API_KEY}`,
    dangerouslyAllowBrowser: true,
  })

  const prompt = `일일 권장 칼로리가 ${kcal.value} kcal인 사람을 위한 식단을 깔끔하게 추천해줘. 답변의 첫 문장은 일일 권장 칼로리가 ${kcal.value} kcal인 사람을 위한 식단을 추천해드리겠습니다. 라고 해줘. 그리고 줄바꿈해서 깔끔하게 보여줘.`;

  const chatResponse = await openai.chat.completions.create({
    messages: [
      {
        role: 'user',
        content: prompt,
      },
    ],
    model: 'gpt-3.5-turbo',
  })
  response.value = chatResponse.choices[0].message.content
  console.log('chatGPT 결과: ', chatResponse.choices[0].message.content)
} catch (error) {
  console.log('chatGPT: 🚨 에러가 발생했습니다.')
}
}

const toggleChatbotVisibility = () => {
isChatbotVisible.value = !isChatbotVisible.value; // 채팅봇 표시 여부를 토글
}

const formattedResponse = computed(() => {
return response.value.replace(/\n/g, '<br>')
})
</script>

<style scoped>
* {
margin: 0;
padding: 0;
box-sizing: border-box;
}

.logo-container {
  position: absolute;
  top: 10px; /* Adjust as needed */
  right: 10px; /* Adjust as needed */
  margin-right: 2%;
}

.text-center {
text-align: center;
margin: 20px;
}

button {
height: 51px;
margin-top: 16px;
margin-left: 23px;
margin-right: 23px;
margin-bottom: 20px;
padding: 10px 20px;
font-size: 16px;
cursor: pointer;
border-top-left-radius: 30px;
border-top-right-radius: 18px;
border-bottom-right-radius: 18px;
border-width: thin;
border: none;
background-color: rgb(245, 205, 192);
color: rgba(48, 45, 41, 0.932);
}

/* button:after {
  border-top: 12px solid rgb(245, 205, 192);
  border-left: 0px solid transparent;
  border-right: 17px solid transparent;
  border-bottom: 0px solid transparent;
  content: "";
  position: absolute;
  top: 76px;
  left: 43px;
} */

.chat-img {
border: 1px solid #bb5454;
padding: 2px;
/* position: fixed; */
/* right: 40px;
bottom: 50px; */
position: absolute;
right: 4%;
bottom: 5%;
max-width: 55px;
height: auto;
border-radius: 25px;
background-color: white;
box-shadow: rgb(0 0 0 / 10%) 0px 12px 60px 5px;
cursor: pointer; /* 이미지 클릭을 위한 커서 설정 */

}
.chatbot-container {
/* position: fixed; */
/* right: 40px;
bottom: 130px;
width: 650px;
height: 780px; */
position: absolute;
right: 3%;
bottom: 14%;
width: 40%;
height: 69%;
background-color: white;
border-radius: 25px;
box-shadow: rgb(0 0 0 / 30%) 0px 12px 60px 5px;
overflow-y: auto;
display: flex;
flex-direction: column;
/* justify-content: center; */
/* overflow: hidden; */
padding: 22px;
/* height: 80%; */
min-height: 250px;
max-height: 780px;
}

.chatbot-message {
/* margin-top: 30px; */
margin-bottom: 30px;
height: 100%;
background-color: rgba(48, 45, 41, 0.164);
margin-top: 5px;
margin-left: 23px;
margin-right: 23px;
padding: 25px 31px 22px 26px;
font-size: 16px;
cursor: pointer;
border-top-right-radius: 40px;
border-top-left-radius: 20px;
border-bottom-left-radius: 48px;
border-width: thin;
border: none;
color: rgba(48, 45, 41, 0.932);

}


</style>