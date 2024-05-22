<template>
  <div>
      <h1>{{ sessionId }} 회원님의 찜한 영상 리스트</h1>
      <div v-if="videos.length">
          <div v-for="video in videos" :key="video.videoId">
              <div class="video-item">
                  <iframe :src="`https://www.youtube.com/embed/${video.videoId}`" width="560" height="315" frameborder="0"
                      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                      allowfullscreen></iframe>
                  <div class="video-info">
                      <p>{{ video.videoTitle }}</p>
                      <p>{{ video.channelName }}</p>
                  </div>
              </div>
              <!-- 영상 사이 마진 -->
              <br> 
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
.video-item {
    display: flex;
    align-items: center;
}

.video-info {
    margin-left: 20px; /* 원하는 간격으로 조정 */
}
</style>