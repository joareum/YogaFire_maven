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


const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            name: 'home',
            component: HomeView
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
            component: FindId
          },
    ]
})

export default router