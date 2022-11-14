<template>
  <div class="singer-album">
    <TheHeader></TheHeader>
    <div class="album-slide">
      <div class="singer-img">
        <img :src="attachImageUrl(singer.pic)" alt="" />
      </div>
      <ul class="info">
        <li>{{ attachSex(singer.sex) }}</li>
        <li>{{ attachBirth(singer.birth) }}</li>
      </ul>
    </div>
  </div>
</template>
<script>
import AlbumContent from "@/components/AlbumContent.vue";
import Comment from "@/components/Comment.vue";
import { mapGetters } from "vuex";
import { songOfSingerId } from "@/assets/api/index";
export default {
  name: "SingerAlbum",
  components: {
    AlbumContent,
    Comment,
  },
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
      songerId: "", //前面传来的歌手id
      songer: {}, //传过来的歌手对象
    };
  },
  created() {
    this.singerId = this.$route.params.id;
    this.singer = this.tempList;
    this.getSongOfSingerId();
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
    //根据歌手id查询歌曲
    getSongOfSingerId() {
      songOfSingerId(this.singerId)
        .then((res) => {
          this.$store.commit("setListOfSongs", res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //对性别的判断
    attachSex(value) {
      if (value == 0) {
        return "女";
      } else {
        return "男";
      }
      return "不明";
    },
    //获取生日
    attachBirth(value) {
      return value.substr(0, 10);
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/singer-album.css";
</style>

