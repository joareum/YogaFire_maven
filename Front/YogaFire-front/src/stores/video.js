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
      console.log(response)
      videos.value = response.data.items
      console.log(response.data)
    })
    .catch((error)=>{
      console.log(error)
    })
  }

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
    selectedVideo.value = video
  }

  return { videoSearch, videos, selectedVideo, clickVideo, videoRecommend, recommendVideos }
})
