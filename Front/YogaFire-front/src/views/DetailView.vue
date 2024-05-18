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
        <!-- {{ videoId }} -->
        <div class="text-container">
          <div>
            <p>{{ title }}</p>
            <span>{{ channelTitle }}</span>
            <span v-if="video.isFavorite" @click="clickLike(video)">💗</span>
            <span v-else @click="clickLike(video)">🤍</span>
          </div>
          <div>
            <p>{{ description }}</p>
            <p>{{ publishTime }}</p>
            </div>
          </div>
        <!-- <searchVideoDetail /> -->
        <hr>
        <createComment />
        <hr>
        <showCommentList />
    </div>
  </template>
  
  <script setup>
  import searchVideo from '@/components/video/searchVideo.vue'
  import createComment from '@/components/comment/createComment.vue'
  import showCommentList from '@/components/comment/showCommentList.vue'
  
  import { useRoute } from 'vue-router';
  import { useRouter } from 'vue-router';
  import { useVideoStore } from '@/stores/video';
  
  // 추가
  import { computed, ref, onMounted } from 'vue';
  
  const store = useVideoStore()
  
  // 추가
  const router = useRouter();
  const route = useRoute();
  
  
  const videoId = ref(route.params.videoId);

  // ??
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
  
  // 추가
  const fetchVideoDetails = async (videoId) => {
    try {
        const response = await axios.get('https://www.googleapis.com/youtube/v3/videos', {
            params: {
                key: import.meta.env.VITE_YOUTUBE_API_KEY,
                part: 'snippet',
                id: videoId,
            }
        });
        if (response.data.items.length > 0) {
            video.value = response.data.items[0];
        }
    } catch (error) {
        console.error('Error fetching video details:', error);
    }
  };
  
  const videoURL = computed(()=>{
    
  return `https://www.youtube.com/embed/${videoId.value}`;
  })
  
  // 좋아요 상태를 토글하는 함수
  // const clickLike = () => {
  //   isFavorite.value = !isFavorite.value;
  //   // localStorage.setItem('videoData', JSON.stringify({
  //   //   videoId: savedVideoId.value,
  //   //   isFavorite: isFavorite.value
  //   // }));
  //   console.log("click Like:", isFavorite.value)
  // }
  
  // 이거이거이거 이거
  // const clickLike = function(video){
  //     console.log("click Like")
  //     store.clickLike(video)
  //     console.log("click Like store")
  //   // isFavorite.value = !isFavorite.value;
  //   console.log(isFavorite.value)
  //   // localStorage.setItem('isFavorite', JSON.stringify(isFavorite.value))
  //   }
  

  const clickLike = function(video) {
    console.log("click Like")
    isFavorite.value = video.isFavorite;
    localStorage.setItem('isFavorite', JSON.stringify(isFavorite.value))
    // localStorage.setItem('isFavorite', isFavorite.value)
//   store.likeVideo(video)
  // isFavorite.value = video.isFavorite
    store.likeVideo(video)
    console.log("click Like complete", video.isFavorite)
  }
  

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
  console.log(isFavorite.value)
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
  