<template>
  <div class="song-list-album">
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
      <div class="songs-body">
        <h2>歌单</h2>
        <hr />
        <AlbumContent :songList="listOfSongs"></AlbumContent>
      </div>
    </div>
  </div>
</template>
<script>
import AlbumContent from "@/components/AlbumContent.vue";
import { mapGetters } from "vuex";
import { listSongDetail, songOfSongId } from "@/assets/api/index";
export default {
  name: "SongListAlbum",

  components: {
    AlbumContent,
  },
  computed: {
    ...mapGetters([
      "listOfSongs", //当前播放列表
      "tempList", //当前歌单对象
    ]),
  },
  data() {
    return {
      songList: [], //当前页面需要展示的歌曲列表
      songListId: "", //前面传来的歌单id
    };
  },
  created() {
    this.songListId = this.$route.params.id;
    this.getSongId();
  },
  methods: {
    //获取图片地址
    attachImageUrl(srcUrl) {
      return srcUrl
        ? this.$store.state.configure.HOST + "/" + srcUrl
        : "@/assets/img/mn.jpg";
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
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/song-list-album.css";
</style>

