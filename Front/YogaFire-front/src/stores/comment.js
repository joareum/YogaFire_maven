import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'

const baseURL = 'http://localhost:8080/video'
// showCommentList에서 이걸 출력해야 함

export const useCommentStore = defineStore('comment', () => {
    const outComment = ref([])

    const getComment = async function(videoId){
        await axios({
            url: `${baseURL}/${videoId}/comment`,
            method: 'GET',
            params: {
                videoId: videoId // 쿼리 파라미터로 전송
            }
        })
        .then((response)=>{
            outComment.value = response.data
        })
        .catch((error)=>{
            console.error("댓글 가져오는 과정에서 오류가 있었습니다.",error)
        })
    }

    const insertComment = async function (videoId, comment) {
        const storedData = localStorage.getItem('user'); // 로컬 스토리지에서 값 가져오기
        const parsedData = JSON.parse(storedData); // JSON 문자열을 객체로 파싱하기
        const sessionId = parsedData.loginUser; // loginUser 값 가져오기

        const newComment = {
            vCommentContent: comment,
            videoId: videoId, // video에서 가져오기
            userId: sessionId, // 로컬 스토리지에서 가져오기
        };

        await axios({
            url: `${baseURL}/${videoId}/comment`,
            method: 'POST',
            data: newComment,
        })
        .then(async ()=>{
            console.log("댓글이 성공적으로 입력되었습니다.", newComment);
            // outComment.value.push(newComment)
            // store.getComment();
            await getComment(videoId);
        }).catch((err)=>{
            console.error("댓글 입력에 오류가 있었습니다.", err);
        });
    };

    const deleteComment = function(videoId, vCommentId){
        axios({
            url: `${baseURL}/${videoId}/comment/${vCommentId}`,
            method: 'DELETE',
            // params:{
            //     videoId: videoId,
            //     vCommentId: vCommentId,
            // }
        }).then(async ()=>{
            console.log('댓글 삭제 완료');
            await getComment(videoId);
        }).catch((err)=>{
            console.error("댓글 삭제 오류 발생.", err);
        });
    }

    return { insertComment, outComment, getComment, deleteComment }
})