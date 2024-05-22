<template>
  <div>
    <!-- <h3>showCommentList</h3> -->
    <div class="commentList">
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
              @click="saveEdit(comment.videoId, comment.vCommentId)">Save</button>
            
            <button v-if="comment.vCommentId === editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="cancelEdit">Cancel</button>
            
              <button v-if="comment.vCommentId !== editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="deleteComment(comment.videoId, comment.vCommentId)">X</button>
            <button v-if="comment.vCommentId !== editingCommentId" class="w-btn w-btn-gra2 w-btn-gra-anim" type="button"
              @click="startEditing(comment.vCommentId, comment.vCommentContent)">↺</button>
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

const deleteComment = function (videoId, commentId) {
  store.deleteComment(videoId, commentId);
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


const startEditing = (commentId, currentContent) => {
  console.log(commentId)
  console.log(editingCommentId.value) // null
  editingCommentId.value = commentId;
  console.log(editingCommentId.value) // 동일
  editedCommentContent.value = currentContent;
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
li {
  list-style-type: none;
  text-align: left;
}

.all {
  display: flex;
  padding-bottom: 5px;
  width: 800px;
  padding-left: 110px;
}

.content,
.date,
.button {
  margin-right: 50px;
  flex: 1;
}

.id {
  margin-right: 0px;
  flex: 1;
}

.content {
  display: flex;
  align-items: center;
  margin-right: 50px;
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
  height: 30px;
  width: 45px;
  position: relative;
  border: none;
  display: inline-block;
  /* padding: 15px 30px; */
  border-radius: 15px;
  font-family: "paybooc-Light", sans-serif;
  box-shadow: 0 15px 35px rgba(0, 0, 0, 0.2);
  text-decoration: none;
  font-weight: 600;
  transition: 0.25s;
  font-size: 18px;
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

/* 버튼 끝 */</style>