<template>
    <SearchVideo />
    <div class="searchList" style="padding: 0 20px;">
        <SearchListVideoItem v-for="video in store.videos"
        :key="video.id.videoId"
        :video="video"/>
    </div>
</template>

<script setup>
    import SearchVideo from '@/components/video/searchVideo.vue'
    import { useVideoStore } from '@/stores/video'
    import SearchListVideoItem from '@/components/video/searchListVideoItem.vue'

    const store = useVideoStore();
</script>

<style scoped>
.searchList{
    padding-left: 500px;
    list-style-type: none;
    display:flex;
    flex-wrap: wrap;
    margin-right: 20px;
    cursor: url("data:image/svg+xml;utf8,<svg xmlns='http://www.w3.org/2000/svg'  width='40' height='48' viewport='0 0 100 100' style='fill:black;font-size:24px;'><text y='50%'>🔥</text></svg>") 16 0, auto;
}

.base{
    margin: 20px;
}

template{
    margin: 30px;
}
</style>