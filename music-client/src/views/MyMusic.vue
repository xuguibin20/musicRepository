<template>
  <div class="my-music-box">
    <TheHeader></TheHeader>
    <div class="my-music">
      <div class="album-slide">
        <div class="album-img">
          <img :src="attachImageUrl(avator)" alt="" />
        </div>
        <ul class="info1">
          <li>名称{{ username }}</li>
          <li>性别：{{ userSex }}</li>
          <li>生日：{{ birth }}</li>
          <li>地区:{{ location }}</li>
        </ul>
      </div>
      <div class="album-content">
        <div class="album title">个性签名：{{ introduction }}</div>
        <div class="songs-body">
          <AlbumContent :songList="collectList"></AlbumContent>
        </div>
      </div>
    </div>
    <ScrollTop></ScrollTop>
    <PlayBar></PlayBar>
    <User></User>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index.js";
import AlbumContent from "@/components/AlbumContent.vue";

import { mapGetters } from "vuex";
import {
  getUserOfId,
  getCollectOfUserId,
  songOfSongId,
} from "@/assets/api/index";
export default {
  name: "MyMusic",
  components: {
    AlbumContent,
  },
  mixins: [mixin],
  computed: {
    ...mapGetters([
      "listOfSongs", //当前播放列表
      "tempList", //当前歌手对象
      "Login", //用户是否登录
      "userId", //当前登录的用户id
    ]),
  },
  data() {
    return {
      avator: "", //用户头像
      username: "", //用户名称
      userSex: "", //性别
      birth: "", //生日
      location: "", //地区
      introduction: "", //签名
      collection: [], //收藏的歌曲列表
      collectList: [], //收藏的歌曲列表（带歌曲详情）
    };
  },
  mounted() {
    this.getMsg(this.userId);
    this.getCollection(this.userId);
  },
  methods: {
    //获取个人信息
    getMsg(userId) {
      getUserOfId(userId)
        .then((res) => {
          this.avator = res.avator;
          this.username = res.username;
          if (res.sex == 0) {
            this.userSex = "女";
          } else if (res.sex == 1) {
            this.userSex = "男";
          } else {
            this.userSex = "不明";
          }
          this.birth = this.attachBirth(res.birth);
          this.location = res.location;

          this.introduction = res.introduction;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //获取我的收藏列表
    getCollection(userId) {
      getCollectOfUserId(userId)
        .then((res) => {
          this.collection = res;
          //通过歌曲id获取歌曲信息
          for (let item of this.collection) {
            this.getSongsOfId(item.songId);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //通过歌曲id获取歌曲信息
    getSongsOfId(id) {
      songOfSongId(id)
        .then((res) => {
          this.collectList.push(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/my-music.css";
</style>
