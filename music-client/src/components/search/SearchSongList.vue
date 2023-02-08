<template>
  <div class="search-song-Lists">
    <ContentList :contentList="albumDatas"></ContentList>
  </div>
</template>
<script>
import ContentList from "@/components/ContentList.vue";
import { getSongListOfLikeTitle } from "@/assets/api/index";
import { mixin } from "@/mixins/index.js";
export default {
  name: "SearchSongList",
  mixins: [mixin],
  components: {
    ContentList,
  },
  mounted() {
    this.getSearchList();
  },
  data() {
    return { albumDatas: [] };
  },
  methods: {
    getSearchList() {
      if (this.$route.query.kewords) {
        this.notify("您输入的内容为空", "warning");
      } else {
        getSongListOfLikeTitle(this.$route.query.keywords).then((res) => {
          if (res) {
            this.albumDatas = res;
          } else {
            this.notify("暂无该歌曲内容", "warning");
          }
        });
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/search-song-Lists.css";
</style>
