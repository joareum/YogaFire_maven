import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'

// axios.defaults.headers['Access-Control-Allow-Origin'] = 'http://localhost:8080';

// axios.defaults.withCredentials = true;

export const useVideoStore = defineStore('video', () => {
  const videos = ref([])
  const selectedVideo = ref(null)
  const recommendVideos = ref([])
  const isFavorite = ref(false)

  const getVideosBySession = async function() {
    try {
      const response = await apiClient.get('/video/session');
      return response.data;
    } catch (error) {
      console.error('Error fetching videos by session:', error);
      throw error;
    }
  };

  const videoSearch = function(keyword){
    const URL = 'https://www.googleapis.com/youtube/v3/search'
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

    axios({
      url:URL,
      method: 'GET',
      params:{
        key:API_KEY,
        part:'snippet',
        maxResults: 12,
        q: keyword,
        type:'video'
      },
    })
    .then((response)=>{
      console.log(response.data)
      videos.value = response.data.items
      console.log(response.data)
    })
    .catch((error)=>{
      console.log(error)
    })
  }
  const uploadVideo = function(video) {
    const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
    const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
    const sessionId = parsedData.loginUser; // loginUser 값 가져오기
    
    if (!video || !video.snippet || (!video.id && !video.videoId)|| !sessionId) {
      console.error("Invalid video object or missing stored id", video);
      return;
    }
  
    const videoId = video.id?.videoId || video.videoId; // video.id.videoId 또는 video.videoId 둘 다 처리
  
    const newVideo = {
      sessionId: sessionId,
      videoId: videoId,
      videoTitle: video.snippet.title,
      area: '전신',
      channelName: video.snippet.channelTitle,
      regDate: video.snippet.publishTime,
      centerName: video.snippet.channelTitle,
      likeYn: video.isFavorite
    };
  
    axios({
      url: `http://localhost:8080/video/${newVideo.videoId}`,
      method: 'POST',
      data: newVideo,
      headers: {
        'Content-Type': 'application/json'
      }

    })
    .then(() => {
      console.log("Video uploaded successfully", newVideo);
    })
    .catch((err) => {
      console.error("Error uploading video", err);
    });
  };


  const videoRecommend = function(){
    const URL = 'https://www.googleapis.com/youtube/v3/search'
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

    axios({
      url:URL,
      method: 'GET',
      params:{
        key:API_KEY,
        part:'snippet',
        maxResults: 8,
        q: '요가 영상 추천',
        type:'video'
      },
    })
    .then((response)=>{
      recommendVideos.value = response.data.items
      console.log(response.data)
    })
    .catch((error)=>{
      console.log(error)
    })
  }

  const clickVideo = function(video){
    console.log(video)
    console.log('localStorage 확인')
    console.log(video.videoId)
    localStorage.setItem('videoId', video.videoId)

    console.log(localStorage.getItem('videoId'))
  }

  const clickLike = (video) => {
    console.log(video)
    // video.isFavorite = !video.isFavorite;
    // isFavorite.value = !isFavorite.value;
    // localStorage.setItem('isFavorite', video.isFavorite)
    localStorage.setItem('isFavorite', JSON.stringify(video.isFavorite))
    console.log("click Like:", localStorate.getItem('isFavorite'))

  }


  const likeVideo = function(video) {
    const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
    const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
    const sessionId = parsedData.loginUser; // loginUser 값 가져오기
    const likeYn = video.isFavorite;
  
    
    if (!video || !video.snippet || (!video.id && !video.videoId)|| !sessionId) {
      console.error("Invalid video object or missing stored id", video);
      return;
    }
  
    const videoId = video.id?.videoId || video.videoId; // video.id.videoId 또는 video.videoId 둘 다 처리
  
    const newVideo = {
      sessionId: sessionId,
      videoId: videoId,
      videoTitle: video.snippet.title,
      area: '전신',
      channelName: video.snippet.channelTitle,
      regDate: video.snippet.publishTime,
      centerName: video.snippet.channelTitle,
      // likeYn: isFavorite.value == false
      // likeYn: isFavorite.value
      // likeYn: video.isFavorite
      likeYn: likeYn
    };
  
    axios({
      url: `http://localhost:8080/video/${newVideo.videoId}/like`,
      method: 'PUT',
      data: newVideo,
      headers: {
        'Content-Type': 'application/json'
      }

    })
    .then(() => {
      console.log("Video uploaded successfully", newVideo);
    })
    .catch((err) => {
      console.error("Error uploading video", err);
    });
  };



  return { 
    getVideosBySession,
    videoSearch,
    videos,
    selectedVideo,
    clickVideo,
    videoRecommend,
    clickLike,
    recommendVideos,
    likeVideo,
    uploadVideo,
    isFavorite, }
})
