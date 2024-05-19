<template>
  <div class="text-center">
    <button @click="getGPTResponse">당신의 적정 칼로리인 {{ kcal }} kcal 에 맞는 식단을 GPT가 알려줄거에요!</button>
    <h4 v-html="formattedResponse"></h4>
  </div>
</template>

<script setup>
import { ref, watchEffect, computed } from 'vue'
import OpenAI from 'openai'

import { useUserStore } from '@/stores/user.js';

const userStore = useUserStore();

const kcal = ref(0); // kcal 값을 저장할 ref 변수 선언

// kcal 값이 변경될 때마다 실행되는 비동기 함수
watchEffect(async () => {
  kcal.value = userStore.calculatedKcal; // 비동기적으로 kcal 값을 가져옴
});

const response = ref('')

const getGPTResponse = async () => {
  try {
    const openai = new OpenAI({
      apiKey: `${import.meta.env.VITE_OPENAI_API_KEY}`,
      dangerouslyAllowBrowser: true,
    })

    const prompt = `일일 권장 칼로리가 ${kcal.value} kcal인 사람을 위한 식단을 간단하게 추천해줘. 답변의 첫 문장은 일일 권장 칼로리가 ${kcal.value} kcal인 사람을 위한 식단을 간단하게 추천해드리겠습니다. 라고 해줘`;

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

const formattedResponse = computed(() => {
  return response.value.replace(/\n/g, '<br>')
})
</script>

<style scoped></style>