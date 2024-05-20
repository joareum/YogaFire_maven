<template>
    <div>
      <h3>showCommentList</h3>
      <div class="commentList">
        
      </div>

  </div>
  </template>
  
  <script setup>
    import { useCommentStore } from '@/stores/comment'

    const store = useCommentStore();

  </script>
  
  <style scoped>
  
  </style>