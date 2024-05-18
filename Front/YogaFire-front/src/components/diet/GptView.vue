<template>
    <div class="text-center">
        <button @click="getGPTResponse">GPT에게 물어봐!</button>
        <h4 v-html="formattedResponse"></h4>
    </div>
</template>

<script setup>
    import { ref, computed } from 'vue'
    import OpenAI from 'openai'

    // 상태 관리
    const response = ref('')

    // chatGPT description
  const getGPTResponse = async () => {
  try {
    const openai = new OpenAI({
      apiKey: `${import.meta.env.VITE_OPENAI_API_KEY}`,
      dangerouslyAllowBrowser: true,
    })

    const prompt = `일일 권장 칼로리가 1800인 사람을 위한 식단을 간단하게 추천해줘`;

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

// 줄바꿈을 <br> 태그로 바꾸기
const formattedResponse = computed(() => {
  return response.value.replace(/\n/g, '<br>')
})
</script>

<style scoped>

</style>