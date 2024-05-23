<template>
    <div>
        <!-- <AnotherNav/>
        <br> -->
    <div class="nav">
      <a href="http://localhost:5173">
        <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
      </a>
      <div class="sub-nav">
        <SubNav />
      </div>
      
      
    </div>
    <div>
      <img src="@/assets/yoga_image1.jpg" style="width: 300px; height: auto;">
      <img src="@/assets/yoga_image2.jpg" style="width: 300px; height: auto;">

      </div>
        <CenterList />
    </div>
</template>

<script setup>
    import CenterList from '@/components/center/CenterList.vue'
    import SubNav from '@/components/common/SubNav.vue'
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
</style>