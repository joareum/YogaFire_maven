import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '@/views/LoginView.vue'
import SignupView from '@/views/SignupView.vue'
import LoginDetail from '@/components/user/LoginDetail.vue'
import SignupDetail from '@/components/user/SignupDetail.vue'
import VideoView from '../views/VideoView.vue'
import SearchView from '../views/SearchView.vue'
import SearchList from '@/components/video/searchListVideo.vue'
import FindId from '@/components/user/FindId.vue'
import CenterView from '../views/CenterView.vue'
import CenterList from '@/components/center/CenterList.vue'
import DietView from '../views/DietView.vue'
import DietDetail from '@/components/diet/DietDetail.vue'
import GptView from '@/components/diet/GptView.vue'
import DetailView from '../views/DetailView.vue'
import createComment from '@/components/comment/createComment.vue'
import showCommentList from '@/components/comment/showCommentList.vue'
import MyPageView from '../views/MyPageView.vue'
import RecommendView from '@/components/video/recommendVideo.vue'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
        },
        {
          path: '/recommend',
          name: 'recommend',
          component: RecommendView
        },
        {
            path: '/login',
            name: 'login',
            component: LoginView,
            children: [
                {
                    path: 'logindetail',
                    name: 'loginDetail',
                    component: LoginDetail
                }
            ]
        },
        {
            path: '/signup',
            name: 'signup',
            component: SignupView,
            children: [
                {
                    path: 'signupdetail',
                    name: 'SignupDetail',
                    component: SignupDetail
                }
            ]
        },
        {
            path: '/video',
            name: 'video',
            component: VideoView
        },
        {
          path: '/detail/:videoId/:title/:channelTitle/:description/:publishTime',
          name: 'detail',
          component: DetailView,
          props: true
          },
        {
            path: '/search',
            // path: '/search/:keyword',
            name: 'search',
            component: SearchView,
            children:[
              {
                path: '',
                name: 'searchList',
                component: SearchList,
              },
            ]
        },
        {
            path: "/findid",
            name: "findid",
            component: FindId,
        },
        {
          path: "/centerView",
          name: "centerView",
          component: CenterView,
          children:[
            {
              path: '',
              name: 'centerList',
              component: CenterList,
            },
          ]
        },
        {
            path: "/dietView",
            name: "dietView",
            component: DietView,
            children:[
              {
                path: '',
                name: 'dietDetail',
                component: DietDetail,
              },
              {
                path: "/gptView",
                name: "gptView",
                component: GptView,
              },
            ]
        },
        {
          path: '/create',
          name: 'createComment',
          component: createComment,
          
        },
        {
          path: '/show',
          name: 'showCommentList',
          component: showCommentList,
          
        },
        {
          path: '/myPage',
          name: 'myPageView',
          component: MyPageView,
          
        },
    ]
})

export default router
