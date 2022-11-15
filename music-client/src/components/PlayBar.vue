<template>
  <div
    class="play-bar"
    @mouseup="mouseup"
    @mouseleave="mouseleave"
    :class="{ show: !toggle }"
  >
    <div @click="toggle = !toggle" class="item-up" :class="{ turn: !toggle }">
      <svg style="width: 20px; height: 20px">
        <use xlink:href="#icon-jiantou-xia-cuxiantiao"></use>
      </svg>
    </div>
    <div class="kongjian">
      <div class="progress-box">
        <div ref="progress" class="progress" @mousemove="mousemove">
          <!-- 进度条 -->
          <div ref="bg" class="bg" @click="updatemove">
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
            ></div>
          </div>
        </div>
      </div>
      <div class="kongjian-box">
        <div class="chang-music">
          <!-- 歌曲相关信息 -->
          <div class="item">
            <!-- 歌曲图片 -->
            <div class="item-img" @click="toLyric">
              <img :src="songPic" />
            </div>
          </div>
          <!-- 歌曲名 -->
          <div class="item-song-title">
            <div>{{ this.songName }}</div>
            <div>{{ this.singerName }}</div>
          </div>
          <!-- 上一首 -->
          <div class="item" @click="prev">
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
          <div class="item" @click="next">
            <svg class="icon">
              <use xlink:href="#icon-ziyuanldpi1"></use>
            </svg>
          </div>
        </div>
        <!-- 播放进度 -->
        <div class="playing-speed">
          <div class="time">
            <!-- 播放开始时间 -->
            <div class="current-time">{{ nowTime }}/</div>

            <!-- 播放结束时间 -->
            <div class="current-time">{{ songTime }}</div>
          </div>

          <!-- 音量 -->
          <div class="item item-volume">
            <svg class="icon">
              <use xlink:href="#icon-yinliang1"></use>
            </svg>
            <el-slider
              class="volume"
              v-model="num"
              :vertical="true"
            ></el-slider>
          </div>
          <!-- 收藏 -->
          <div class="item" @click="collection">
            <svg :class="{ active: isCollect }" class="icon">
              <use xlink:href="#icon-xihuan-shi"></use>
            </svg>
          </div>
          <!-- 下载 -->
          <div class="item" @click="collection">
            <svg class="icon">
              <use xlink:href="#icon-xiazai"></use>
            </svg>
          </div>
          <!-- 当前播放的歌曲列表 -->
          <div class="item" @click="changeAside">
            <svg class="icon">
              <use xlink:href="#icon-liebiao"></use>
            </svg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { setCollect, getCollectOfUserId, delCollect } from "@/assets/api/index";
