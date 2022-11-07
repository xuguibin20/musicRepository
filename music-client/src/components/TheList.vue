<template>
  <transition name="slide-fade">
    <div class="the-aside" v-if="showAside">
      <h2 class="title">播放列表</h2>
      <ul class="menus">
        <li
          v-for="(item, index) in listOfSongs"
          :key="index"
          :class="{ 'is-play': id == item.id }"
          @click="
            toPlay(item.id, item.url, item.pic, index, item.name, item.lyric)
          "
        >
          {{ replaceLName(item.name) }}
        </li>
      </ul>
    </div>
  </transition>
</template>
<script>
import { mapGetters } from "vuex";
export default {
  name: "TheList",
  computed: {
    ...mapGetters([
      "showAside", //是否显示歌曲列表
      "listOfSongs", //当前歌曲列表
      "id", //播放中的音乐id
    ]),
  },

  methods: {
    //获取名字前半部分-即歌手名
    replaceFName(str) {
      let arr = str.split("-");
      return arr[0];
    }, //获取名字后半部分-即歌名
    replaceLName(str) {
      let arr = str.split("-");

      return arr[1];
    },
    //播放
    toPlay(id, url, pic, index, name, lyric) {
      this.$store.commit(
        "setUrl",
        this.$store.state.configure.HOST + "/" + url
      );
      this.$store.commit("setId", id);
      this.$store.commit("setLyric", this.parseLyric(lyric));
      this.$store.commit("setisPlay", true);
      this.$store.commit(
        "setSongPic",
        this.$store.state.configure.HOST + "/" + pic
      );
      this.$store.commit("setListIndex", index);
      this.$store.commit("setSongName", this.replaceLName(name));
      this.$store.commit("setSingerName", this.replaceFName(name));
    },
    //解析歌词
    parseLyric(text) {
      let lines = text.split("\n"); // 划分歌词的每一行
      let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g; //提取时间格式
      let result = []; //返回一个数组
      // 歌词格式不是时间+歌词的，直接返回
      if (!/\[.+\]/.test(text)) {
        return [[0, text]];
      }
      //去掉前面格式不正确的歌词
      while (!pattern.test(lines[0])) {
        lines = lines.slice(1);
      }
      //遍历每一行的歌词，存进一个元素是时间，另一个元素是歌词的数组
      for (let item of lines) {
        //存时间
        let time = item.match(pattern);
        //存歌词
        let lyric = item.replace(pattern, "");
        for (let key of Object.keys(time)) {
          //按冒号分割时间格式,存进数组
          let t = time[key].slice(1, -1).split(":");
          if (lyric != "") {
            result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), lyric]);
          }
        }
      }
      //按照第一个元素--时间--排序
      result.sort((a, b) => {
        return a[0] - b[0];
      });
      return result;
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/the-aside.css";
</style>
