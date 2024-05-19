<template>
    <div class="container">
        <div class="insert text-center">
            <!-- 사람 이모티콘?? 같은 거 들어가면 귀여울 듯 -->
            <label for="height">키</label>
            <input type="text" id="height" v-model="height" class="input" />

            <label for="weight">체중</label>
            <input type="text" id="weight" v-model="weight" class="input" />

            <label for="age">나이</label>
            <input type="text" id="age" v-model="age" class="input" />
            <br>
            <br>

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
        <div class="result text-center">당신의 적정 칼로리는 {{ kcal }} kcal 입니다.</div>

    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();

const height = ref();
const weight = ref();
const age = ref();
const sex = ref('');
const activityLevel = ref(1.2);

const bmr = computed(() => {
    if (sex.value === "남") {
        return 88.362 + (13.397 * weight.value) + (4.799 * height.value) - (5.677 * age.value);
    } else {
        return 447.593 + (9.247 * weight.value) + (3.098 * height.value) - (4.33 * age.value);
    }
});

const kcal = computed(()=>{
    console.log('칼로리 계산 시작점')
    if (bmr.value && activityLevel.value) {
        let result = bmr.value * activityLevel.value;
        // useUserStore.calculatedKcal = 20;
        // console.log("d"+useUserStore.calculatedKcal)
        console.log('칼로리 계산 완료')
        console.log(Math.round(result))
        return Math.round(result);
    } else {
        // useUserStore.calculatedKcal = 40;
        return 0; // 또는 적절한 기본값 설정
    }
});

userStore.calculatedKcal = kcal

</script>

<style scoped></style>