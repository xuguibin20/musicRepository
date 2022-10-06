import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import InfoPage from '@/views/InfoPage.vue'
import Home from '@/components/Home.vue'
import ConsumerPage from '@/views/ConsumerPage.vue'
import SingerPage from '@/views/SingerPage.vue'
import SongListPage from '@/views/SongListPage.vue'
import SongPage from '@/views/SongPage.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: 'Login' },
  { path: '/Login', component: Login },
 
  {
    path: '/Home', component: Home,
    children: [
           { path: '/Info', component: InfoPage },
           { path: '/Consumer', component: ConsumerPage },
           { path: '/SongList', component: SongListPage },
      { path: '/Singer', component: SingerPage },
      { path: '/Song', component: SongPage },
    ]
  },
  
]

const router = new VueRouter({
  routes
})

export default router
