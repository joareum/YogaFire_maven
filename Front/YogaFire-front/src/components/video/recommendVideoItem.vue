<template>
    <li @click="clickVideo">
        <img :src="video.snippet.thumbnails.default.url"> 
    </li>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import { defineProps} from 'vue'; 
const store = useVideoStore()

const props  = defineProps({
    video:Object ,
})

const clickVideo = function(){
    store.clickVideo(props.video)
}

</script>

<style scoped>
img{
    height: 300px;
}

li{
    margin: 20px;
}
</style>