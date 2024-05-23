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
  height: 38px;
  border: none;
  outline: none;
  border-bottom: 2px solid #ff7c7c;
  margin-bottom: 7%;
  font-size: 16px;
  text-align: center;
  resize: none;
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  transition: border-color 0.3s, box-shadow 0.3s;
}

textarea:focus {
  border-bottom: 2px solid #ff5252;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%; /* 부모 요소의 높이를 기준으로 정렬 */
}
</style>
