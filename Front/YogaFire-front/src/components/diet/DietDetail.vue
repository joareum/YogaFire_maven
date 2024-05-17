<template>
    <div class="container">
        <div class="insert text-center">
            <label for="height">키</label>
            <input type="text" id="height" v-model="height" class="input" />

            <label for="weight">체중</label>
            <input type="text" id="weight" v-model="weight" class="input" />

            <label for="age">나이</label>
            <input type="number" id="age" v-model="age" class="input" />

            <label for="sex">성별</label>
            <input type="radio" name="sex" value="남" id="sex" v-model="sex" class="input" />남
            <input type="radio" name="sex" value="여" id="sex" v-model="sex" class="input" />여

            <label for="activity">운동량</label>
            <select id="activity" v-model="activityLevel" class="input">
                <option value="1.2">조금 혹은 거의 하지 않음</option>
                <option value="1.375">주에 1-3일 정도 운동</option>
                <option value="1.55">주에 3-5일 운동</option>
                <option value="1.725">주에 6-7일 운동</option>
                <option value="1.9">매우 활동적</option>
            </select>
            <br />
        </div>
        <br />
        <div class="result text-center">당신의 적정 칼로리는 {{ kcal }} 입니다.</div>

    </div>
</template>

<script setup>
import { ref, computed } from "vue";

const height = ref(0);
const weight = ref(0);
const age = ref(0);
const sex = ref(0);
const activityLevel = ref(1.2);

const bmr = computed(()=>{
})
const kcal = ref("");

</script>

<style scoped></style>