import { mixin } from "@/mixins/index.js";
export default {
  name: "PlayBar",
  mixins: [mixin],
  data() {
    return {
      picUrl: "",
      nowTime: "00:00", //当前播放进度的时间
      songTime: "00:00", //当前歌曲总时间
      curLength: 0, //进度条的位置
      progressLength: 0, //进度条的总长度
      mouseStartX: 0, //拖动进度条后的位置
      tag: false, //拖动开始，结束的标志；开始拖动时为true，其他任何时刻为false
      num: 0,
      toggle: true, //播放器的显示与隐藏
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
    //音量变化
    num() {
      this.$store.commit("setVolume", this.num / 100);
    },
    autoNext() {
      this.next();
    },
  },
  computed: {
    ...mapGetters([
      "id",
      "url",
      "Login", //用户是否已经登录
      "userId", //当前登录的用户id
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
      "showAside", //是否显示歌曲列表
      "listIndex", //当前歌曲在歌单中的位置

      "isCollect", //当前播放的歌曲是否已经收藏
    ]),
  },
  mounted() {
    this.progressLength = this.$refs.progress.getBoundingClientRect().width;
    document.querySelector(".item-volume").addEventListener(
      "click",
      (e) => {
        document.querySelector(".volume").classList.add("show-volume");
        e.stopPropagation();
      },
      false
    );
    document.querySelector(".item-volume").addEventListener(
      "click",
      (e) => {
        e.stopPropagation();
      },
      false
    );
    //点击页面空白处，音量条消失
    // document.addEventListener(
    //   "click",
    //   (e) => {
    //     document.querySelector(".volume").classList.remove("show-volume");
    //     e.stopPropagation();
    //   },
    //   false
    // );
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
      this.$store.commit("setisPlay", true);
    },
    //正在拖动
    mousemove(e) {
      if (!this.duration) {
        return false;
      }
      if (this.tag) {
        this.$store.commit("setisPlay", false);
        let movementX = e.clientX - this.mouseStartX; //移动后的位置-开始拖动的位置=移动了多少
        let curLength = this.$refs.curProgress.getBoundingClientRect().width;

        let newPercent = ((movementX + curLength) / this.progressLength) * 100;
        if (newPercent > 100) {
          newPercent = 100;
        } else if (newPercent < 0) {
          newPercent = 0;
        }
        this.curLength = newPercent;

        this.mouseStartX = e.clientX;
        this.changTime(newPercent);
        // if (!this.isPlay) {

        // }
      }
    },
    //鼠标离开进度条范围
    mouseleave() {
      this.tag = false;
    },
    //更改歌曲进度
    changTime(percent) {
      let newCurTime = percent * 0.01 * this.duration;
      this.$store.commit("setChangeTime", newCurTime);
    },
    //点击播放条，切换播放进度
    updatemove(e) {
      if (!this.tag) {
        //进度条的左侧坐标
        let curLength = this.$refs.bg.offsetLeft;
        let newPercent = ((e.clientX - curLength) / this.progressLength) * 100;
        if (newPercent > 100) {
          newPercent = 100;
        } else if (newPercent < 0) {
          newPercent = 0;
        }
        this.curLength = newPercent;
        this.changTime(newPercent);
        if (!this.isPlay) {
          this.$store.commit("setisPlay", true);
        }
      }
    },
    //显示歌曲列表
    changeAside() {
      this.$store.commit("setShowAside", true);
    },
    //上一首
    prev() {
      //没有歌，即索引为-1和歌曲只有一首时不执行
      if (this.listIndex != -1 && this.listOfSongs.length > 1) {
        //切换上一首
        if (this.listIndex > 0) {
          this.$store.commit("setListIndex", this.listIndex - 1);
        }
        //第一首的情况，切换到倒数第一首
        else {
          this.$store.commit("setListIndex", this.listOfSongs.length - 1);
        }
        this.toPlay(this.listOfSongs[this.listIndex].url);
      }
    },
    //下一首
    next() {
      //没有歌，即索引为-1和歌曲只有一首时不执行
      if (this.listIndex != -1 && this.listOfSongs.length > 1) {
        //切换下一首
        if (this.listIndex < this.listOfSongs.length - 1) {
          this.$store.commit("setListIndex", this.listIndex + 1);
        }
        //最后一首的情况，切换到第一首
        else {
          this.$store.commit("setListIndex", 0);
        }
        this.toPlay(this.listOfSongs[this.listIndex].url);
      }
    },
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
    toPlay(url) {
      if (url && url != this.url) {
        this.$store.commit("setisCollect", false);
        if (this.Login) {
          getCollectOfUserId(this.userId).then((res) => {
            for (let item of res) {
              if (item.songId == this.listOfSongs[this.listIndex].id) {
                this.$store.commit("setisCollect", true);
                break;
              }
            }
          });
        }
        this.$store.commit(
          "setUrl",
          this.$store.state.configure.HOST + "/" + url
        );
        this.$store.commit("setId", this.listOfSongs[this.listIndex].id);
        this.$store.commit("setisPlay", true);
        this.$store.commit(
          "setSongPic",
          this.$store.state.configure.HOST +
            "/" +
            this.listOfSongs[this.listIndex].pic
        );

        this.$store.commit(
          "setSongName",
          this.replaceLName(this.listOfSongs[this.listIndex].name)
        );
        this.$store.commit(
          "setSingerName",
          this.replaceFName(this.listOfSongs[this.listIndex].name)
        );

        this.$store.commit(
          "setLyric",
          this.parseLyric(this.listOfSongs[this.listIndex].lyric)
        );
      }
    },
    //点击图片，转向歌词页面
    toLyric() {
      this.$router.push({ path: `/Lyric` });
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
    //收藏
    collection() {
      if (this.Login) {
        //已收藏过
        if (this.isCollect) {
          delCollect(this.id).then((res) => {
            if (res.code == 1) {
              this.notify("取消成功", "success");
              this.$store.commit("setisCollect", false);
            } else {
              this.notify("取消失败", "error");
            }
          });
        } else {
          let params = new URLSearchParams();
          params.append("userId", this.userId);
          params.append("type", 0);
          params.append("songId", this.id);
          setCollect(params).then((res) => {
            if (res.code == 1) {
              this.notify("收藏成功", "success");
              this.$store.commit("setisCollect", true);
            } else if (res.code == 2) {
              this.notify("已收藏", "warning");
            } else {
              this.notify("收藏失败", "error");
            }
          });
        }
      } else {
        this.notify("请先登录", "warning");
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/play-bar.css";
.kongjian-box {
  display: flex;
  align-items: center;
  flex-direction: row;
  flex-wrap: wrap;
  bottom: 0;
  height: 60px;
  width: 100%;
  min-width: 1000px;
  justify-content: space-between;
}

.kongjian-box .chang-music {
  display: flex;
  align-items: flex-start;
  flex-direction: row;
  flex-wrap: wrap;
  bottom: 0;
  height: 60px;
  width: 50%;
  min-width: 1000px;
}
</style>
