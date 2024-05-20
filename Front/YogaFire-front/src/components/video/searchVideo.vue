<!-- 검색 컴포넌트 -->
<template>
    <TheHeaderNav/>
    <div class="search-bar">
        <input class="search-bar__input" type="text" placeholder="YOGAFIRE" v-model="keyword" @keyup.enter="search">
        <span>     </span>
        <!-- <button class="w-btn w-btn-gra2 w-btn-gra-anim" type="button" @click="search">Search</button> -->
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
@import url("https://fonts.googleapis.com/css?family=Poppins:200,300,400,500,600,700,800,900&display=swap");

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    text-align: center;
    padding: 100px;
    background: whitesmoke;
    display: table-cell;
}

button {
    margin: 20px;
}

.input-box{
    width: 500px;
    height: 46px;
    margin-left: 20px;
    border-color: gray;
}

.w-btn {
    height: 50px;
    width: 98px;
    position: relative;
    border: none;
    display: inline-block;
    /* padding: 15px 30px; */
    border-radius: 15px;
    font-family: "paybooc-Light", sans-serif;
    box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
    text-decoration: none;
    font-weight: 600;
    transition: 0.25s;
    font-size: 18px;
}

.w-btn-gra2 {
    background: linear-gradient(-45deg, #f58969, #e45c90, #f27b66, #ff2c2c);
    color: white;
    /* background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
    color: white; */
}

.w-btn-gra-anim {
    background-size: 400% 400%;
    animation: gradient1 5s ease infinite;
}

@keyframes gradient1 {
    0% {
        background-position: 0% 50%;
    }
    50% {
        background-position: 100% 50%;
    }
    100% {
        background-position: 0% 50%;
    }
}

search-bar {
  width: 220px;
  height: 27px;
  border-radius: 5px;
  border: solid 1px rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
  opacity: 1;
}
.search-bar__input {
  width: 400px;
  height: 45px;
  /* border: none; */
  -webkit-appearance: none;
  text-align: center;
  margin-left: 10px;
  overflow: auto;
  z-index: -1;
  font-size: 15px;
}


/* .search-bar__input:focus {
  outline: none;
  width: 300px;
  text-align: left;
} */

.fa-search {
  font-size: 15px;
}


</style>