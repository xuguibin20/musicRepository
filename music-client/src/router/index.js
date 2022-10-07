import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home.vue'
import Singer from '@/views/Singer.vue';
import MyMusic from '@/views/MyMusic.vue';
import SongList from '@/views/SongList.vue';
import Search from '@/views/Search.vue';

Vue.use(VueRouter);

const routes = [
   { path: '/', redirect: 'Home' },
  { path: '/Home', component: Home },
  { path: '/SongList', component: SongList },
  { path: '/MyMusic', component: MyMusic },
  { path: '/Singer', component: Singer },
   { path: '/Search', component:Search}
]

const router = new VueRouter({
  routes 
});

export default router;

