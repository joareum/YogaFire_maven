<template>
  <div id="container">
    <header>
      <nav>
        <!-- <RouterLink to="/">HOME</RouterLink>
        <RouterLink to="/" class="logout-link"> |</RouterLink>  -->
        <RouterLink v-if="store.loginUser === ''" :to="{ name: 'login' }"> LOG IN</RouterLink>
        <RouterLink v-if="store.loginUser === ''" to="/" class="logout-link"> |</RouterLink> 
        <RouterLink v-if="store.loginUser === ''" :to="{ name: 'signup' }"> SIGN UP</RouterLink>
        <RouterLink v-if="store.loginUser === ''" to="/" class="logout-link"> |</RouterLink>  
        <RouterLink v-if="store.loginUser !== ''" to="/" @click="logout" class="logout-link"> LOG OUT</RouterLink>
        <RouterLink v-if="store.loginUser !== ''" to="/" class="logout-link"> |</RouterLink>  
        <RouterLink :to="{ name: 'myPageView' }"> MY PAGE </RouterLink>
        <!-- <RouterLink to="/" class="logout-link"> |</RouterLink>   -->
        <br>
        <RouterLink :to="{ name: 'dietView' }"> DIET </RouterLink>
        <RouterLink to="/" class="logout-link"> |</RouterLink>  
        <RouterLink :to="{ name: 'centerView' }"> CENTER </RouterLink> 
        <RouterLink to="/" class="logout-link"> |</RouterLink>  
        <RouterLink :to="{ name: 'recommend' }"> RECOMMEND VIDEO </RouterLink> 
        <!-- <RouterLink to="/" class="logout-link"> |</RouterLink>   -->
      </nav>
    </header>
  </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from 'vue';

const store = useUserStore();

const logout = function() {
  console.log();
  store.logout();
}
</script>

<style scoped>
#container {
  text-align: center;
}

nav a, nav p {
  font-weight: bold;
  font-size: 150%;
  text-decoration: none;
  color: rgba(0, 0, 0, 0.514);
}

/* 해당 스타일은 삭제합니다. */

nav a.logout-link {
  color: rgba(0, 0, 0, 0.514);
}

/* RouterLink에 마우스 hover 시 색상 변경 */
/* 해당 부분을 삭제하고 아래의 스타일을 추가합니다. */
nav a:hover {
    color: #ff7c7c; /* 마우스 hover 시 색상 변경 */
}
</style>


