import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const baseURL = 'http://localhost:8080/video'

export const useCommentStore = defineStore('comment', () => {
    const insertComment = function (videoId, comment) {
        // const videoId = video.id?.videoId || video.videoId; // video.id.videoId 또는 video.videoId 둘 다 처리

        const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
        const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
        const sessionId = parsedData.loginUser; // loginUser 값 가져오기

        const newComment = {
            vCommentContent: comment,
            videoId: videoId, // video에서 가져오기
            userId: sessionId, // 로컬 스토리지에서 가져오기
        };

        axios({
            url: `${baseURL}/${videoId}/comment`,
            method: 'POST',
            data: newComment,
        })
        .then(()=>{
            console.log("댓글이 성공적으로 입력되었습니다.", newComment);
        }).catch((err)=>{
            console.error("댓글 입력에 오류가 있었습니다.", err);
        });
    };

    // showCommentList에서 이걸 출력해야 함
    const outComment = ref([])

    const getComment = function(videoId){
        const insertVideoId = {
            videoId : videoId,
        }

        axios({
            url: `${baseURL}/${videoId}/comment`,
            method: GET,
            data: insertVideoId,
        })
        .then((response)=>{
            console.log(response.data)
            outComment.value = response.data
        })
        .catch((error)=>{
            console.log(error)
        })
    }

    return { insertComment, outComment, getComment }
})