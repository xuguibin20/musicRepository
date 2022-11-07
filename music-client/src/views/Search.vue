<template>
  <div class="search-box">
    <TheHeader></TheHeader>
    <div class="search">
      <nav class="searchList-nav" ref="change">
        <span
          :class="{ isActive: toggle == 'Song' }"
          @click="handleChangeView('Song')"
          >歌曲</span
        >
        <span
          :class="{ isActive: toggle == 'SongList' }"
          @click="handleChangeView('SongList')"
          >歌单</span
        >
      </nav>
      <component :is="currentView"></component>
    </div>
    <ScrollTop></ScrollTop>
    <TheList></TheList>
    <PlayBar></PlayBar>
    <TheFooter></TheFooter>
  </div>
</template>
<script>
import SearchSongList from "@/components/search/SearchSongList.vue";
import SearchSong from "@/components/search/SearchSong.vue";
export default {
  name: "Search",
  data() {
    return {
      toggle: "Song",
      currentView: "SearchSong",
    };
  },
  methods: {
    //切换组件
    handleChangeView(component) {
      this.currentView = "Search" + component;
      this.toggle = component;
    },
  },
  watch: {
    $route(to, from) {
      this.$router.go(0);
    },
  },
  components: {
    SearchSongList,
    SearchSong,
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/search.css";
</style>
