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
.container {
    width: 100%;
    max-width: 2200px;
    height: 1000px;
    display: flex;
    margin: 0 auto;
}
div.left {
    width: 50%;
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
    width: 50%;
    /* float: right; */
    box-sizing: border-box;

    /* background-color: blue; */
}
</style>