
</script>
<style lang="less" scoped></style>
<template>
  <div class="test">

    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll"
          >批量删除</el-button
        >

        <el-input
          v-model="select_word"
          size="mini"
          placeholder="请输入用户名"
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
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="40px"></el-table-column>
      
      <el-table-column
        prop="name"
        label="用户名"
        width="500"
        align="center"
      >
      </el-table-column>
     
      <el-table-column
        prop="content"
        label="评论"
       size="max"
        align="center"
      >
      </el-table-column>
    
      <el-table-column label="操作" width="170px" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row.id)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
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
import { getUserOfId, getCommentOfSongListId, delComment } from "@/api/index";
export default {
  mixins: [mixin],
  name: "Comment",

  data() {
    return {
      delVisible: false, //删除弹窗是否显示

      tableData: [],
      tempData: [],
      select_word: "",
      pageSize: 5, //分页每页大小
      currentPage: 1, //当前页
      idx: -1, //当前选择项目
      multipleSelection: [], //哪些项已经打勾了
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
  },

  computed: {
    ...mapGetters([
      "isPlay", //getters里的
      "id", //歌曲id
      "singerId", //歌手id
      "songId", //歌曲id
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
    this.getData();
  },
  destroyed() {
    this.$store.commit("setisPlay", false);
  },
  methods: {
    handleClose(done) {
      this.$confirm("确认关闭？")
        .then((_) => {
          done();
        })
        .catch((_) => {});
    },
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //根据歌单id查询所有评论
    getData() {
      this.tempData = [];
      this.tableData = [];

      getCommentOfSongListId(this.$route.query.id).then((res) => {
        for (let item of res) {
          this.getUsers(item.userId, item);
        }

        this.currentPage = 1;
      });
    },
    getUsers(id, item) {
      getUserOfId(id).then((res) => {
        let o = item;
        o.name = res.username;
        this.tableData.push(o);
        this.tempData.push(o);
      });
    },
    //删除评论
    handleDelete(id) {
      let params = new URLSearchParams();
      params.append("userId", this.$route.query.id);
      params.append("songId", id);

      delCollection(params)
        .then((res) => {
          if (res.code == 1) {
            this.getData();

            this.notify("删除成功", "success");
          } else {
            this.notify("删除失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
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
</style>