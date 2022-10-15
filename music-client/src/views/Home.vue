<template>
  <div class="home">
    <Swiper></Swiper>
    <div class="section" v-for="(item, index) in songList" :key="index">
      <div class="section-title">{{ item.name }}</div>
      <ContentList :contentList="item.list"></ContentList>
    </div>
  </div>
</template>

<script>
import Swiper from "@/components/Swiper.vue";
import ContentList from "@/components/ContentList.vue";
import { getAllSinger, getAllSongList } from "@/assets/api/index.js";

export default {
  name: "Home",
  created() {
    this.getSinger();
    this.getSongList();
  },

  data() {
    return {
      songList: [
        { name: "歌单", list: [] },
        { name: "歌手", list: [] },
      ],
    };
  },
  methods: {
    //获取前十条歌单
    getSongList() {
      getAllSongList().then((res) => {
        this.songList[0].list = res.slice(0, 10);
      }).catch;
      (err) => {
        console.log(err);
      };
    },
    //获取前十名歌手
    getSinger() {
      getAllSinger().then((res) => {
        this.songList[1].list = res.slice(0, 10);
      }).catch;
      (err) => {
        console.log(err);
      };
    },
  },
  components: {
    Swiper,
    ContentList,
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/home.css";
</style>
