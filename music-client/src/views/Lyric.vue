<template>
  <div class="lyric-box" style="background-color: rgb(163, 163, 163)">
    <TheHeader></TheHeader>
    <div class="song-lyric">
      <h1 class="lyric-title">歌词</h1>
      <!-- 有歌词显示歌词列表 -->
      <ul class="has-lyric" v-if="lyr.length" key="index">
        <li v-for="(item, index) in lyr" :key="index">
          {{ item[1] }}
        </li>
      </ul>
      <!-- 没有歌词 -->
      <div v-else class="no-lyric" key="no-lyric">
        <span>暂无歌词</span>
      </div>
    </div>
    <TheList></TheList>
    <PlayBar></PlayBar>
    <TheFooter></TheFooter>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mixin } from "@/mixins";

export default {
  name: "Lyric",
  mixins: [mixin],
  data() {
    return {
      lyr: [], //歌曲的歌词
    };
  },
  computed: {
    ...mapGetters([
      "curTime", //当前歌曲播放到的位置
      "id", //当前播放的歌曲id
      "lyric", //歌词
      "listOfSongs", //当前歌曲列表
      "listIndex", //当前歌曲在歌单中的位置
    ]),
  },

  created() {
    this.lyr = this.lyric;
  },
  watch: {
    id() {
      this.lyr = this.parseLyric(this.listOfSongs[this.listIndex].lyric);
    },
    curTime() {
      if (this.lyr.length > 0) {
        for (let i = 0; i < this.lyr.length; i++) {
          if (this.curTime >= this.lyr[i][0]) {
            for (let j = 0; j < this.lyr.length; j++) {
              document.querySelectorAll(".has-lyric li")[j].style.color =
                "#000";
              document.querySelectorAll(".has-lyric li")[j].style.fontSize =
                "15px";
              document.querySelectorAll(".has-lyric li")[
                j
              ].style.backgroundColor = "rgba(116, 116, 116,0)";
            }
            if (i >= 0) {
              document.querySelectorAll(".has-lyric li")[i].style.color =
                "#fff";
              document.querySelectorAll(".has-lyric li")[i].style.fontSize =
                "25px";
              document.querySelectorAll(".has-lyric li")[
                i
              ].style.backgroundColor = "rgba(116, 116, 116,0.6)";
            }
          }
        }
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/lyric.css";
</style>