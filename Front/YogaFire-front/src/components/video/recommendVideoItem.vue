<template>
    <li>
        <div class="video-container">
            <img :src="video.snippet.thumbnails.high.url" @click="clickVideo(video)"> 
        </div>
    </li>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import { defineProps} from 'vue'; 

import { useRouter } from 'vue-router';

const store = useVideoStore()

const props  = defineProps({
    video: Object,
    required: true
})

const router = useRouter();

const clickVideo = function(video){
    store.clickVideo(video.id)

    router.push({name: 'detail', params: {
        videoId: video.id.videoId,
        channelTitle: props.video.snippet.channelTitle,
        description: props.video.snippet.description,
        title: props.video.snippet.title,
        publishTime: props.video.snippet.publishTime,
        isFavorite: 'N'
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