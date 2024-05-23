<template>
    <div class="base-container">
        <div class="left">
        <DietDetail />
        </div>
        <div class="right">
        <GptView />
        </div>
    </div>
</template>

<script setup>
    import DietDetail from '@/components/diet/DietDetail.vue'
    import GptView from '@/components/diet/GptView.vue'
</script>

<style scoped>
html{
    height: 100%;
    margin: 0;
    padding: 0;
}

body{
    margin: 0px;
    /* border: solid 2px black; */
}

.base-container {
    width: 100%;
    /* max-height: 2000px; */
    height: 100%;
    display: flex;
    margin: 0;
    padding: 0;
    
}

.left {
    /* 이거 하면 고정 되는데 navbar가 사라짐 */
    position: fixed;
    width: 50%;
    height: 100vh;
    box-sizing: border-box;
    
  background-image: linear-gradient(-225deg, #FFE29F 0%, #FFA99F 48%, #FF719A 100%);

  background-size: 100vh; /* 배경 이미지를 요소에 맞게 확장하여 보여줍니다. */
  background-repeat: no-repeat; 
}

div.right {
    height: 100%;
    width: 50%;
    box-sizing: border-box;

}

@media (max-width: 768px) {
    .container {
        flex-direction: column;
    }

    .left, .right {
        width: 100%;
    }
}
</style>