import Vue from "vue";
import VueRouter from "vue-router";
import Login from "@/views/Login";
import InfoPage from "@/views/InfoPage";
import Home from "@/components/Home";
import ConsumerPage from "@/views/ConsumerPage";
import SingerPage from "@/views/SingerPage";
import SongListPage from "@/views/SongListPage";
import ListSongPage from "@/views/ListSongPage";
import SongPage from "@/views/SongPage";
import Collect from "@/views/Collect";
Vue.use(VueRouter);

const routes = [
  { path: "/", redirect: "Login" },
  { path: "/Login", component: Login },

  {
    path: "/Home",
    component: Home,
    children: [
      { path: "/Info", component: InfoPage },
      { path: "/Consumer", component: ConsumerPage },
      { path: "/SongList", component: SongListPage },
      { path: "/ListSong", component: ListSongPage },
      { path: "/Singer", component: SingerPage },
      { path: "/Song", component: SongPage },
      { path: "/Collect", component: Collect },
    ],
  },
];

const router = new VueRouter({
  routes,
});

export default router;
