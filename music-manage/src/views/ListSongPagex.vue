<template>
  <div class="table">
    <div class="crumbs">
      <i class="el-icon-tickets">歌单歌曲信息</i>
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

      <el-table-column prop="name" label="歌手-歌名" align="center">
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
        id="tf"
      >
        <el-form-item prop="singerName" label="歌手名" size="mini">
          <el-input
            v-model="registerForm.singerName"
            placeholder="歌手名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="songName" label="歌曲名" size="mini">
          <el-input
            v-model="registerForm.songName"
            placeholder="歌曲名"
          ></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer">
        <el-button size="mini" @click="centerDialogVisible = false"
          >取消</el-button
        >
        <el-button size="mini" @click="getSongId">确定</el-button>
      </span>
    </el-dialog>

    <el-dialog title="删除歌曲" :visible.sync="delVisible" width="300px" center>
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
import { mapGetters } from "vuex";
import "@/assets/js/iconfont.js";
import {
  listSongOfSongListId,
  songOfSongId,
  songOfSongName,
  addSongforList,
  delSongofListSong,
} from "@/api/index";
export default {
  mixins: [mixin],
  name: "ListSongPagx",
  data() {
    return {
      songId: "", //歌曲id
      singerId: "", //歌手id
      singerName: "", //歌手名
      centerDialogVisible: false, //添加弹窗是否显示

      delVisible: false, //删除弹窗是否显示
      //添加框
      registerForm: {
        singName: "", //歌手名
        songName: "", //歌名
      },

      tableData: [],
      tempData: [],
      select_word: "",
      pageSize: 5, //分页每页大小
      currentPage: 1, //当前页
      idx: -1, //当前选择项目
      multipleSelection: [], //哪些项已经打勾了
      toggle: false, //播放器显示图标的状态
      songListId: "", //歌单id
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
    ...mapGetters([
      "isPlay", //getters里的？
    ]),
  },
  created() {
    this.songListId = this.$route.query.songListId; //从歌单页面传过来歌单id
    this.getData();
  },
  destroyed() {
    this.$store.commit("setisPlay", false);
  },
  methods: {
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //根据歌手id查询歌曲
    getData() {
      this.tempData = [];
      this.tableData = [];
      listSongOfSongListId(this.songListId).then((res) => {
        for (let item of res) {
          this.getSong(item.songId);
        }
        // this.tableData = res;
        // this.tempData = res;
        // this.currentPage = 1;
      });
    },
    //根据歌曲id查询歌曲对象，放到tableData和tempData里面
    getSong(id) {
      songOfSongId(id)
        .then((res) => {
          this.tempData.push(res);
          this.tableData.push(res);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //添加歌曲前的准备，获取到歌曲id
    getSongId() {
      let _this = this;
      var songOfName =
        _this.registerForm.singName + "-" + this.registerForm.songName;
      songOfSongName(songOfName).then((res) => {
        _this.addSong(res[0].id);
      });
    },
    //添加歌曲
    addSong(songId) {
      let _this = this;
      let params = new URLSearchParams();
      params.append("songId", songId);
      params.append("songListId", this.songListId);
      addSongforList(params)
        .then((res) => {
          if (res.code == 1) {
            this.getData();
            this.notify("添加成功", "success");
          } else {
            this.notify("添加失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
      _this.centerDialogVisible = false;
      //   let _this = this;
      //   var form = new FormData(document.getElementById("tf"));
      //   form.append("singerId", this.singerId);
      //   form.append("introduction", this.registerForm.introduction);
      //   form.append("lyric", this.registerForm.lyric);
      //   form.set("name", this.singerName + "-" + this.registerForm.name);
      //   if (!form.get("lyric")) {
      //     form.set("lyric", "[00:00:00]暂无歌词");
      //   }
      //   var req = new XMLHttpRequest();
      //   req.onreadystatechange = function () {
      //     //req.readyState == 4 获取到返回的完整数据
      //     // req.status == 200 和后台正常交互完成了
      //     if (req.readyState == 4 && req.status == 200) {
      //       let res = JSON.parse(req.response);
      //       if (res.code == 1) {
      //         _this.getData();

      //         _this.registerForm = {};
      //         //bug 上传文件不会清空
      //         _this.notify("保存成功", "success");
      //       } else {
      //         _this.notify("保存失败", "error");
      //       }
      //       //刷新页面
      //     }
      //   };
      //   req.open("post", `${_this.$store.state.HOST}/song/add`, false);
      //   req.send(form);
      //   _this.centerDialogVisible = false;
    },
    // //弹出编辑页面
    // handleEdit(row) {
    //   this.editVisible = true;
    //   this.form = {
    //     id: row.id,
    //     name: row.name,
    //     introduction: row.introduction,
    //     lyric: row.lyric,
    //   };
    // },
    // 保存编辑页面修改的数据
    // editSave() {
    //   let params = new URLSearchParams();
    //   params.append("id", this.form.id);
    //   params.append("name", this.form.name);
    //   params.append("introduction", this.form.introduction);
    //   params.append("lyric", this.form.lyric);
    //   updateSongMsg(params)
    //     .then((res) => {
    //       if (res.code == 1) {
    //         this.getData();
    //         this.notify("编辑成功", "success");
    //       } else {
    //         this.notify("编辑失败", "error");
    //       }
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    //   this.editVisible = false;
    // },

    // //更新歌曲
    // uploadSongUrl(id) {
    //   return `${this.$store.state.HOST}/song/updateSong?id=${id}`;
    // },
    //删除一首歌
    deleteRow() {
      delSongofListSong(this.idx)
        .then((res) => {
          if (res) {
            this.getData();
            this.notify("删除成功", "success");
          } else {
            this.notify("删除失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
      this.delVisible = false;
    },
    // //解析歌词
    // parseLyric(text) {
    //   let lines = text.split("\n");
    //   let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g;
    //   let result = [];
    //   if (text == "") {
    //     result = "暂无歌词";
    //   } else {
    //     for (let item of lines) {
    //       let value = item.replace(pattern, "");
    //       result.push(value);
    //     }
    //   }
    //   return result;
    // },
    // //更新歌曲之前的检验
    // beforeSongUpload(file) {
    //   let _this = this;
    //   var testMsg = file.name.substring(file.name.lastIndexOf(".") + 1);
    //   if (testMsg != "mp3") {
    //     _this.$notify({
    //       title: "上传文件只能是mp3格式",
    //       type: "error",
    //     });
    //     return false;
    //   }
    //   return true;
    // },
    // //更新歌曲成功之后要做的工作
    // handleSongSuccess(res) {
    //   let _this = this;
    //   if (res.code == 1) {
    //     _this.getData();
    //     window.setTimeout(function () {
    //       window.location.reload();
    //     });
    //     _this.$notify({
    //       title: "更新成功",
    //       type: "success",
    //     });
    //   } else {
    //     _this.$notify({
    //       title: "更新失败",
    //       type: "error",
    //     });
    //   }
    // },
    //切换播放歌曲
    // getSongUrl(url, id) {
    //   this.toggle = id;
    //   this.$store.commit("setUrl", this.$store.state.HOST + "/" + url);
    //   if (this.isPlay) {
    //     this.$store.commit("setisPlay", false);
    //     this.toggle = " id";
    //   } else {
    //     this.$store.commit("setisPlay", true);
    //     this.toggle = id;
    //   }
    // },
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
.play {
  position: absolute;
  z-index: 100;
  width: 87px;
  height: 80px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  top: 15px;
  left: 11px;
  overflow: hidden;
}
.play:hover {
  background-color: rgba(71, 71, 71, 0.4);
  border-radius: 5px;
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
