<template>
    <div class="nav">
      <a href="http://localhost:5173">
        <img src="@/assets/yogafire_pink.png" class="logo" @click="goHome">
      </a>
      <div class="sub-nav">
        <SubNav />
      </div>
    </div>
    
    <div class="content">
      <LikeVideo />
    </div>
  </template>

<script setup>
import LikeVideo from '@/components/like/LikeVideo.vue'
import SubNav from '@/components/common/SubNav.vue'
</script>

<style scoped>
/* 전체 레이아웃 스타일 */
.nav {
  display: flex;
  justify-content: space-between; /* 양쪽 끝으로 정렬 */
  padding: 20px; /* 상하 좌우 패딩 추가 */
  margin: 10px 20px; /* 상하 10px 좌우 20px 마진 */
}

.nav a {
  margin-right: 20px;
}

.logo {
  max-width: 85px;
  height: auto;
  transition: transform 0.3s; /* 애니메이션 효과 추가 */
}

.logo:hover {
  transform: scale(1.1); /* 마우스 오버 시 확대 */
}

.sub-nav {
  margin-left: auto;
}

/* 콘텐츠 영역 스타일 */
.content {
  margin: 20px;
  padding: 20px 45px; /* 상하 20px 좌우 45px 패딩 */
  background-color: #ffffff; /* 흰색 배경 */
  border-radius: 10px; /* 모서리 둥글게 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

/* 기본 폰트 스타일 */
body {
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
  color: #333; /* 텍스트 색상 */
  background-color: #f9f9f9; /* 전체 배경색 */
  margin: 0; /* 기본 마진 제거 */
  padding: 0; /* 기본 패딩 제거 */
  box-sizing: border-box;
}

/* 링크 스타일 */
a {
  text-decoration: none;
  color: #ff69b4; /* 핑크 색상 */
  font-weight: bold;
  transition: color 0.3s; /* 색상 전환 효과 */
}

a:hover {
  color: #ff1493; /* 마우스 오버 시 색상 변경 */
}

/* 버튼 스타일 */
button {
  background-color: #ff69b4; /* 핑크색 배경 */
  color: #fff; /* 흰색 텍스트 */
  border: none;
  padding: 10px 20px; /* 상하 10px 좌우 20px 패딩 */
  border-radius: 5px; /* 모서리 둥글게 */
  cursor: pointer;
  font-size: 16px; /* 폰트 크기 */
  transition: background-color 0.3s; /* 배경색 전환 효과 */
}

button:hover {
  background-color: #ff1493; /* 마우스 오버 시 배경색 변경 */
}

/* 기타 스타일 */
* {
  box-sizing: border-box;
}

img {
  max-width: 100%;
  height: auto;
}
</style>
