<template class="template">
    <div v-if="videoId" class="container">
      <h3>DetailView</h3>
        <searchVideo />
        <div class="video-detail">
      <iframe
        width="560"
        height="315"
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
            <span @click="toggleLike" :class="{ 'like': isFavorite }"> 
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

  // video 객체 생성
  const video = {
  id: { videoId: videoId.value },
  sessionId: { sessionId: sessionId.value},
  snippet: {
    title: title.value,
    channelTitle: channelTitle.value,
    publishTime: publishTime.value
  },
  isFavorite: isFavorite.value
  };
  
  const loadData = () => {
  try {
    const savedVideoId = localStorage.getItem('videoId');
    const savedIsFavorite = localStorage.getItem('isFavorite');
    console.log('완료')
    console.log(savedVideoId)
    if (savedVideoId) {
      videoId.value = savedVideoId;
    }
    if (savedIsFavorite !== null) {
      isFavorite.value = JSON.parse(savedIsFavorite);
    }
    
  } catch (error) {
    console.error('Error loading data from localStorage:', error);
  }
  };

  const fetchVideoDetails = async (videoId) => {
  try {
    const response = await axios.get(`http://localhost:8080/video/${newVideo.videoId}/like`);
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
  
  const videoURL = computed(()=>{
    
  return `https://www.youtube.com/embed/${videoId.value}`;
  })


const toggleLike = async () => {
  try {
    isFavorite.value = !isFavorite.value; // 좋아요 상태 토글
    const newVideo = {
      sessionId: parsedData.loginUser,
      videoId: videoId.value,
      videoTitle: title.value,
      area: '전신',
      channelName: channelTitle.value,
      regDate: publishTime.value,
      centerName: channelTitle.value,
      likeYn: isFavorite.value
    };
    const response = await axios.put(`http://localhost:8080/video/${newVideo.videoId}/like`, newVideo);
    console.log("Like status updated successfully", newVideo);

    // 좋아요 상태가 변경되었으므로, 로컬 스토리지에 저장
    localStorage.setItem('isFavorite', JSON.stringify(isFavorite.value)); // 변경된 부분

  } catch (error) {
    console.error("Error updating like status", error);
  }
};


// 윗단이 수정된 clickLike
  const clickUpload = function(video) {
  console.log("click Upload")
  const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
  const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
  const sessionId = parsedData.loginUser; // loginUser 값 가져오기
  console.log(sessionId)  
  store.uploadVideo(video)
  console.log("click Upload complete")
  // console.log(videoId)
  }

  onMounted(() => {
  loadData()
  // 컴포넌트가 마운트될 때 'isFavorite' 값을 로드하는지 확인
  // console.log(isFavorite.value)
  clickUpload(video)
  })
  
  </script>
  
  <style scoped>
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
  