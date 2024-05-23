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
import { useCommentStore } from '@/stores/comment';
import { useRoute } from 'vue-router';

const store = useCommentStore();
const route = useRoute();
const routevideoId = ref(route.params.videoId);

const editingCommentId = ref(null); // 현재 수정 중인 댓글의 ID
const editedCommentContent = ref(''); // 수정 중인 댓글의 내용
const originalCommentContent = ref(''); // 수정하기 전 댓글의 원래 내용

onMounted(async () => {
  await store.getComment(routevideoId.value);
});

const comments = computed(() => store.outComment);

// localStorage에서 'user' 키의 값을 가져옴
const userString = localStorage.getItem('user');
// JSON 문자열을 JavaScript 객체로 파싱
const userObject = JSON.parse(userString);
// 객체에서 'loginUser' 값을 가져옴
const loginUser = userObject.loginUser;

const deleteComment = async (videoId, commentId, commentUserId) => {
  if (loginUser === commentUserId) {
    await store.deleteComment(videoId, commentId);
    await store.getComment(routevideoId.value); // 댓글 목록 업데이트
  } else {
    alert('본인의 댓글만 삭제할 수 있습니다.');
  }
};

const formatDate = (dateStr) => {
  const date = new Date(dateStr);
  const formattedDate = `${date.getFullYear()}-${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
  return formattedDate;
};

const saveEdit = async (videoId, vCommentId) => {
  await store.reComment(videoId, vCommentId, editedCommentContent.value);
  await store.getComment(routevideoId.value); // 댓글 목록 업데이트
  editingCommentId.value = null;
  editedCommentContent.value = '';
};

const startEditing = (commentId, currentContent, commentUserId) => {
  if (loginUser === commentUserId) {
    editingCommentId.value = commentId;
    editedCommentContent.value = currentContent;
    originalCommentContent.value = currentContent; // 원래 댓글 내용 저장
  } else {
    alert('본인의 댓글만 수정할 수 있습니다.');
  }
};

const cancelEdit = () => {
  editedCommentContent.value = originalCommentContent.value; // 원래 댓글 내용으로 복원
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
  min-height: 35vh;
  width: 100%;
  margin-bottom: 50px;
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
  margin-bottom: 2%;
  padding-left: 18px;
  align-items: baseline;
}

.id, .date, .button {
  flex: 2; /* 가로 너비를 늘리기 위해 flex 값 증가 */
  text-align: center;
  display: flex; /* 높이를 맞추기 위해 flex 박스로 설정 */
  align-items: baseline; /* 요소를 가운데 정렬 */
  justify-content: center; /* 내용 가운데 정렬 */
}

.content {
  flex: 3; /* 가로 너비를 늘리기 위해 flex 값 증가 */
  padding: 0 20px;
  display: flex;
  /* align-items: baseline; */
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
  width: 100%;
  height: 100px;
  border: 2px solid #ff7c7c;
  outline: none;
  margin-bottom: 7%;
  font-size: 16px;
  padding: 10px;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  resize: none;
  transition: border-color 0.3s, box-shadow 0.3s;
}

textarea:focus {
  border-color: #ff5252;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}
</style>