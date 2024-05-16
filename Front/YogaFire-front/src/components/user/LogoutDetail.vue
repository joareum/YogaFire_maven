<template>
    <div>
        <h4>로그아웃</h4>
        <fieldset>
            <legend>로그아웃</legend>
            <!-- <div>
                <label for="id">아이디 : </label>
                <input type="text" id="id" v-model="user.id">
            </div>
            <div>
                <label for="password">비밀번호 : </label>
                <input type="password" id="password" v-model="user.password">
            </div> -->
            <div>
                <button @click="logout">로그인</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from 'vue';

const store = useUserStore();



</script>

<style scoped></style>