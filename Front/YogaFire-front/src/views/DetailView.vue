<template>
  <div class="base">
    <div class="nav">
      <a href="http://localhost:5173">
        <img src="@/assets/yogafire_pink.png" style="max-width: 85px; height: auto;">
      </a>
      <div class="sub-nav">
        <SubNav />
      </div>
    </div>

    <div v-if="videoId" class="container"></div>

    <div class="search">
      <searchVideo />
    </div>
    <div class="video-content">
      <div class="video-detail">
        <iframe width="700" height="393.75" :src="videoURL" title="YouTube video player" frameborder="0"
          allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
          referrerpolicy="strict-origin-when-cross-origin" allowfullscreen>
        </iframe>
      </div>
      <div class="text-container">
        <div>
          <h3 class="channel-title">{{ title }}</h3>
          <div class="profile-channel-container">

            <span class="profile-empty"></span><span class="channel-name">{{ channelTitle }}</span>

            
            <span class="heart" @click="toggleLike">
              {{ likeCount > 0 ? '💗' : '🤍' }}
            </span>
          </div>
          <div class="content-part">
            <div class="channel-time">최초 공개: {{ formattedPublishTime }}</div>
            <div>{{ description }}</div>

          </div>
        </div>
      </div>
    </div>
    <div class="total-comment-area">
      <createComment />
      <showCommentList />
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import searchVideo from '@/components/video/searchVideo.vue';
import createComment from '@/components/comment/createComment.vue';
import showCommentList from '@/components/comment/showCommentList.vue';
import SubNav from '@/components/common/SubNav.vue';

import { useRoute, useRouter } from 'vue-router';
import { useVideoStore } from '@/stores/video';
import { computed, ref, onMounted } from 'vue';

const store = useVideoStore();

const router = useRouter();
const route = useRoute();

const videoId = ref(route.params.videoId);

const storedData = localStorage.getItem('user');
const parsedData = JSON.parse(storedData);
const sessionId = parsedData.loginUser;

const channelTitle = ref(route.params.channelTitle);
const description = ref(route.params.description);
const title = ref(route.params.title);
const publishTime = ref(route.params.publishTime);
const isFavorite = ref(false);

const savedVideoId = ref(null);
const savedisFavorite = ref(null);

const videoByUser = {
  sessionId: { sessionId: sessionId.value },
  snippet: {
    title: title.value,
    channelTitle: channelTitle.value,
    publishTime: publishTime.value
  },
  isFavorite: isFavorite.value
};

const loadData = () => {
  try {
    const savedUser = localStorage.getItem('user');
    const parsedUser = JSON.parse(savedUser);

    if (parsedUser && parsedUser.videoId === videoId.value) {
      isFavorite.value = parsedUser.isFavorite;
    }
  } catch (error) {
    console.error('Error loading data from localStorage:', error);
  }
};

const videoURL = computed(() => {
  return `https://www.youtube.com/embed/${videoId.value}`;
});

const formattedPublishTime = computed(() => {
  if (publishTime.value) {
    const date = new Date(publishTime.value);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}년 ${month}월 ${day}일`;
  }
  return '';
});

const isVideoId = ref(route.params.videoId); // 좋아요할 때 쓸 것
const likeCount = ref(0)

const isLike = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/video/${isVideoId.value}/isLike/${sessionId}`)
    likeCount.value = response.data // 0 이상이면 Like된 것, 0 이면 Like 안된 것
    console.log(likeCount.value)
  } catch (err) {
    console.error("isLike 에러", err)
  }
}

onMounted(() => {
  isLike()
});

