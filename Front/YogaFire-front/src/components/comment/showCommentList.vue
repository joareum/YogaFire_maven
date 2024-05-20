<template>
  <div>
    <h3>showCommentList</h3>
    <div class="commentList">
      <li v-for="comment in comments" :key="comment.id">
        {{ comment.userId }} {{ comment.vCommentContent }} {{ comment.vCommentReg }}
        <button @click="deleteComment(comment.videoId, comment.vCommentId)">삭제</button>
      </li>
    </div>

</div>
</template>

<script setup>
  import { ref, onMounted, watch, computed  } from 'vue';
  import { useCommentStore } from '@/stores/comment'
  import { useRoute } from 'vue-router';

  const store = useCommentStore();
  const route = useRoute();
  const routevideoId = ref(route.params.videoId);
  
  onMounted(async ()=>{
      await store.getComment(routevideoId.value)
      console.log('mount')
      console.log(store.outComment)
      comments.value = store.outComment
    })

  // watch 걸기
  const comments = computed(() => store.outComment);

  const deleteComment = function(videoId, commentId){
    store.deleteComment(videoId, commentId);
  }
</script>

<style scoped>

</style>