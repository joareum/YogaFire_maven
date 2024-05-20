<template>
    <div>
        <h4>로그인</h4>
        <fieldset>
            <!-- <legend>로그인</legend> -->
            <div>
                <label for="userId">아이디 : </label>
                <input type="text" id="userId" v-model="user.userId">
            </div>
            <div>
                <label for="password">비밀번호 : </label>
                <input type="password" id="password" v-model="user.password">
            </div>
            <div>
                <button @click="login">로그인</button>
                <button @click="findId">아이디 찾기</button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import { useUserStore } from "@/stores/user";
import { ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();
const store = useUserStore();

const user = ref({
    userId: '',
    password: '',
})

const login = function() {
    console.log(user.value)
    store.login(user.value)
}

const findId = function(){
  router.push({ name: 'findid'});
}

</script>

<style scoped></style>