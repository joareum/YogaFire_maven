<template>
    <div id="container">
        <header>
            <nav>
                <RouterLink to="/">HOME</RouterLink> |
                <RouterLink v-if="store.loginUser === ''" :to="{ name: 'login' }">LOG IN |</RouterLink> 
                <RouterLink v-if="store.loginUser === ''" :to="{ name: 'signup' }"> SIGN UP |</RouterLink> 
                <p v-if="store.loginUser !== ''" @click="logout"> LOG OUT |</p>
                <p> MY PAGE |</p>
                <RouterLink :to="{ name: 'dietView' }"> DIET |</RouterLink>
                <RouterLink :to="{ name: 'centerView' }"> CENTER |</RouterLink> 
            </nav>
        </header>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from 'vue';

const store = useUserStore();


const logout = function() {
    console.log()
    store.logout()
}

</script>

<style scoped>
#container {
    text-align: center;
}

nav a , p{
    font-weight: bold;
    text-decoration: none;
    color: rgba(0, 0, 0, 0.514);
}

nav a.router-link-exact-active {
    color: #ffbdd3
}
</style>