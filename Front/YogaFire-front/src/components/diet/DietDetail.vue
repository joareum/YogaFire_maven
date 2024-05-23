<template>
    <div class="container">
        <div class="box">
            <h1 @click="goHome">🏃🏃🏃</h1>
            <div class="input-box">
                <div class="input-group">
                    <label for="height">키</label>
                    <input type="text" id="height" v-model="height" class="input" />
                </div>

                <div class="input-group">
                    <label for="weight">체중</label>
                    <input type="text" id="weight" v-model="weight" class="input" />
                </div>

                <div class="input-group input-age">
                    <label for="age">나이</label>
                    <input type="text" id="age" v-model="age" class="input" />
                </div>

                <div class="input-checkbox">
                    <span>
                        <span class="checkbox">
                            <input type="checkbox" name="sex" value="남" id="sex" ref="maleCheckbox"
                                @change="handleCheckboxChange('남')" />
                            <span class="checkbox-text">남</span>
                        </span>
                    </span>
                    <span>
                        <span class="checkbox">
                            <input type="checkbox" name="sex" value="여" id="sex" ref="femaleCheckbox"
                                @change="handleCheckboxChange('여')" />
                            <span class="checkbox-text">여</span>
                        </span>
                    </span>
                </div>


                <div class="input-group input-activity">
                    <label for="activity">운동량</label>
                    <select id="activity" v-model="activityLevel" class="input">
                        <option value="1.2">조금 혹은 거의 하지 않음</option>
                        <option value="1.375">주에 1-3일 정도 운동</option>
                        <option value="1.55">주에 3-5일 운동</option>
                        <option value="1.725">주에 6-7일 운동</option>
                        <option value="1.9">매우 활동적</option>
                    </select>
                </div>
            </div>
            <div class="result">당신의 적정 칼로리는 {{ kcal }} kcal 입니다.</div>
        </div>

    </div>
</template>

<script setup>
import { ref, computed } from "vue";
import { useUserStore } from '@/stores/user.js';
import { useRoute, useRouter } from 'vue-router'

const router = useRouter();


const userStore = useUserStore();

const height = ref();
const weight = ref();
const age = ref();
const sex = ref('');
const activityLevel = ref(1.2);

const maleCheckbox = ref(null);
const femaleCheckbox = ref(null);

const handleCheckboxChange = (selectedSex) => {
    if (sex.value === selectedSex) {
        sex.value = '';
        if (selectedSex === '남') {
            maleCheckbox.value.checked = false;
        } else {
            femaleCheckbox.value.checked = false;
        }
    } else {
        sex.value = selectedSex;
        if (selectedSex === '남') {
            femaleCheckbox.value.checked = false;
        } else {
            maleCheckbox.value.checked = false;
        }
    }
};

const bmr = computed(() => {
    if (sex.value === "남") {
        return 88.362 + (13.397 * weight.value) + (4.799 * height.value) - (5.677 * age.value);
    } else {
        return 447.593 + (9.247 * weight.value) + (3.098 * height.value) - (4.33 * age.value);
    }
});

const kcal = computed(() => {
    console.log('칼로리 계산 시작점')
    if (bmr.value && activityLevel.value) {
        let result = bmr.value * activityLevel.value;
        console.log('칼로리 계산 완료')
        console.log(Math.round(result))
        return Math.round(result);
    } else {
        return 0; // 또는 적절한 기본값 설정
    }
});

userStore.calculatedKcal = kcal

const goHome = () => {
    router.push('/');
};

</script>



<style scoped>
*,
*:before,
*:after {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}


.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100vh;
}

.box {
    width: 80%;
    max-width: 600px;
    text-align: center;
    margin-top: 45%;
    margin-bottom: 55%;
    /* 흰 박스 마진 */
    background-color: rgb(255, 255, 255, 0.5);
    padding: 2%;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
    margin-top: 5%;
    margin-bottom: 6%;
    text-shadow: -3px -3px 0 #ffffff, 0 -3px 0 #ffffff, 3px -3px 0 #ffffff, 3px 0 0 #ffffff, 3px 3px 0 #ffffff, 0 3px 0 #ffffff, -3px 3px 0 #ffffff, -3px 0 0 #ffffff;
}



.input-box {
    display: flex;
    flex-direction: column;
    gap: 15px;
}

.input-group {
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.input-group label {
    flex: 1;
    text-align: left;
    margin-left: 10%;
}

.input-group input,
.input-group select {
    flex: 2;
    text-align: center;
    height: 30px;
    width: 80%;
    font-size: 15px;
    border: 0;
    border-radius: 12px;
    outline: none;
    background-color: rgb(255, 255, 255);
    margin-right: 10%;
}

.input-age {
    margin-bottom: 3%
}

.input-activity {
    margin-top: 3%;
}

.checkbox {
    margin: 0 2%;
    accent-color: rgb(250, 16, 55);
}

.checkbox-box {
    display: flex;
    align-items: center;
    margin-left: 5%;
    
}

.checkbox-text {
    margin-left: 3%;
    font-size: 15px;
    margin-right: 5%;
}

.result {
    margin-top: 8%;
    margin-bottom: 7%;
    padding: 10px;
    width: 80%;
    margin-left: 10%;
    text-align: center;
    border: 2px solid #f8dddd86;
    font-weight: bold;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    background-color: #d8e9ff86;
}

</style>