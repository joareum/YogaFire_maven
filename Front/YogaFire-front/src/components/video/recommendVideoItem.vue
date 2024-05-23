<template>
    <div class="list-all">
    <li>
        <div class="video-item">
        <div class="video-container">
            <img :src="video.snippet.thumbnails.high.url" @click="clickVideo(video)">
        </div>
        <div class="text-container">
        <span>{{ video.snippet.title }}</span>
    </div>
    </div>
    </li>
</div>
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

img {
    width: 100%;
    height: 100%;
    object-fit: cover;
    cursor: pointer;
}


li {
    margin: 1%;

}

.list-all {
    margin: 0 auto;
}

.video-item {
    width: 365px;
}

.text-container {
    word-break: break-word;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    width: 100%;
    height: 34px;
    padding: 0.8%;
    margin-top: 2%;
    margin-bottom: 17%;
    text-overflow: ellipsis;
}

.video-container {
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    width: 100%;
    gap: 3%;
    border-radius: 10px;
    border-color: white;
    width: 365px; /* 원하는 너비 설정 */
    height: 196px; /* 원하는 높이 설정 */
    overflow: hidden; /* 컨테이너를 넘는 내용 숨기기 */
}
</style>