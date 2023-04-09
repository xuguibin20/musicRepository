<template>
  <div id="app">
    <router-view />
    <!-- <TheFooter></TheFooter> -->
    <SongAudio></SongAudio>
  </div>
</template>

<script>
import SongAudio from "@/components/SongAudio.vue";

export default {
  name: "App",
  components: {
    SongAudio,
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/app.css";
</style>
