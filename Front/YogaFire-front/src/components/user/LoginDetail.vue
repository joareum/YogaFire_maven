<template>
    <div>
        <fieldset>
            <h4>YOGAFIRE</h4>
            <!-- <legend>로그인</legend> -->
            <br>
            <div class="text-align">
                <!-- <label for="userId">아이디 : </label> -->
                <input class="insert" type="text" id="userId" v-model="user.userId">
            </div>
            <br>
            <div class="text-align">
                <!-- <label for="password">비밀번호 : </label> -->
                <input class="insert" type="password" id="password" v-model="user.password">
            </div>
            <br>
            <div class="text-align">
                <button @click="login">로그인</button>
                <!-- <button @click="findId">아이디 찾기</button> -->
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

<style scoped>
.insert{
    width: 800px;
}

fieldset{
    padding-top: 300px;
    padding-left : 300px;
    padding-right: 300px;
}

h4{
    text-align: center;
    font-size:60px;
    color:white;
}

.text-align{
    text-align: center;
}
</style>