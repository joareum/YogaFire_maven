<template class="template">
  <div v-if="videoId" class="container">
    <AnotherNav/>
    <!-- <h2>DetailView</h2> -->
    <div class="search">
      <searchVideo />
    </div>
      <div class="video-detail">
    <iframe
      width="650"
      height="400"
      :src="videoURL"
      title="YouTube video player"
      frameborder="0"
      allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
      referrerpolicy="strict-origin-when-cross-origin"
      allowfullscreen>
    </iframe>
  </div>
      <div class="text-container">
        <div>
          <p>{{ title }}</p>
          <span>{{ channelTitle }}</span>
          <br>
          <br>
          <span class="heart" @click="toggleLike" :class="{ 'like': isFavorite }"> 
        {{ isFavorite ? '💗' : '🤍' }} 
      </span> 
        </div>
        <div>
          <!-- 빼도 될 것 같아서 일단 지웁니당. 깔끔하게..! -->
          <!-- <p>{{ description }}</p> -->
          <!-- <p>{{ publishTime }}</p> -->
          </div>
        </div>
      <br>
      <createComment />
      <br>
      <showCommentList />
  </div>
</template>

<script setup>
import axios from 'axios';
import searchVideo from '@/components/video/searchVideo.vue'
import createComment from '@/components/comment/createComment.vue'
import showCommentList from '@/components/comment/showCommentList.vue'
import AnotherNav from '@/components/common/AnotherNav.vue'

import { useRoute, useRouter } from 'vue-router';
import { useVideoStore } from '@/stores/video';
import { computed, ref, onMounted } from 'vue';

const store = useVideoStore()

const router = useRouter();
const route = useRoute();

const videoId = ref(route.params.videoId);

const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
const sessionId = parsedData.loginUser; // loginUser 값 가져오기

const channelTitle = ref(route.params.channelTitle);
const description = ref(route.params.description);
const title = ref(route.params.title);
const publishTime = ref(route.params.publishTime);
const isFavorite = ref(false);

const savedVideoId = ref(null);
const savedisFavorite = ref(null);

const videoByUser = {
sessionId: { sessionId: sessionId.value},
snippet: {
  title: title.value,
  channelTitle: channelTitle.value,
  publishTime: publishTime.value
},
isFavorite: isFavorite.value
};

// like 때문에 쓰는 거임 
const loadData = () => {
try {
  // 로컬 스토리지에서 아이디 불러 옴
  const savedUser = localStorage.getItem('user');
  const parsedUser = JSON.parse(savedUser);

  if (parsedUser && parsedUser.videoId === videoId.value) {
      isFavorite.value = parsedUser.isFavorite;
    }
  } catch (error) {
    console.error('Error loading data from localStorage:', error);
  }
};
  

const fetchVideoDetails = async (videoId) => {
try {
  const response = await axios.get(`http://localhost:8080/video/${videoId}/like/${sessionId}`);
  if (response.data) {
    const videoData = response.data;
    videoId.value = videoData.videoId;
    title.value = videoData.title;
    channelTitle.value = videoData.channelName;
    description.value = videoData.description;
    publishTime.value = videoData.publishTime;
    isFavorite.value = videoData.likeYn;
  }
} catch (error) {
  console.error('Error fetching video details:', error);
}
};

const videoURL = computed(() => {
  return `https://www.youtube.com/embed/${videoId.value}`;
})


const toggleLike = async () => {
try {
  isFavorite.value = !isFavorite.value; // 좋아요 상태 토글

  const updatedUser = {
      loginUser: sessionId,
      videoId: videoId.value,
      isFavorite: isFavorite.value
    };
    localStorage.setItem('user', JSON.stringify(updatedUser));

 
  // API 요청 보내기
  const newVideo = {
    sessionId: sessionId,
    videoId: videoId.value,
    videoTitle: title.value,
    area: '전신',
    channelName: channelTitle.value,
    regDate: publishTime.value,
    centerName: channelTitle.value,
    likeYn: isFavorite.value
  };
  
  const response = await axios.put(`http://localhost:8080/video/${newVideo.videoId}/like/${newVideo.sessionId}`, newVideo);
  console.log("Like status updated successfully", newVideo);

  // 결과 확인 (디버깅용)
  console.log(JSON.parse(localStorage.getItem('user')))

} catch (error) {
  console.error("Error updating like status", error);
}
};

// 윗단이 수정된 clickLike
const clickUpload = async (video) => {
console.log("click Upload")
// const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
// const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
// const sessionId = parsedData.loginUser; // loginUser 값 가져오기
// console.log(sessionId)  
await store.uploadVideo(video)
console.log("click Upload complete")
// console.log(videoId)
}

onMounted(() => {

loadData()

// 컴포넌트가 마운트될 때 'isFavorite' 값을 로드하는지 확인
// console.log(isFavorite.value)
clickUpload({
  id: { videoId: videoId.value },
  sessionId: sessionId.value,
  snippet: {
    title: title.value,
    channelTitle: channelTitle.value,
    publishTime: publishTime.value
  },
  isFavorite: isFavorite.value
  })
})

</script>

<style scoped>
.search{
  margin-top:30px ;
}
.text-container{
  margin-top: -25px;
}

iframe{
  margin-top: -5px;
}
.container {
display: flex;
flex-direction: column;
align-items: center;
text-align: center;
}

.video-detail {
/* margin: 0 auto; */

margin: 35px;
}

hr {
width: 100%;
}

h3 {
margin-bottom: 20px;
}
</style>