const toggleLike = async () => {
  console.log(likeCount.value + '<- 이 값이 0 이상이면 Like된 것, 0 이면 Like 안된 것')
  try {
    if(sessionId == ""){
      alert('비회원은 찜 기능을 사용할 수 없습니다. 로그인 해주세요!')
    } else if (likeCount.value === 0) {
      console.log(likeCount.value + '<- 이 값이 0 이상이면 Like된 것, 0 이면 Like 안된 것')
      // isFavorite.value = !isFavorite.value;

      const updatedUser = {
        loginUser: sessionId,
        videoId: videoId.value,
      };
      localStorage.setItem('user', JSON.stringify(updatedUser));


      const newVideo = {
        sessionId: sessionId,
        videoId: videoId.value,
      };

      // vLikeVideo
      const response = await axios.get(`http://localhost:8080/video/${newVideo.videoId}/like/${newVideo.sessionId}`)
        .then(response => {
          console.log('video_like 추가 완료' + response.data + '<- 1이면 추가 완료 아니면 아님'); // 이 값까지 갔는데 왜 갑자기 catch로 가지..? / 1이 되어버렸음
        })
        .catch(error => {
          if (error.response && error.response.status === 404) {
            alert('비회원은 찜 기능을 사용할 수 없습니다. 로그인 해주세요.');
          } else if (error.request) {
            // 요청은 보냈지만 응답을 받지 못한 경우 (CORS 오류)
            alert('비회원은 찜 기능을 사용할 수 없습니다. 로그인 해주세요.');
          } else {
            // 요청을 보내기 전에 오류 발생
            console.error('An error occurred:', error);
          }
        });


      likeCount.value = 1;
      isFavorite.value = true;

    } else {
      console.log(likeCount)
      await unLike();

      // Update the like count and isFavorite status
      likeCount.value = 0;
      isFavorite.value = false;
    }

  } catch (error) {
    console.error('An error occurred:', error);
    if (error.response && error.response.status === 404) {
      alert('비회원은 찜 기능을 사용할 수 없습니다. 로그인 해주세요.');
    } else if (error.request) {
      // 요청은 보냈지만 응답을 받지 못한 경우 (CORS 오류)
      alert('비회원은 찜 기능을 사용할 수 없습니다. 로그인 해주세요.');
    } else {
      // 요청을 보내기 전에 오류 발생
      console.error('An error occurred:', error);
    }
  }
};


const unLike = async () => {
  try {
    await axios.delete(`http://localhost:8080/video/${isVideoId.value}/like/${sessionId}`)
    console.log("좋아요 취소 성공")
  } catch (err) {
    console.error("좋아요 취소 실패")
  }
}


const clickUpload = async (video) => {
  console.log("click Upload");
  await store.uploadVideo(video);
  console.log("click Upload complete");
};



const shortDescriptionLength = 100; // 간략한 설명의 길이
const showFullDescription = ref(false); // 전체 설명을 보여줄지 여부

const shortDescription = computed(() => {
  return description.value.slice(0, shortDescriptionLength);
});

const toggleDescription = () => {
  showFullDescription.value = !showFullDescription.value;
};

</script>

<style scoped>
.profile-channel-container {
  display: flex;
  align-items: center;
  margin-bottom: 4%;
}

.profile-empty {
  display: inline-block;
  width: 30px;
  height: 30px;
  background-color: rgb(255, 221, 221);
  border-radius: 50%;
  border: 1px solid rgb(164, 154, 154);
  margin-right: 14px;
}

.channel-name {
  font-size: 17px;
  margin-right: auto; /* Ensures there's space between the channel name and the like button */
}

.heart {
  font-size: 22px;
  margin-left: auto;
  margin-right: 10px;
}

/* Additional styles for other elements */
.nav {
  display: flex;
  align-items: center;
  margin-top: 0px;
}

.nav a {
  margin-right: 20px;
}

.sub-nav {
  margin-left: auto;
}

.base {
  margin: 20px;
  padding-left: 45px;
  padding-right: 40px;
}

.search {
  margin-top: 30px;
  text-align: center;
}

.video-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.video-detail {
  display: flex;
  justify-content: center;
  margin: 20px;
}

.text-container {
  width: 700px;
  text-align: left;
  margin-top: -2%;
  padding-left: 1px;
}

iframe {
  border-radius: 13px;
}

.channel-title {
  margin-top: 4%;
  font-size: 20px;
  margin-bottom: 4%;
}

.content-part {
  margin-top: 4%;
  padding: 4%;
  background-color: rgba(203, 202, 202, 0.326);
  border-radius: 18px;
  height: 100px;
  margin-bottom: 8%;
}

.channel-time {
  font-size: 14px;
  margin-bottom: 4%;
}

hr {
  width: 100%;
}

h3 {
  margin-top: 2%;
}

.total-comment-area {
  background: linear-gradient(to bottom,
      rgba(245, 228, 222, 0) 12%,
      rgba(245, 228, 222, 0.4) 30%,
      rgba(245, 228, 222, 0.5) 75%,
      rgba(245, 228, 222, 0.6) 100%,
      rgba(245, 228, 222, 0.8) 100%,
      rgba(245, 228, 222, 1) 100%);
  border-radius: 14px;
  padding: 3%;
  width: 740px;
  min-height: 500px;
  margin: 0 auto;
}
</style>
