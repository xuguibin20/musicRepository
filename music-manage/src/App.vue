<template>
  <div id="app">
    <SongAudio></SongAudio>
    <router-view />
  </div>
</template>

<script>
import SongAudio from "@/components/SongAudio.vue";
export default {
  name: "App",
  components: { SongAudio },
};
</script>
<style lang="less">
@import "@/assets/css/main.css";
</style>
