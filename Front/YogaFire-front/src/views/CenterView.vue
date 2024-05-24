<template>
  <div>
    <div class="nav">
      <a href="http://localhost:5173">
        <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
      </a>
      <div class="sub-nav">
        <SubNav />
      </div>
    </div>
    <div class="image-container">
      <img src="@/assets/yoga_image1.png" class="image">
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

.image-container {
  display: flex;
  justify-content: center; /* Center align horizontally */
  margin-bottom: 50px;
  margin-top: 8px;
  align-items: center; /* Center align vertically */
}

.image {
  width: auto;
  height: 450px;
}

</style>
