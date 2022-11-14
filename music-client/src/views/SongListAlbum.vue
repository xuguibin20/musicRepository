<template>
  <div class="song-list-album">
    <TheHeader></TheHeader>
    <div class="album-slide">
      <div class="album-img">
        <img :src="attachImageUrl(tempList.pic)" alt="" />
      </div>
      <div class="album-info">
        <h2>简介</h2>
        <span>{{ tempList.introduction }}</span>
      </div>
    </div>
    <div class="album-content">
      <div class="album-title">
        <p>{{ tempList.title }}</p>
      </div>
      <div class="album-score">
        <div>
          <h3>歌单评分：</h3>
          <div>
            <el-rate v-model="average" disabled></el-rate>
          </div>
        </div>
        <span>{{ average * 2 }}</span>
        <div>
          <h3>评价：</h3>
          <div @click="setRank1">
            <el-rate v-model="rank" allow-half show-text></el-rate>
          </div>
        </div>
      </div>
      <div class="songs-body">
        <h2>歌单</h2>
        <hr />
        <AlbumContent :songList="listOfSongs"></AlbumContent>
        <Comment :payId="songListId" :type="1"></Comment>
      </div>
    </div>
    <ScrollTop></ScrollTop>
    <TheList></TheList>
    <PlayBar></PlayBar>
    <User></User>
    <TheFooter></TheFooter>
  </div>
</template>
<script>
import AlbumContent from "@/components/AlbumContent.vue";
import Comment from "@/components/Comment.vue";
import { mapGetters } from "vuex";
import {
  listSongDetail,
  songOfSongId,
  setRank,
  getRankOfSongListId,
} from "@/assets/api/index";
export default {
  name: "SongListAlbum",

  components: {
    AlbumContent,
    Comment,
  },
  computed: {
    ...mapGetters([
      "listOfSongs", //当前播放列表
      "tempList", //当前歌单对象
      "Login", //用户是否登录
      "userId", //当前登录的用户id
    ]),
  },
  data() {
    return {
      songList: [], //当前页面需要展示的歌曲列表
      songListId: "", //前面传来的歌单id
      average: 0, //平均分
      rank: 0, //提交评价的分数
    };
  },
  created() {
    this.songListId = this.$route.params.id;
    this.getSongId();
    this.getRank(this.songListId);
  },
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
    //获取当前歌单的歌曲列表
    getSongId() {
      listSongDetail(this.songListId)
        .then((res) => {
          for (let item of res) {
            this.getSongList(item.songId);
          }
          this.$store.commit("setListOfSsongs", this.songList);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //根据歌曲id获取歌曲信息
    getSongList(id) {
      songOfSongId(id)
        .then((res) => {
          this.songList.push(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //获取歌单评分
    getRank(id) {
      getRankOfSongListId(id)
        .then((res) => {
          this.average = res / 2;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //提交评分
    setRank1() {
      if (this.Login) {
        let params = new URLSearchParams();
        params.append("songListId", this.songListId);
        params.append("consumerId", this.userId);
        params.append("score", this.rank * 2);
        setRank(params)
          .then((res) => {
            if (res.code == 1) {
              this.notify("评分成功", "success");

              this.getRank(this.songListId);
            } else {
              this.notify("评分失败", "error");
            }
          })
          .catch((err) => {
            this.notify("您已经评价过啦！", "error");
          });
      } else {
        this.rank = null;
        this.notify("请先登录", "warning");
      }
    },
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/song-list-album.css";
</style>

