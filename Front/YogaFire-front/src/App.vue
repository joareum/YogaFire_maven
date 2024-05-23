<template>
  <div>
    <!-- <br> -->
    <!-- <TheHeaderNav/> -->
    <RouterView />
    <br>
    <br>
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
// import TheHeaderNav from '@/components/common/TheHeaderNav.vue'

</script>

<style scoped>

@font-face {
    font-family: 'SejonghospitalBold';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2312-1@1.1/SejonghospitalBold.woff2') format('woff2');
    font-weight: 400;
    font-style: normal;
}

* {
  font-family: 'SejonghospitalBold'; /* 적용할 폰트명을 사용합니다. */
}

body {

margin: 0;

}

#app {

width : 100vw;
height : 100vh;

}

</style>
