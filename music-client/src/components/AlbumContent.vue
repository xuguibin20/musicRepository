<template>
  <div class="content">
    <h1 class="title">
      <slot name="title"></slot>
    </h1>
    <ul>
      <li>
        <div class="song-item">
          <span class="item-index"></span>
          <span class="item-title">歌曲名</span>
          <span class="item-name">歌手</span>
          <span class="item-intro">专辑</span>
        </div>
      </li>
      <li v-for="(item, index) in songList" :key="index">
        <div class="song-item">
          <span class="item-index">{{ index + 1 }}</span>
          <span class="item-title">{{ repalceFName(item.name) }}</span>
          <span class="item-name">{{ repalceLName(item.name) }}</span>
          <span class="item-intro">{{ item.introduction }}</span>
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index.js";
export default {
  mixins: [mixin],
  name: "AlbumContent",
  props: ["songList"],
  data() {
    return {};
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/album-content.css";
</style>
