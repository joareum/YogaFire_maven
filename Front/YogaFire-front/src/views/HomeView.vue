<template>
  <!-- 로고 -->
  <div class="img">
    <img src="@/assets/yogafire.png" style="max-width: 300px; height: auto;">
  </div>
  <div class="ibm-plex-sans-bold-italic">
    <h2>YOGAFIRE</h2>
  </div>
  
  <div class="base">
    <div class="recommend-container">
      <SearchVideo/>
      <RecommendVideo />
    </div>
  </div>
  
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
  </template>
  
  <script setup>
    import SearchVideo from '@/components/video/searchVideo.vue'
    import RecommendVideo from '@/components/video/recommendVideo.vue'
  
  </script>
  
  <style>
  /* .logo-font {
  font-family: "Open Sans", sans-serif;
  font-optical-sizing: auto;
  font-weight: 700;
  font-style: Italic;
  font-variation-settings: "wdth" 100;
  text-align: center;
  font-size: 26px;
  color: rgb(173, 33, 33);
  } */

  /* .logo-font {
  font-family: "Open Sans", sans-serif;
  font-optical-sizing: auto;
  font-weight: 700;
  font-style: Italic;
  font-variation-settings: "wdth" 100;
  text-align: center;
  font-size: 26px;
  color: rgb(173, 33, 33);
} */

.ibm-plex-sans-bold-italic {
  font-family: "IBM Plex Sans", sans-serif;
  font-weight: 700;
  font-style: italic;
  text-align: center;
  font-size: 30px;
  color: rgb(173, 33, 33);
}

  .base .recommend-container {
    text-align: center;
  }
  
  .img {
    text-align: center;
  }

  .base {
    margin: 0 50px;
  }

  
  </style>
  