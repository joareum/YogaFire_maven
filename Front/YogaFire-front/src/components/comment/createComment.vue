<template>
  <div>
    <!-- <span>createComment ! </span> -->
    <textarea v-model="context" @keyup.enter="createComment"></textarea>
    <!-- <button @click="createComment">댓글달기</button> -->
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
textarea{
  width: 650px;
  border: none;
  outline: none;
  border-bottom: 2px solid #ff7c7c;
  font-size: 12px;
  text-align: center;
  resize: none;
  font-size: 16px;
}
</style>