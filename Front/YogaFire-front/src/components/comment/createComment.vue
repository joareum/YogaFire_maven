<template>
  <div class="container">
    <textarea v-model="context" @keyup.enter="createComment" placeholder="COMMENT"></textarea>
  </div>
</template>

<script setup>
import { useCommentStore } from '@/stores/comment'
import { useRoute } from 'vue-router';
import { ref } from 'vue';

const route = useRoute();
const store = useCommentStore();

const routevideoId = ref(route.params.videoId);
const context = ref('')

const createComment = async function() {
  await store.insertComment(routevideoId.value , context.value);
  context.value = '';
}
</script>

<style scoped>
textarea {
  width: 650px;
  height: 38px; /* 높이 설정 */
  border: none;
  outline: none;
  border-bottom: 2px solid #ff7c7c;
  margin-bottom: 7%;
  font-size: 16px;
  text-align: center;
  resize: none;
  background-color: #f9f9f9; /* 배경색 추가 */
  padding: 10px; /* 안쪽 여백 추가 */
  border-radius: 8px; /* 모서리를 둥글게 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  transition: border-color 0.3s, box-shadow 0.3s; /* 전환 효과 추가 */
}

textarea:focus {
  border-bottom: 2px solid #ff5252; /* 포커스 시 테두리 색 변경 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* 포커스 시 그림자 효과 변경 */
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%; /* 부모 요소의 높이를 기준으로 정렬 */
}
</style>
