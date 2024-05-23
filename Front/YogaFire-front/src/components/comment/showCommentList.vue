<template>
  <div class="comment-list-container">
    <!-- <h3>showCommentList</h3> -->
    <div class="comment-list">
      <li v-for="comment in comments" :key="comment.vCommentId">
        <div class="all">
          <div class="id">{{ comment.userId ? comment.userId : '비회원' }} </div>
          <div class="content">
            <template v-if="comment.vCommentId !== editingCommentId">
              {{ comment.vCommentContent }}
            </template>

            <template v-else>
              <textarea v-model="editedCommentContent"></textarea>
            </template>
          </div>

          <div class="date">{{ formatDate(comment.vCommentReg) }}</div>

          <div class="button">
            <button v-if="comment.vCommentId === editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="cancelEdit">취소</button>

            <button v-if="comment.vCommentId === editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="saveEdit(comment.videoId, comment.vCommentId)">저장</button>
            
            <button v-if="comment.vCommentId !== editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="deleteComment(comment.videoId, comment.vCommentId, comment.userId)">삭제</button>
            
            <button v-if="comment.vCommentId !== editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="startEditing(comment.vCommentId, comment.vCommentContent, comment.userId)">수정</button>
              <!-- ↺ -->
          </div>

        </div>
      </li>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import { useCommentStore } from '@/stores/comment'
import { useRoute } from 'vue-router';

const store = useCommentStore();
const route = useRoute();
const routevideoId = ref(route.params.videoId);

const editingCommentId = ref(null); // 현재 수정 중인 댓글의 ID
const editedCommentContent = ref(''); // 수정 중인 댓글의 내용
const originalCommentContent = ref(''); // 수정하기 전 댓글의 원래 내용

onMounted(async () => {
  await store.getComment(routevideoId.value)
  comments.value = store.outComment
})

const comments = computed(() => store.outComment);

// localStorage에서 'user' 키의 값을 가져옴
const userString = localStorage.getItem('user');
// JSON 문자열을 JavaScript 객체로 파싱
const userObject = JSON.parse(userString);
// 객체에서 'loginUser' 값을 가져옴
const loginUser = userObject.loginUser;

const deleteComment = function (videoId, commentId, commentUserId) {

  if(loginUser === commentUserId){
    store.deleteComment(videoId, commentId);
  } else{
    alert('본인의 댓글만 삭제할 수 있습니다.');
  }
}

const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
  return formattedDate;
}

const saveEdit = async (videoId, vCommentId) => {
  await store.reComment(videoId, vCommentId, editedCommentContent.value);
  editingCommentId.value = null;
  editedCommentContent.value = '';
  await store.getComment(routevideoId.value); // 댓글 목록 업데이트
};


const startEditing = (commentId, currentContent, commentUserId) => {
  if(loginUser === commentUserId){
    console.log(commentId)
    console.log(editingCommentId.value) // null
    editingCommentId.value = commentId;
    console.log(editingCommentId.value) // 동일
    editedCommentContent.value = currentContent;
  }else{
    alert('본인의 댓글만 수정할 수 있습니다.');
  }
}; 

// const startEditing = (commentId) => {
//   const comment = comments.value.find(comment => comment.id === commentId);
//   editedCommentContent.value = comment.vCommentContent;
//   editingCommentId.value = commentId;
// };


const cancelEdit = () => {
  editedCommentContent.value = originalCommentContent.value
  editingCommentId.value = null;
  editedCommentContent.value = '';
};

</script>

<style scoped>
.comment-list-container {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  min-height: 35vh; /* 최소 높이 설정 */
  width: 100%;
  margin-bottom: 50px; /* 아래쪽 마진 추가 */
}

.comment-list {
  width: 100%;
}

li {
  list-style-type: none;
  text-align: left;
}

.all {
  display: flex;
  padding-bottom: 5px;
  width: 100%;
  padding-left: 110px;
}

.content {
  margin-left: 20px; /* 왼쪽 마진을 더 넓게 설정 */
  margin-right: 100px; /* 오른쪽 마진을 더 넓게 설정 */
  flex: 1;
}

.date {
  margin-right: 30px;
  flex: 1;
}

.button {
  margin-right: 50px;
  flex: 1;
}

.id {
  text-align: center;
  margin-right: 3%;
  flex: 1;
}

.content {
  display: flex;
  align-items: center;
  /* flex-grow: 2; */
}

.date {
  margin-left: 50px;
  /* flex-grow: 1.5; */
}

.button {
  text-align: left;
  flex-grow: 1;
}

/* 버튼 디자인 */

* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  text-align: center;
  padding: 100px;
  background: whitesmoke;
  display: table-cell;
}

button {
  margin: 0px;
}

.input-box {
  width: 500px;
  height: 46px;
  margin-left: 20px;
  border-color: gray;
}

.w-btn {
  height: 28px;
  width: 42px;
  margin-right: 3%;
  position: relative;
  border: none;
  display: inline-block;
  /* padding: 15px 30px; */
  border-radius: 13px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  font-size: 13.5px;
}

.w-btn-gra2 {
  background: linear-gradient(-45deg, #f58969, #e45c90, #f27b66, #ff2c2c);
  color: white;
}

.w-btn-gra-anim {
  background-size: 400% 400%;
  animation: gradient1 5s ease infinite;
}

@keyframes gradient1 {
  0% {
    background-position: 0% 50%;
  }

  50% {
    background-position: 100% 50%;
  }

  100% {
    background-position: 0% 50%;
  }
}

textarea {
  width: 100%; /* 좌우 넓이 설정 */
  height: 100px; /* 높이 설정 */
  border: 2px solid #ff7c7c;
  /* margin-right: -10px !important; */
  outline: none;
  margin-bottom: 7%;
  font-size: 16px;
  padding: 10px; /* 안쪽 여백 추가 */
  border-radius: 8px; /* 모서리를 둥글게 */
  background-color: #f9f9f9; /* 배경색 추가 */
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
  resize: none; /* 크기 조절 비활성화 */
  transition: border-color 0.3s, box-shadow 0.3s; /* 전환 효과 추가 */
}

textarea:focus {
  border-color: #ff5252; /* 포커스 시 테두리 색 변경 */
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15); /* 포커스 시 그림자 효과 변경 */
}

</style>
