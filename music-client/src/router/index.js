import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '@/views/Home'
import Singer from '@/views/Singer';
import MyMusic from '@/views/MyMusic';
import SongList from '@/views/SongList';
import Search from '@/views/Search';
import Login from '@/views/Login.vue';
import Register from '@/views/Register.vue';


Vue.use(VueRouter);

const routes = [
   { path: '/', redirect: 'Home' },
  { path: '/Home', component: Home },
  { path: '/SongList', component: SongList },
  { path: '/MyMusic', component: MyMusic },
  { path: '/Singer', component: Singer },
  { path: '/Search', component: Search },
  { path: '/Login', component: Login },
     { path: '/Register', component:Register}
]

const router = new VueRouter({
  routes 
});

export default router;

