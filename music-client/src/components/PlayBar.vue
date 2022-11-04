<template>
  <div class="play-bar">
    <div class="kongjian">
      <!-- 上一首 -->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi"></use>
        </svg>
      </div>
      <!-- 播放按钮 -->
      <div class="item" @click="togglePlay">
        <svg class="icon">
          <use :xlink:href="playButtonUrl"></use>
        </svg>
      </div>
      <!-- 下一首 -->
      <div class="item">
        <svg class="icon">
          <use xlink:href="#icon-ziyuanldpi1"></use>
        </svg>
      </div>
      <!-- 歌曲图片 -->
      <div class="item-img">
        <img :src="songPic" />
      </div>
      <!-- 播放进度 -->
      <div class="playing-speed">
        <!-- 播放开始时间 -->
        <div class="current-time">{{ nowTime }}</div>
        <!-- 播放条整体 -->
        <div class="progress-box">
          <!-- 歌曲名 -->
          <div class="item-song-title">
            <div>{{ this.songName }}</div>
            <div>{{ this.singerName }}</div>
          </div>
          <div ref="progress" class="progress" @mousemove="mousemove">
            <!-- 进度条 -->
            <div ref="bg" class="bg">
              <div
                ref="curProgress"
                class="cur-progress"
                :style="{ width: curLength + '%' }"
              ></div>
              <!-- 拖动点 -->
              <div
                ref="idot"
                class="idot"
                :style="{ left: curLength + '%' }"
                @mousedown="mousedown"
                @mouseup="mouseup"
              ></div>
            </div>
          </div>
        </div>
        <!-- 播放结束时间 -->
        <div class="current-time">{{ songTime }}</div>
        <!-- 音量 -->
        <div class="item item-volume">
          <svg class="icon">
            <use xlink:href="#icon-yinliang1"></use>
          </svg>
        </div>
        <!-- 收藏 -->
        <div class="item">
          <svg class="icon">
            <use xlink:href="#icon-xihuan-shi"></use>
          </svg>
        </div>
        <!-- 下载 -->
        <div class="item">
          <svg class="icon">
            <use xlink:href="#icon-xiazai"></use>
          </svg>
        </div>
        <!-- 当前播放的歌曲列表 -->
        <div class="item">
          <svg class="icon">
            <use xlink:href="#icon-liebiao"></use>
          </svg>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";

export default {
  name: "PlayBar",
  data() {
    return {
      picUrl: "",
      nowTime: "00:00", //当前播放进度的时间
      songTime: "00:00", //当前歌曲总时间
      curLength: 0, //进度条的位置
      progressLength: 0, //进度条的总长度
      mouseStartX: 0, //拖动进度条后的位置
      tag: false, //拖动开始，结束的标志；开始拖动时为true，其他任何时刻为false
    };
  },
  watch: {
    isPlay() {
      if (this.isPlay) {
        this.$store.commit("setplayButtonUrl", "#icon-zanting");
      } else {
        this.$store.commit("setplayButtonUrl", "#icon-bofang");
      }
    },
    curTime() {
      this.nowTime = this.formatTime(this.curTime);
      this.songTime = this.formatTime(this.duration);
      this.curLength = (this.curTime / this.duration) * 100;
    },
  },
  computed: {
    ...mapGetters([
      "id",
      "url",
      "songName", //歌名
      "singerName", //歌手名字
      "playButtonUrl", //播放状态的图片
      "isPlay", //播放状态  getters里的？相当于java的getter方法
      "listOfSongs", //当前歌曲列表
      "duration", //音乐的时长
      "curTime", //当前歌曲播放的时间
      "changeTime", //指定播放时刻
      "autoNext", //当前歌曲播放完毕自动播放下一首
      "volume", //音量
      "songPic", //正在播放的音乐图片
    ]),
  },
  mounted() {
    this.progressLength = this.$refs.progress.getBoundingClientRect().width;
  },
  methods: {
    //控制音乐的播放或暂停
    togglePlay() {
      if (this.isPlay) {
        this.$store.commit("setisPlay", false);
        this.$store.commit("setplayButtonUrl", "#icon-bofang");
      } else {
        this.$store.commit("setisPlay", true);
        this.$store.commit("setplayButtonUrl", "#icon-zanting");
      }
    },
    //格式化时间---到秒
    formatTime(value) {
      let time = parseInt(value);
      let result = "";
      let hour = parseInt(time / 3600); //小时
      let minute = parseInt((time / 60) % 60); //分钟
      let second = parseInt(time % 60); //秒
      if (hour > 0) {
        if (hour < 10) {
          result = "0" + hour + ":";
        } else {
          result = hour + ":";
        }
      }
      if (minute > 0) {
        if (minute < 10) {
          result += "0" + minute + ":";
        } else {
          result += minute + ":";
        }
      } else {
        //miute=0时
        result += "00:";
      }
      if (second > 0) {
        if (second < 10) {
          result += "0" + second;
        } else {
          result += second;
        }
      } else {
        //miute=0时
        result += "00";
      }
      return result;
    },
    //开始拖动
    mousedown(e) {
      this.mouseStartX = e.clientX;
      this.tag = true;
    },
    //结束拖动
    mouseup() {
      this.tag = false;
    },
    //正在拖动
    mousemove(e) {
      if (!this.duration) {
        return false;
      }
      if (this.tag) {
        let movementX = e.clientX - this.mouseStartX; //移动后的位置-开始拖动的位置=移动了多少
        let curLength = this.$refs.curProgress.getBoundingClientRect().width;

        let newPercent = ((movementX + curLength) / this.progressLength) * 100;
        if (newPercent > 100) {
          newPercent = 100;
        }
        this.curLength = newPercent;

        this.mouseStartX = e.clientX;
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/play-bar.css";
</style>