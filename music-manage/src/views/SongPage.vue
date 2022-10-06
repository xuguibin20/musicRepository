<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-tickets">歌曲信息</i>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-button type="primary" size="mini" @click="delAll"
          >批量删除</el-button
        >
        <el-input
          v-model="select_word"
          size="mini"
          placeholder="请输入歌曲名"
          class="handle-input"
        ></el-input>
        <el-button
          type="primary"
          size="mini"
          @click="centerDialogVisible = true"
          >添加歌曲</el-button
        >
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
      <el-table-column label="歌曲图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="song-img">
            <img :src="getUrl(scope.row.pic)" style="width=100%" />
          </div>
          <el-upload
            :action="uploadUrl(scope.row.id)"
            :before-upload="beforeAvatorUpload"
            :on-success="handleAvatorSuccess"
          >
            <el-button size="mini">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <el-table-column prop="name" label="歌手-歌名" width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="introduction"
        label="专辑"
        width="150"
        align="center"
      >
      </el-table-column>
      <el-table-column label="歌词" align="center">
        <template slot-scope="scope">
          {{ scope.row.lyric }}
        </template>
      </el-table-column>
      <el-table-column label="操作" width="170px" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
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
    <el-dialog
      title="添加歌曲"
      :visible.sync="centerDialogVisible"
      width="400px"
      center
    >
      <el-form
        :model="registerForm"
        ref="registerForm"
        label-width="80px"
        action=""
        id="tf"
      >
        <div>
          <label for="">歌名</label>
          <el-input type="text" name="name"></el-input>
        </div>
        <div>
          <label for="">专辑</label>
          <el-input type="text" name="introduction"></el-input>
        </div>
        <div>
          <label for="">歌词</label>
          <el-input type="textarea" name="lyric"></el-input>
        </div>
        <div>
          <label for="">歌曲上传</label>
          <input type="file" name="file" />
        </div>
        <div>
          <label for="">歌名</label>
          <el-input type="text" name="name"></el-input>
        </div>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="centerDialogVisible = false"
          >取消</el-button
        >
        <el-button size="mini" @click="addSong">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog
      title="修改歌曲"
      :visible.sync="editVisible"
      width="400px"
      center
    >
      <el-form :model="form" ref="form" label-width="80px">
        <el-form-item prop="name" label="歌手-歌名" size="mini">
          <el-input v-model="form.name" placeholder="歌手-歌名"></el-input>
        </el-form-item>

        <el-form-item prop="introduction" label="专辑" size="mini">
          <el-input v-model="form.introduction" placeholder="专辑"></el-input>
        </el-form-item>
        <el-form-item prop="lyric" label="歌词" size="mini">
          <el-input
            v-model="form.lyric"
            placeholder="歌词"
            type="textarea"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="editVisible = false">取消</el-button>
        <el-button size="mini" @click="editSave">确定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="歌曲" :visible.sync="delVisible" width="300px" center>
      <div align="center">删除不可恢复，是否确定删除</div>
      <span slot="footer">
        <el-button size="mini" @click="delVisible = false">取消</el-button>
        <el-button size="mini" @click="deleteRow">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index";
import {} from "@/api/index";
export default {
  mixins: [mixin],
  data() {
    return {
      singerId: "", //歌手id
      singerName: "", //歌手名
      centerDialogVisible: false, //添加弹窗是否显示
      editVisible: false, //编辑弹窗是否显示
      delVisible: false, //删除弹窗是否显示
      //添加框
      registerForm: {
        name: "",
        singerName: "",
        lyric: "",
        introduction: "",
      },
      //添加框
      form: {
        id: "",
        name: "",
        introduction: "",
        lyric: "",
      },

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
    //计算当前搜索结果表里的数据
    data() {
      return this.tableData.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      );
    },
  },
  created() {
    this.singerId = this.$$router.query.id;
    this.singerName = this.$$router.query.name;
    this.getData();
  },
  methods: {
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //查询所有歌手
    getData() {
      this.tempData = [];
      this.tableData = [];
      getAllSinger().then((res) => {
        this.tableData = res;
        this.tempData = res;
      });
    },
    //添加歌曲
    addSong() {
      this.centerDialogVisible = false;
    },
    //弹出编辑页面
    handleEdit(row) {
      // this.editVisible = true;
      // this.form = {
      //   id: row.id,
      //   name: row.name,
      //   sex: row.sex,
      //   birth: row.birth,
      //   location: row.location,
      //   introduction: row.introduction,
      // };
    },
    // 保存编辑页面修改的数据
    editSave() {
      // let date = new Date(this.form.birth);
      // let datetime =
      //   date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      // let params = new URLSearchParams();
      // params.append("id", this.form.id);
      // params.append("name", this.form.name);
      // params.append("sex", this.form.sex);
      // params.append("birth", datetime);
      // params.append("location", this.form.location);
      // params.append("introduction", this.form.introduction);

      // updateSinger(params)
      //   .then((res) => {
      //     if (res.code == 1) {
      //       this.getData();
      //       this.notify("编辑成功", "success");
      //     } else {
      //       this.notify("编辑失败", "error");
      //     }
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
      // this.editVisible = false;
    },
    //更新图片
    uploadUrl(id) {
      // return `${this.$store.state.HOST}/song/updateSongPic?id=${id}`;
    },
    //删除一名歌手
    deleteRow() {
      // delSinger(this.idx)
      //   .then((res) => {
      //     if (res) {
      //       this.getData();
      //       this.notify("删除成功", "success");
      //     } else {
      //       this.notify("删除失败", "error");
      //     }
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
      // this.delVisible = false;
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

.handle-box {
  margin-bottom: 20px;
}

.song-img {
  width: 100%;
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
</style>
