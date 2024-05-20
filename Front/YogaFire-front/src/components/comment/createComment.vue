<template>
  <div>
    <span>createComment ! </span>
    <textarea placeholder="댓글을 입력하세요" v-model="context"></textarea>
    <button @click="createComment">댓글달기</button>
  </div>
</template>
  
<script setup>
import { useCommentStore } from '@/stores/comment'
import { useRoute } from 'vue-router';
import { ref } from 'vue';

const route = useRoute();
const store = useCommentStore();

const routevideoId = ref(route.params.videoId);

//  // video 객체 생성
//  const video = {
//   id: { videoId: videoId.value },
//   sessionId: { sessionId: sessionId.value},
//   snippet: {
//     title: title.value,
//     channelTitle: channelTitle.value,
//     publishTime: publishTime.value
//   },
//   isFavorite: isFavorite.value
//   };

const context = ref('')

const createComment = function() {
  store.insertComment(routevideoId.value , context.value);
  context.value = '';
}

</script>

  
<style scoped>
</style>