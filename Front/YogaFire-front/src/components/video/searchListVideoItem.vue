<template>
    <li>
        <div class="video-container">
            <img :src="video.snippet.thumbnails.high.url" @click="clickVideo(video)">
        </div>
        <!-- <span>{{ video.snippet.title }}</span> -->
    </li>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import { defineProps } from 'vue'; 


import { useRouter } from 'vue-router';

const store = useVideoStore()

const props  = defineProps({
    video:{
        type:Object,
        required: true
    }
})

const router = useRouter();

const clickVideo = function(video){
  console.log(video)
  store.clickVideo(video.id)

  router.push({
    name: 'detail',
    params: {
      videoId: video.id.videoId,
      channelTitle: video.snippet.channelTitle,
      description: video.snippet.description,
      title: video.snippet.title,
      publishTime: video.snippet.publishTime
    }})
}

</script>

<style scoped>
img {
    margin: 3.2px 0px 1px 0px;
    width: 350px;

}

li{ 
    margin: 20px 5px;
}

.text-container {
    word-break: break-word;
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    max-width: 280px;
    height: 50px;
    padding: 2px;
    margin: 4px;
    overflow: hidden;
    text-overflow: ellipsis;
}

.video-container {
    border: 3px groove;
    border-radius: 4px;
    border-color: white;
}
</style>