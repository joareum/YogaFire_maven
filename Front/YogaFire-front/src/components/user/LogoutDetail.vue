<template>
    <div>
        <h4>로그아웃</h4>
        <fieldset>
            <legend>로그아웃</legend>
            <div>
                <button @click="logout">로그인</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";


</script>

<style scoped></style>