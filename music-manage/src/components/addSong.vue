<template>
  <div class="table">
    <div class="container">
      <div class="handle-box">
        <el-input
          v-model="select_word"
          size="mini"
          placeholder="请输入歌曲名"
          class="handle-input"
        ></el-input>
      </div>
    </div>
    <el-table
      size="mini"
      border
      style="width: 100%"
      height="666px"
      :data="data"
    >
      <el-table-column label="歌曲图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="song-img">
            <img
              :src="getUrl(scope.row.pic)"
              style="width: 100%; aspect-ratio: 1 / 1"
            />
            <div class="mask" style="width: 100%; aspect-ratio: 1 / 1"></div>
          </div>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="歌手" width="120" align="center">
      </el-table-column>
      <el-table-column prop="name" label="歌名" width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="introduction"
        label="专辑"
        width="150"
        align="center"
      >
      </el-table-column>

      <el-table-column label="操作" width="170px" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary"
            @click="handleAdd(scope.row.id, scope.row.singerId)"
            >添加</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <div class="pagination">
      <el-pagination
        background
        layout="total,prev,pager,next"
        :current-page="currentPage"
        :page-size="pageSize"
        :total="tableData.length"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index";
import { mapGetters } from "vuex";
import "@/assets/js/iconfont.js";
import { getAllSong } from "@/api/index";
export default {
  mixins: [mixin],
  name: "addSong",
  data() {
    return {
      songId: "", //歌曲id
      singerId: "", //歌手id
      singerName: "", //歌手名

      tableData: [],
      tempData: [],
      select_word: "",
      pageSize: 5, //分页每页大小
      currentPage: 1, //当前页
      idx: -1, //当前选择项目
      multipleSelection: [], //哪些项已经打勾了
      toggle: false, //播放器显示图标的状态
    };
  },
  watch: {
    select_word() {
      //搜索框里面的内容发生变化的时候，搜索结果table列表的内容跟着它的内容发生变化
      if (this.select_word == "") {
        this.tableData = this.tempData;
      } else {
        this.tableData = [];
        for (let item of this.tempData) {
          if (item.name.includes(this.select_word)) {
            this.tableData.push(item);
          }
        }
      }
    },
    isPlay() {
      if (this.isPlay) {
        this.$store.commit("setplayButtonUrl", "#icon-zanting");
      } else {
        this.$store.commit("setplayButtonUrl", "#icon-bofang");
      }
    },
  },
  computed: {
    ...mapGetters([
      "isPlay", //getters里的
      "id", //歌曲id
    ]),
    //计算当前搜索结果表里的数据
    data() {
      return this.tableData.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
    },
  },
  created() {
    this.singerId = this.$route.query.id;
    this.singerName = this.$route.query.name;
    this.getSong();
  },

  methods: {
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //获得所有歌曲
    getSong() {
      this.tempData = [];
      this.tableData = [];
      getAllSong().then((res) => {
        this.tableData = res;
        this.tempData = res;
        this.currentPage = 1;
      });
    },
    //添加歌曲的信息到缓存中
    handleAdd(id, singerId) {
      this.$store.commit("setSongId", id);
      this.$store.commit("setSingerId", singerId);
    },
  },
};
</script>
<style scoped>
.table {
  min-width: 800px;
}

.container {
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.song-img {
  height: 80px;
  border-radius: 5px;
  margin-bottom: 5px;
  overflow: hidden;
}

.handle-input {
  width: 300px;
  display: inline-block;
  margin-left: 10px;
  margin-right: 10px;
}
.pagination {
  display: flex;
  justify-content: center;
}

.crumbs {
  margin-bottom: 20px;
}

.icon-total {
  margin-top: 10px;
  margin-left: 5px;
}
.icon {
  width: 2em;
  height: 2em;
  color: rgb(254, 254, 255);
  fill: currentColor;
  overflow: hidden;
}
</style>
