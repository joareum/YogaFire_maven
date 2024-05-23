<template>
    <li>
      <div class="video-container" @click="clickVideo(video)">
        <div>
        <img :src="video.snippet.thumbnails.high.url">
    </div>
        <div class="text-container">
          <span>{{ video.snippet.title }}</span>
        </div>
      </div>
    </li>
  </template>
  
  <script setup>
  import { useVideoStore } from '@/stores/video';
  import { defineProps } from 'vue'; 
  import { useRouter } from 'vue-router';
  
  const store = useVideoStore();
  
  const props = defineProps({
      video: {
          type: Object,
          required: true
      }
  });
  
  const router = useRouter();
  
  const clickVideo = function(video){
      console.log(video);
      store.clickVideo(video.id);
  
      router.push({
          name: 'detail',
          params: {
              videoId: video.id.videoId,
              channelTitle: video.snippet.channelTitle,
              description: video.snippet.description,
              title: video.snippet.title,
              publishTime: video.snippet.publishTime
          }
      });
  }
  </script>
  
  <style scoped>
  li {
      display: inline-block;
      margin: 25px 5px;
  }
  
  .video-container {
      display: flex;
      flex-direction: column;
      /* align-items: center; */
      border: 3px groove;
      border-radius: 4px;
      border-color: white;
  }
  
  img {
      /* margin: 3.2px 0px 1px 0px; */
      width: 350px;
  }
  
  .text-container {
      word-break: break-word;
      max-width: 350px;
      height: 50px;
      padding: 2px;
      margin: 4px;
      text-align: left;
      overflow: hidden;
      text-overflow: ellipsis;
  }
  </style>
  