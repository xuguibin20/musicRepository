import { likeSongOfName } from "@/assets/api/index.js";

export const mixin = {
  methods: {
    //提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type,
      });
    },
    //获取图片地址
    attachImageUrl(srcUrl) {
      return srcUrl
        ? this.$store.state.configure.HOST + "/" + srcUrl
        : this.$store.state.configure.HOST + "/img/mn.jpg";
    },
    //根据歌名模糊查询歌曲
    getSong() {
      if (!this.$route.query.keywords) {
        this.$store.commit("setListOfSongs", []);
        this.notify("您输入的内容为空", "warning");
      } else {
        likeSongOfName(this.$route.query.keywords)
          .then((res) => {
            if (!res.length) {
              this.$store.commit("setListOfSongs", []);
              this.notify("系统暂无符合条件的歌曲", "warning");
            } else {
              this.$store.commit("setListOfSongs", res);
            }
          })
          .catch((err) => {
            console.log(err);
          });
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
    toPlay(id, url, pic, index, name, lyric) {
      this.$store.commit(
        "setUrl",
        this.$store.state.configure.HOST + "/" + url
      );
      this.$store.commit("setId", id);
      this.$store.commit("setisPlay", true);
      this.$store.commit(
        "setSongPic",
        this.$store.state.configure.HOST + "/" + pic
      );
      this.$store.commit("setListIndex", index);
      this.$store.commit("setSongName", this.replaceLName(name));
      this.$store.commit("setSingerName", this.replaceFName(name));
      this.$store.commit("setLyric", this.parseLyric(lyric));
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
