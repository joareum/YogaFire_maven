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
    list-style-type: none;
    display:flex;
    flex-wrap: wrap;
}

.base{
    margin: 20px;
}

template{
    margin: 30px;
}
</style>