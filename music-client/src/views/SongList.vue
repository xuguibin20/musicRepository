<template>
  <div class="song-list-box">
    <TheHeader></TheHeader>

    <ul class="song-list-header">
      <li
        v-for="(item, index) in songStyle"
        :key="index"
        @click="handleChangView(item.name)"
        :class="{ active: item.name == activeName }"
      >
        {{ item.name }}
      </li>
    </ul>

    <ContentList :contentList="data"></ContentList>
    <div class="pagination">
      <el-pagination
        @current-change="handleCurrentChange"
        background
        layout="total,prev,pager,next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="albumDatas.length"
      >
      </el-pagination>
    </div>

    <ScrollTop></ScrollTop>
    <PlayBar></PlayBar>
    <User></User>
  </div>
</template>
<script>
import ContentList from "@/components/ContentList.vue";
import { getAllSongList, getSongListOfLikeStyle } from "@/assets/api/index";
import { mixin } from "@/mixins/index.js";
import { songStyle } from "@/assets/data/songStyle";
export default {
  name: "SongList",
  mixins: [mixin],
  components: {
    ContentList,
  },
  mounted() {
    this.getSongList();
    this.songStyle = songStyle;
    this.activeName = "全部歌单";
  },
  computed: {
    //计算当前搜索结果表里的数据
    data() {
      return this.albumDatas.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
    },
  },
  data() {
    return {
      albumDatas: [], //歌单数据
      pageSize: 10, //一页有多少条数据
      currentPage: 1, //当前的页数
      songStyle, //歌单风格
      activeName: "全部歌单", //当前选中的风格
    };
  },
  methods: {
    //获取所有歌单
    getSongList() {
      getAllSongList().then((res) => {
        this.currentPage = 1;
        this.albumDatas = res;
      }).catch;
      (err) => {
        console.log(err);
      };
    },
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //根据Style显示对应的歌单
    handleChangView(name) {
      this.activeName = name;
      this.albumDatas = [];
      if (name == "全部歌单") {
        this.getSongList();
      } else {
        this.getSongListOfStyle(name);
      }
    },
    //根据style模糊查询歌单
    getSongListOfStyle(style) {
      getSongListOfLikeStyle(style).then((res) => {
        this.currentPage = 1;
        this.albumDatas = res;
      });
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/song-list.css";
</style>
