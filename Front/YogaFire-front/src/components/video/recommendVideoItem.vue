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
/* img {
    margin: 3.2px 0px 1px 0px;
    width: 350px;
} */

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
    display: -webkit-box;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
    width: 100%;
    height: 2%;
    padding: 0.8%;
    margin-top: 2%;
    margin-bottom: 17%;
    overflow: hidden;
    text-overflow: ellipsis;
    /* background-color: rgba(252, 182, 165, 0.2); */
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