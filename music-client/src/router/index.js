import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home";
import Singer from "@/views/Singer";
import MyMusic from "@/views/MyMusic";
import SongList from "@/views/SongList";
import Search from "@/views/Search";
import Login from "@/views/Login";
import Register from "@/views/Register";
import Setting from "@/views/Setting";
import SingerAlbum from "@/views/SingerAlbum";
import SongListAlbum from "@/views/SongListAlbum";

import Lyric from "@/views/Lyric";
Vue.use(VueRouter);

const routes = [
  { path: "/", redirect: "/Login" },
  { path: "/Home", component: Home },
  { path: "/SongList", component: SongList },
  { path: "/MyMusic", component: MyMusic },
  { path: "/Singer", component: Singer },
  { path: "/Search", component: Search },
  { path: "/Login", component: Login },
  { path: "/Register", component: Register },
  {
    path: "/Lyric",
    component: Lyric,
  },
  {
    path: "/Setting",
    component: Setting,
  },
  {
    path: "/SingerAlbum/:id",
    component: SingerAlbum,
  },
  {
    path: "/SongListAlbum/:id",
    component: SongListAlbum,
  },
];

const router = new VueRouter({
  routes,
});

export default router;
