<template>
    <div class="base">
        <AnotherNav />

        <div class="search">
            <br>
            <h1>내 안의 요가를 깨우다</h1>
            <!-- <h1>깨우다</h1> -->
            <br>
            <SearchVideo />
            <br>
        </div>

        <div class="list">
            <RecommendVideoItem v-for="video in store.recommendVideos" :key="video.id" :video="video" />
        </div>
    </div>
</template>

<script setup>
import { useVideoStore } from '@/stores/video'
import RecommendVideoItem from '@/components/video/recommendVideoItem.vue'
import { onMounted } from 'vue';
import AnotherNav from '@/components/common/AnotherNav.vue'
import SearchVideo from '@/components/video/searchVideo.vue'

const store = useVideoStore()

onMounted(() => {
    store.videoRecommend()
})

</script>

<style scoped>
/* 가로정렬 여기에 걸기 */
h1 {
    text-align: center;
    font-size: 60px;
    background-image: linear-gradient(to top, #ff9a9e 0%, #fecfef 99%, #fecfef 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    font-weight: bold;
}

.search {
    text-align: center;
}

.list {
    list-style-type: none;
    display: flex;
    flex-wrap: wrap;
    padding-left: 45px;
}

.base {
    margin: 20px;
    padding-left: 45px;
    padding-right: 40px;
}
</style>