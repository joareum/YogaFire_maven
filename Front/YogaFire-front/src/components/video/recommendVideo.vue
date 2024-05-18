<template>
    <div class="base">
        <!-- <h2>추천영상</h2> -->
        <div class="list">
            <RecommendVideoItem v-for="video in store.recommendVideos"
            :key ="video.id" 
            :video="video" />
        </div>
    </div>
</template>

<script setup>
import { useVideoStore } from '@/stores/video'
import RecommendVideoItem from '@/components/video/recommendVideoItem.vue'
import { onMounted } from 'vue'; 

const store = useVideoStore()

onMounted (()=>{
    store.videoRecommend()
})

</script>

<style scoped>
/* 가로정렬 여기에 걸기 */
.list{
    list-style-type: none;
    display:flex;
    flex-wrap: wrap;
}

.base{
    margin: 20px;
}
</style>