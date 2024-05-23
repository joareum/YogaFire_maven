<template>
    <div class="searchBase">
        <div class="nav">
      <a href="http://localhost:5173">
        <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
      </a>
      <div class="sub-nav">
        <SubNav />
      </div>
    </div>
        <!-- <AnotherNav/> -->
<br>
<br>
<div>
            <SearchListVideo />
        </div>
        <!-- {{ store.videos }} -->
    </div>
</template>

<script setup>
import SearchListVideo from '@/components/video/searchListVideo.vue'
import SubNav from '@/components/common/SubNav.vue'

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
.nav {
  display: flex;
  align-items: center;
  margin-top: 0px;
}

.nav a {
  margin-right: 20px;
}

.sub-nav {
  margin-left: auto;
}

.searchBase {
    text-align: center;
}

</style>