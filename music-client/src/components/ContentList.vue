<template>
  <div class="content-list">
    <ul class="section-content">
      <li
        class="content-item"
        v-for="(item, index) in contentList"
        :key="index"
      >
        <!-- 歌手有item.name，而歌单没有；借此来判断点击的是歌单还是歌手 -->
        <div class="kuo" @click="goAlbum(item)">
          <img class="item-img" :src="attachImageUrl(item.pic)" />
          <div class="mask">
            <svg class="icon">
              <use xlink:href="#icon-bofang"></use>
            </svg>
          </div>
        </div>
        <p class="item-name">{{ item.name || item.title }}</p>
      </li>
    </ul>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index.js";
export default {
  mixins: [mixin],
  name: "ContentList",
  props: ["contentList"],
  data() {
    return {};
  },
  methods: {
    goAlbum(item) {
      this.$store.commit("setTempList", item);
      //转向歌手页面
      if (item.name != null) {
        this.$router.push({ path: `/SingerAlbum/${item.id}` });
      }
      //转向歌单页面
      else {
        this.$router.push({ path: `/SongListAlbum/${item.id}` });
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/content-list.css";
</style>
