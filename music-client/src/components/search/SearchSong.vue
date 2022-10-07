<template>
  <div class="search-songs">
    <AlbumContent :songList="listOfSongs"></AlbumContent>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mixin } from "@/mixins/index.js";
import AlbumContent from "@/components/AlbumContent.vue";
export default {
  name: "SearchSong",
  mixins: [mixin],
  data() {
    return {};
  },

  computed: { ...mapGetters(["listOfSongs"]) },
  mounted() {
    this.getSong();
  },
  components: {
    AlbumContent,
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/search-songs.css";
</style>
