<template>
    <div>
      <h1>{{ sessionId }} 회원님의 찜한 영상 리스트</h1>
      <div v-if="videos.length">
        <div v-for="video in videos" :key="video.videoId" class="video-item-container">
          <div class="video-item">
            <iframe :src="`https://www.youtube.com/embed/${video.videoId}`" frameborder="0"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen></iframe>
            <div class="video-info">
              <p class="video-title">{{ video.videoTitle }}</p>
              <p class="channel-name">{{ video.channelName }}</p>
            </div>
          </div>
        </div>
      </div>
      <div v-else>
        <p>찜하신 영상이 없습니다.</p>
      </div>
    </div>
  </template>
  

<script setup>
import { useVideoStore } from '@/stores/video';
import { ref, onMounted } from 'vue';

// 로컬 스토리지에서 sessionId 가져오기
const storedData = localStorage.getItem('user');
const parsedData = JSON.parse(storedData);
const sessionId = parsedData.loginUser;

const store = useVideoStore();

const videos = ref([]);


onMounted(async () => {
  try {
    const videoList = await store.getVideosBySession(sessionId);
    videos.value = videoList;
  } catch (error) {
    console.error('Error fetching videos by session:', error);
  }
});
</script>

<style scoped>
/* 컨테이너 스타일 */
.video-item-container {
  margin-bottom: 20px; /* 영상 사이 마진 */
}

/* 비디오 항목 스타일 */
.video-item {
  display: flex;
  align-items: center;
  padding: 15px;
  border: 1px solid #e0e0e0; /* 연한 테두리 색상 */
  border-radius: 8px; /* 모서리 둥글게 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 효과 */
  background-color: #ffffff; /* 배경색 */
  transition: transform 0.3s; /* 전환 효과 */
}

/* 비디오 항목 마우스 오버 효과 */
.video-item:hover {
  transform: scale(1.02); /* 마우스 오버 시 확대 */
}

/* iframe 스타일 */
iframe {
  width: 560px;
  height: 315px;
  border-radius: 8px; /* 모서리 둥글게 */
  border: 1px solid #e0e0e0; /* 연한 테두리 색상 */
}

/* 비디오 정보 스타일 */
.video-info {
  display: flex;
  flex-direction: column;
  justify-content: flex-start; /* 텍스트를 위쪽에 배치 */
  margin-left: 20px; /* 왼쪽 간격 */
  padding-top: 5px; /* 상단 패딩 추가 */
  flex: 1; /* flex-grow */
}

/* 비디오 제목 스타일 */
.video-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 5px;
}

/* 채널 이름 스타일 */
.channel-name {
  font-size: 14px;
  color: #757575; /* 텍스트 색상 */
  margin-top: 3px; /* 추가 간격 */
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
