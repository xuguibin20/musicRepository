
<template>
  <div class="singer-box">
    <TheHeader></TheHeader>
    <div class="singer">
      <ul class="singer-header">
        <li
          v-for="(item, index) in singerStyle"
          :key="index"
          @click="handleChangView(item)"
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
    </div>
    <ScrollTop></ScrollTop>
    <PlayBar></PlayBar>
    <User></User>
    <TheFooter></TheFooter>
  </div>
</template>
<script>
import ContentList from "@/components/ContentList.vue";
import { getAllSinger, getSingerOfSex } from "@/assets/api/index";
import { mixin } from "@/mixins/index.js";
import { singerStyle } from "@/assets/data/singerStyle";
export default {
  name: "Singer",
  mixins: [mixin],
  components: {
    ContentList,
  },
  mounted() {
    this.gegetSingerList();
    this.singerStyle = singerStyle;
    this.activeName = "全部歌手";
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
      albumDatas: [], //歌手数据
      pageSize: 10, //一页有多少条数据
      currentPage: 1, //当前的页数
      singerStyle, //歌手性别
      activeName: "全部歌手", //当前选中的歌手
    };
  },
  methods: {
    //获取所有歌手
    gegetSingerList() {
      getAllSinger().then((res) => {
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
    //根据类型显示对应的歌单
    handleChangView(item) {
      this.activeName = item.name;
      this.albumDatas = [];
      if (item.name == "全部歌手") {
        this.gegetSingerList();
      } else {
        this.getSingerOfSex(item.type);
      }
    },
    //根据性别查询对应的歌手
    getSingerOfSex(sex) {
      getSingerOfSex(sex).then((res) => {
        this.currentPage = 1;
        this.albumDatas = res;
      });
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/singer.css";
</style>
