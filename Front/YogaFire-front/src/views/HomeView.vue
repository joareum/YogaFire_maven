<template>
  <!-- 로고 -->
  <div class="img">
    <img src="@/assets/logo.png">
  </div>
  
  <div class="base">
    <div class="recommend-container">
      <SearchVideo/>
      <RecommendVideo />
    </div>
  </div>
  
  </template>
  
  <script setup>
    import SearchVideo from '@/components/video/searchVideo.vue'
    import RecommendVideo from '@/components/video/recommendVideo.vue'
  
  </script>
  
  <style>
  .base .recommend-container{
    text-align: center;
  }
  
  .img{
    text-align: center;
  }

  .base{
    margin: 0 50px;
  }

  
  </style>
  