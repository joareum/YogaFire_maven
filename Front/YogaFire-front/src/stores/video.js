import { ref, computed, onMounted } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080'
});

const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
const parsedData = storedData ? JSON.parse(storedData) : null; // JSON 문자열을 객체로 파싱하기

// 기본값 설정
const sessionId = parsedData ? parsedData.loginUser : null;


export const useVideoStore = defineStore('video', () => {
  const videos = ref([])
  const selectedVideo = ref(null)
  const recommendVideos = ref([])
  const isFavorite = ref(false)

  const getVideosBySession = async (sessionId) => {
    try {
      const response = await apiClient.get('/video/session', {
        params: { sessionId: sessionId }});
        return response.data;
      
    } catch (error) {
      console.error('Error fetching videos by session:', error);
      throw error;
    }
  };

  const videoSearch = async (keyword) => {
    const URL = 'https://www.googleapis.com/youtube/v3/search'
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

    try {
      const response = await axios.get(URL, {
        params: {
          key: API_KEY,
          part: 'snippet',
          maxResults: 12,
          q: keyword,
          type: 'video'
        },
      })
        videos.value = response.data.items
        console.log(response.data)
      } catch(error) {
        console.log(error)
      }
  }



  const uploadVideo = async (video) => {

    const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
    const parsedData = storedData ? JSON.parse(storedData) : null; // JSON 문자열을 객체로 파싱하기
    const sessionId = parsedData ? parsedData.loginUser : null; // loginUser 값 가져오기


    if (!video || !video.snippet || (!video.id && !video.videoId)) {
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
      likeYn: video.isFavorite,
    };

    try {
      await apiClient.post(`/video/${newVideo.videoId}`, newVideo)
      console.log("Video uploaded successfully", newVideo);
    } catch (err) {
      console.error("Error uploading video", err);
    }
  }

  const videoRecommend = async () => {
    const URL = 'https://www.googleapis.com/youtube/v3/search'
    const API_KEY = import.meta.env.VITE_YOUTUBE_API_KEY

    try {
      const response = await axios.get(URL, {
        params: {
          key: API_KEY,
          part: 'snippet',
          maxResults: 20,
          q: '요가 영상 추천',
          type: 'video'
      }
    })
      recommendVideos.value = response.data.items
      } catch (error) {
        console.log(error)
      }
  }

  const clickVideo = (video) => {
    console.log(video)
    console.log('clickVideo: localStorage 확인')
    console.log(video.videoId)
    localStorage.setItem('videoId', video.videoId)
    console.log(localStorage.getItem('videoId'))
  }

  const clickLike = (video) => {
    console.log(video)
    console.log('clickLike: localStorage 확인')
    localStorage.setItem('isFavorite', JSON.stringify(video.isFavorite))
    console.log("click Like:", localStorate.getItem('isFavorite'))

  }

  const likeVideo = async (videoByUser) => {
    const likeYn = videoByUser.isFavorite;

    const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
    const parsedData = storedData ? JSON.parse(storedData) : null; // JSON 문자열을 객체로 파싱하기
    const sessionId = parsedData ? parsedData.loginUser : null; // loginUser 값 가져오기


    if (!videoByUser || !videoByUser.snippet || (!videoByUser.id && !videoByUser.videoId) || !sessionId) {
      console.error("Invalid video object or missing stored id", videoByUser);
      return;
    }

    const videoId = videoByUser.id?.videoId || videoByUser.videoId; // video.id.videoId 또는 video.videoId 둘 다 처리

    const newVideo = {
      sessionId: sessionId,
      videoId: videoId,
      videoTitle: videoByUser.snippet.title,
      area: '전신',
      channelName: videoByUser.snippet.channelTitle,
      regDate: videoByUser.snippet.publishTime,
      centerName: videoByUser.snippet.channelTitle,
      likeYn: videoByUser.isFavorite
    };

    try {
      await apiClient.get(`/video/${newVideo.videoId}/like/${newVideo.sessionId}`, newVideo)
      console.log("Like status updated successfully", newVideo)
      isFavorite.value = newVideo.likeYn;
      localStorage.setItem('isFavorite', JSON.stringify(newVideo.likeYn))
    } catch (err) {
      console.error("Error updating like status", err)
    }
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
  }
})