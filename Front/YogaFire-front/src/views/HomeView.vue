<template>
  <div class="total">
    <div class="img">
      <img src="@/assets/yogafire_pink.png" style="max-width: 300px; height: auto;">
    </div>
    
    <div class="base">
      <div class="recommend-container">
        <br>
        <SearchVideo/>
        <br>
        <TheHeaderNav/>
      </div>
    </div>
    
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;1,100;1,200;1,300;1,400;1,500;1,600;1,700&family=Open+Sans:ital,wght@0,300..800;1,300..800&display=swap" rel="stylesheet">
  </div>
  
  </template>
  
  <script setup>
    import SearchVideo from '@/components/video/searchVideo.vue'
    import TheHeaderNav from '@/components/common/TheHeaderNav.vue'
  
  </script>
  
  <style>
  .total{
    padding-top: 130px;
  }


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
  