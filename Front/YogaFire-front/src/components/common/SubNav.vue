<template>
    <div id="container">
      <header>
        <nav>
          <RouterLink v-if="store.loginUser === ''" :to="{ name: 'login' }"> LOG IN</RouterLink>
          <RouterLink v-if="store.loginUser === ''" to="/" class="logout-link"> |</RouterLink>
          <RouterLink v-if="store.loginUser === ''" :to="{ name: 'signup' }"> SIGN UP</RouterLink>
          <RouterLink v-if="store.loginUser === ''" to="/" class="logout-link"> |</RouterLink>
          <RouterLink v-if="store.loginUser !== ''" to="/" @click="logout" class="logout-link"> LOG OUT</RouterLink>
          <RouterLink v-if="store.loginUser !== ''" to="/" class="logout-link"> |</RouterLink>
          <RouterLink :to="{ name: 'recommend' }"> RECOMMEND VIDEO </RouterLink>
          <RouterLink to="/" class="logout-link"> |</RouterLink>
          <RouterLink :to="{ name: 'dietView' }"> DIET </RouterLink>
          <RouterLink to="/" class="logout-link"> |</RouterLink>
          <RouterLink :to="{ name: 'centerView' }"> CENTER </RouterLink>
          <RouterLink to="/" class="logout-link"> |</RouterLink>
          <RouterLink :to="{ name: 'myPageView' }"> MY PAGE </RouterLink>
        </nav>
      </header>
    </div>
  </template>
  
  <script setup>
  import { useUserStore } from "@/stores/user";
  
  const store = useUserStore();
  
  const logout = function () {
    console.log();
    store.logout();
  }
  </script>
  
  <style scoped>
  .logo-container {
    float: left;
  }
  
  img{
    text-align: left;
  }
  
  #container {
    text-align: right;
    padding-right: 15px;
    padding-top: 5px;
  }
  
  nav a,
  nav p {
    font-weight: bold;
    text-decoration: none;
    color: rgba(0, 0, 0, 0.514);
    font-size: 25px;
  }
  
  nav a.router-link-exact-active {
     color: #ff7c7c
  }
  
  nav a.logout-link.router-link-exact-active {
    color: rgba(0, 0, 0, 0.514) !important;
  }
  
  nav a.logout-link {
    color: rgba(0, 0, 0, 0.514);
  }
  </style>
  