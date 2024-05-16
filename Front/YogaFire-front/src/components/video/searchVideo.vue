<!-- 검색 컴포넌트 -->
<template>
    <div>
        <input type="text" v-model="keyword">
        <span>     </span>
        <button @click="search">검색</button>
        <!-- <RouterLink :to="{name:'search', params: {keyword:keyword}}">test</RouterLink> -->
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useVideoStore } from '@/stores/video'
import { useRoute, useRouter, RouterLink } from 'vue-router'

const router = useRouter();
const store = useVideoStore()
const keyword = ref('');
const search = function(){
    store.videoSearch(keyword.value)
    router.push({name: 'search'});
};

</script>

<style scoped>
input{
    width: 500px;
    height: 30px;
    margin-left: 20px;
}

button{
    margin-right: 20px;
}
</style>