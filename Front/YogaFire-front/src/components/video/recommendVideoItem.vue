<template>
    <li>
        <div class="video-container">
            <img :src="video.snippet.thumbnails.high.url" @click="clickVideo(video)">
        </div>
    </li>
</template>

<script setup>
import { useVideoStore } from '@/stores/video';
import { defineProps } from 'vue';

import { useRouter } from 'vue-router';

const store = useVideoStore()

const props = defineProps({
    video: Object,
    required: true
})

const router = useRouter();

const clickVideo = function (video) {
    store.clickVideo(video.id)

    router.push({
        name: 'detail',
        params: {
            videoId: video.id.videoId,
            channelTitle: props.video.snippet.channelTitle,
            description: props.video.snippet.description,
            title: props.video.snippet.title,
            publishTime: props.video.snippet.publishTime,
            isFavorite: 'N'
        }
    }).then(() => {
        console.log(video.id.videoId + '으로 이동했습니다.');
    }).catch(error => {
        console.error('Navigation to detail view failed:', error);
    });

}

</script>

<style scoped>
/* img {
    margin: 3.2px 0px 1px 0px;
    width: 350px;
} */

img {
    margin: 0 auto; /* 수평 중앙 정렬 */
    display: block; /* 블록 요소로 설정하여 수직 정렬을 조정합니다. */
    width: 350px;
    height: auto; /* 너비에 따라 비율을 유지하도록 설정합니다. */
}


li {
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