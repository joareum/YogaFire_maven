<template>
    <div class="container">
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
html, body {
    height: 100%;
    margin: 0;
    padding: 0;
}

.container {
    width: 100%;
    /* max-height: 2000px; */
    height: 100%;
    display: flex;
    margin: 0 auto;
}

div.left {
    width: 50%;
    height: 100%;
    /* float: left; */
    box-sizing: border-box;
    
    /* background: rgba(255, 124, 124, 0.080); */
    /* background-image: linear-gradient(
    45deg,
    #ff9a9e 0%,
    #fad0c4 99%,
    #fad0c4 100%
  ); */

    /* background-image: linear-gradient(-20deg, #ddd6f3 0%, #faaca8 100%, #faaca8 100%); */
    /* background-image: linear-gradient(
    45deg,
    #ff9a9e 0%,
    #fad0c4 99%,
    #fad0c4 100%
  ); */
  background-image: linear-gradient(-225deg, #FFE29F 0%, #FFA99F 48%, #FF719A 100%);
  /* background-image: linear-gradient(45deg, #ee9ca7 0%, #ffdde1 100%); */

}

div.right {
    height: 100%;
    width: 50%;
    /* float: right; */
    box-sizing: border-box;

    /* background-color: blue; */
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