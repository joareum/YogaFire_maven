<template>
    <div class="container">
        <div class="box">
            <h1>🏃🏃🏃</h1>
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

                <div class="input-group">
                    <!-- <label class="label" for="sex">성별</label> -->
                    <span class="checkbox-box">
                        <span class="checkbox">
                            <input type="checkbox" name="sex" value="남" id="sex" ref="maleCheckbox"
                                @change="handleCheckboxChange('남')" />
                            <span class="checkbox-text" />
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



<style scoped>
*,
*:before,
*:after {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    /* text-align: center; */
}


.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 0 auto;
    /* height: 100px; */
    /* background-color: rgba(255, 255, 255, 0.5); */
    /* padding: 20px; */
    /* border: 1px solid blanchedalmond; */
}

.box {
    width: 680px;
    text-align: center;
    margin-top: 30px;
    /* 흰 박스 마진 */
    background-color: rgb(255, 255, 255, 0.5);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

h1 {
    margin-top: 27px;
    margin-bottom: 45px;
    text-shadow: -3px -3px 0 #ffffff, 0 -3px 0 #ffffff, 3px -3px 0 #ffffff, 3px 0 0 #ffffff, 3px 3px 0 #ffffff, 0 3px 0 #ffffff, -3px 3px 0 #ffffff, -3px 0 0 #ffffff;
    /* text-shadow:-2.5px -2.5px 0 #ffffff,0 -2.5px 0 #ffffff, 2.5px -2.5px 0 #ffffff,2.5px 0 0 #ffffff,2.5px 2.5px 0 #ffffff,0 2.5px 0 #ffffff,-2.5px 2.5px 0 #ffffff,-2.5px 0 0 #ffffff; */
}

.input-group {
    margin: 13px 0;
    text-align: center;
}


.input-age {
    margin-bottom: 30px;
}

.input-activity {
    margin-top: 30px;
}

.input {
    text-align-last: center;
    text-align: center;
    width: 350px;
    height: 30px;
    /* margin-left: 30px; */
    margin-right: 38px;

    font-size: 15px;
    border: 0;
    border-radius: 12px;
    outline: none;
    padding-left: 10px;
    background-color: rgb(255, 255, 255);
}

.checkbox {
    margin-left: 40px;
    margin-right: 30px;
    accent-color: rgb(250, 16, 55);
}

.checkbox-box {
    margin-left: 70px;
}

.checkbox-text {

    margin-left: 13px;
}

.result {
    margin-top: 40px;
    margin-bottom: 26px;
    text-align: center;
}
</style>