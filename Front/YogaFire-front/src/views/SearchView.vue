<template>
    <div class="searchBase">

        <h2>searchView</h2>
            <SearchListVideo />
        <!-- {{ store.videos }} -->
    </div>
</template>

<script setup>
import SearchListVideo from '@/components/video/searchListVideo.vue'
// import { onMounted } from 'vue'
// import { useVideoStore } from '@/stores/video';
// import { useRoute } from 'vue-router'

// const route = useRoute()

// const store = useVideoStore()



// onMounted( () => {
//     console.log("onMount")
//     console.log(route.params.keyword)
//     store.videoSearch(route.params.keyword)
// })


</script>

<style scoped>
.searchBase{
    text-align: center;
}
</style>