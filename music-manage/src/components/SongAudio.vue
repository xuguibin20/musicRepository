<template>
  <div class="song-audio">
    <audio
      muted
      id="player"
      :src="url"
      controls="controls"
      @canplay="startPlay"
      @ended="ended"
    ></audio>
  </div>
</template>

<script>
import { mapGetters } from "vuex"; //可以监控store里的值
export default {
  name: "SongAudio",
  data() {
    return {};
  },
  watch: {
    //监听播放还是暂停
    isPlay() {
      this.togglePlay();
    },
  },
  computed: {
    ...mapGetters([
      "id",
      "url",
      "isPlay", //getters里的？
    ]),
  },
  methods: {
    //获取链接后准备播放
    startPlay() {
      let player = document.querySelector("#player");
      //开始播放
      if (this.isPlay) {
        player.play();
      }
    },
    //播放完成之后触发
    ended() {
      this.$store.commit("setisPlay", false); //store里的'isPlay'
    },
    //开始，暂停
    togglePlay() {
      let player = document.querySelector("#player");
      if (this.isPlay) {
        player.play();
      } else {
        player.pause();
      }
    },
  },
};
</script>

<style lang="less">
</style>
