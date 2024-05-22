<template>
    <div class="base">
        <!-- <HomeView /> -->
        <div class="nav">
            <a href="http://localhost:5173">
                <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
            </a>
            <!-- <AnotherNav /> -->
            <div class="sub-nav">
            <SubNav />
        </div>
        </div>

        <!-- <div class="search">
            <SearchVideo />
        </div> -->
        <div>
            <h3>요가는 자신을 찾는 여정의 시작입니다.</h3>
            <h4>함께 시작해보세요.</h4>
            <hr>
        </div>
        <div class="list-under">
        <div class="list">
            <RecommendVideoItem v-for="video in store.recommendVideos" :key="video.id" :video="video" />
        </div>
    </div>
    </div>
</template>

<script setup>
import { useVideoStore } from '@/stores/video'
import RecommendVideoItem from '@/components/video/recommendVideoItem.vue'
import { onMounted } from 'vue';
import SubNav from '@/components/common/SubNav.vue'
import SearchVideo from '@/components/video/searchVideo.vue'

const store = useVideoStore()

onMounted(() => {
    store.videoRecommend()
})

</script>

<style scoped>
.nav {
    display: flex; /* Flexbox를 사용하여 자식 요소를 가로로 정렬합니다. */
    align-items: center; /* 세로 중앙 정렬 */
    margin-top: 0px;
    /* margin-left: 75%; */
}

.nav a {
    margin-right: 20px; /* img와 SubNav 사이의 간격을 조절합니다. */
}

.sub-nav {
    margin-left: auto; /* SubNav를 오른쪽 끝으로 정렬합니다. */
}

.search {
    text-align: left !important;
    width: 600px !important;
    margin-top: 10%;
    margin-left: 3%;
    text-align: center;
}

h3 {
    background-color: white;
    margin-top: 4%;
    margin-left: 3%;
    text-align: left;
    font-size: 54px;
    background-image: linear-gradient(to right, #fd8bad 0%, #ffe658 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    margin-bottom: 0px;
    letter-spacing: -5px;
}

h4 {
    margin-left: 3%;
    text-align: left;
    font-size: 50px;
    font-weight: bold;
    background-image: linear-gradient(to right, #fd8bad 0%, #ffe658 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    margin-top: 0px;
    letter-spacing: -1px;
    margin-bottom: 36px;
}

hr {
    border: 0;
    height: 4px;
    margin-left: 1%;
    background-image: linear-gradient(to right, rgb(250, 112, 153, 0.4) 0%, rgba(250, 239, 177, 0.4) 100%);
}

.list-under {
    /* background-color: #fd8bad; */
    display: flex;
    justify-content: center;
}

.list {
    /* background-color: rgba(255, 236, 227, 0.36); */
    /* background-color: rgba(245, 228, 222, 0.36); */
    background: linear-gradient(to bottom,
    rgba(245, 228, 222, 0.25) 25%,
    rgba(245, 228, 222, 0.4) 50%,
    rgba(245, 228, 222, 0.5) 75%,
    rgba(245, 228, 222, 0.6) 100%,
    rgba(245, 228, 222, 0.8) 100%,
    rgba(245, 228, 222, 1) 100%
    );

    width: 88%;
    padding: 3%;
    list-style-type: none;
    display: flex;
    flex-wrap: wrap;
    margin: auto;
    /* padding-left: 45px; */
}


.base {
    margin: 20px;
    padding-left: 45px;
    padding-right: 40px;
}

img {
    margin-top: 1%;
    margin-left: 0px;
}


</style>