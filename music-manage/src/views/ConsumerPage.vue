<template>
  <div class="table">
    <!-- 页面操作 -->
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
        <el-button
          type="primary"
          size="mini"
          @click="centerDialogVisible = true"
          >添加新用户</el-button
        >
      </div>
    </div>
    <!-- 信息展示 -->
    <el-table
      size="mini"
      border
      style="width: 100%"
      height="666px"
      :data="data"
      @selection-change="handleSelectionChange"
    >
      <!-- 选择一名或多名哦用户 -->
      <el-table-column type="selection" width="40px"></el-table-column>
      <!-- 用户图片以及更新图片 -->
      <el-table-column label="用户图片" width="110" align="center">
        <template slot-scope="scope">
          <div class="consumer-img">
            <img
              :src="getUrl(scope.row.avator)"
              style="width: 100%; aspect-ratio: 1/1"
            />
          </div>
          <el-upload
            :action="uploadPicUrl(scope.row.id)"
            :before-upload="beforePicUpload"
            :on-success="handleAvatorSuccess"
          >
            <el-button size="mini">更新图片</el-button>
          </el-upload>
        </template>
      </el-table-column>
      <!-- 用户名 -->
      <el-table-column prop="username" label="用户" width="120" align="center">
      </el-table-column>
      <!-- 性别 -->
      <el-table-column label="性别" align="center" width="50">
        <template slot-scope="scope">
          {{ changeSex(scope.row.sex) }}
        </template>
      </el-table-column>
      <!-- 手机号码 -->
      <el-table-column
        prop="phoneNum"
        label="手机号"
        width="120"
        align="center"
      >
      </el-table-column>
      <!-- 电子邮箱 -->
      <el-table-column prop="email" label="电子邮箱" width="240" align="center">
      </el-table-column>
      <!-- 生日 -->
      <el-table-column label="生日" width="120" align="center">
        <template slot-scope="scope">
          {{ attachBirth(scope.row.birth) }}
        </template>
      </el-table-column>
      <!-- 签名 -->
      <el-table-column prop="introduction" label="签名" align="center">
      </el-table-column>
      <!-- 地区 -->
      <el-table-column prop="location" label="地区" width="100" align="center">
      </el-table-column>
      <!-- 收藏 -->
      <el-table-column label="收藏" width="80" align="center">
        <template slot-scope="scope">
          <el-button size="mini" @click="getCollect(scope.row.id)"
            >收藏</el-button
          >
        </template>
      </el-table-column>
      <!-- 对用户的操作 -->
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
    <!-- 分页模块 -->
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
    <!-- 添加，查询，删除 -->
    <el-dialog
      title="添加用户"
      :visible.sync="centerDialogVisible"
      width="400px"
      center
    >
      <el-form :model="registerForm" ref="registerForm" label-width="80px">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input
            v-model="registerForm.username"
            placeholder="用户名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input
            type="password"
            v-model="registerForm.password"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-radio-group v-model="registerForm.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phoneNum" label="手机号码" size="mini">
          <el-input
            v-model="registerForm.phoneNum"
            placeholder="手机号码"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email" label="电子邮箱" size="mini">
          <el-input
            v-model="registerForm.email"
            placeholder="电子邮箱"
          ></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="registerForm.birth"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item prop="introduction" label="签名" size="mini">
          <el-input
            v-model="registerForm.introduction"
            placeholder="签名"
          ></el-input>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-input
            v-model="registerForm.location"
            placeholder="地区"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="centerDialogVisible = false"
          >取消</el-button
        >
        <el-button size="mini" @click="addConsumer">确定</el-button>
      </span>
    </el-dialog>
    <!-- 编辑用户 -->
    <el-dialog
      title="修改用户"
      :visible.sync="editVisible"
      width="400px"
      center
    >
      <el-form :model="form" ref="form" label-width="80px">
        <el-form-item prop="username" label="用户名" size="mini">
          <el-input v-model="form.username" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="password" label="密码" size="mini">
          <el-input
            type="password"
            v-model="form.password"
            placeholder="密码"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" size="mini">
          <el-radio-group v-model="form.sex">
            <el-radio :label="0">女</el-radio>
            <el-radio :label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="phoneNum" label="手机号码" size="mini">
          <el-input v-model="form.phoneNum" placeholder="手机号码"></el-input>
        </el-form-item>

        <el-form-item prop="email" label="电子邮箱" size="mini">
          <el-input v-model="form.email" placeholder="电子邮箱"></el-input>
        </el-form-item>
        <el-form-item prop="birth" label="生日" size="mini">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="form.birth"
            style="width: 100%"
          ></el-date-picker>
        </el-form-item>
        <el-form-item prop="introduction" label="签名" size="mini">
          <el-input v-model="form.introduction" placeholder="签名"></el-input>
        </el-form-item>
        <el-form-item prop="location" label="地区" size="mini">
          <el-input v-model="form.location" placeholder="地区"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer">
        <el-button size="mini" @click="editVisible = false">取消</el-button>
        <el-button size="mini" @click="editSave">确定</el-button>
      </span>
    </el-dialog>
    <!-- 删除用户 -->
    <el-dialog title="删除用户" :visible.sync="delVisible" width="300px" center>
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
import {
  setConsumer,
  getAllConsumer,
  updateConsumer,
  delConsumer,
} from "@/api/index";
export default {
  mixins: [mixin],
  data() {
    return {
      centerDialogVisible: false, //添加弹窗是否显示
      editVisible: false, //编辑弹窗是否显示
      delVisible: false, //删除弹窗是否显示
      //添加框
      registerForm: {
        username: "",
        password: "",
        sex: "",
        birth: "",
        phoneNum: "",
        email: "",
        location: "",
        introduction: "",
      },
      //添加框
      form: {
        id: "",
        username: "",
        password: "",
        sex: "",
        birth: "",
        phoneNum: "",
        email: "",
        location: "",
        introduction: "",
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
          if (item.username.includes(this.select_word)) {
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
    this.getData();
  },
  methods: {
    //获取当前页
    handleCurrentChange(val) {
      this.currentPage = val;
    },
    //查询所有用户
    getData() {
      this.tempData = [];
      this.tableData = [];
      getAllConsumer().then((res) => {
        this.tableData = res;
        this.tempData = res;
        this.currentPage = 1;
      });
    },
    //添加用户
    addConsumer() {
      let date = this.registerForm.birth;
      let datetime =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      let params = new URLSearchParams();
      params.append("username", this.registerForm.username);
      params.append("password", this.registerForm.password);
      params.append("sex", this.registerForm.sex);
      // params.append("pic", "img/consumerPic/mn.jpg");
      params.append("phoneNum", this.registerForm.phoneNum);
      params.append("email", this.registerForm.email);
      params.append("birth", datetime);
      params.append("location", this.registerForm.location);
      params.append("introduction", this.registerForm.introduction);

      setConsumer(params)
        .then((res) => {
          if (res.code == 1) {
            this.getData();
            this.registerForm = {};
            this.notify("添加成功", "success");
          } else {
            this.notify("添加失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
      this.centerDialogVisible = false;
    },
    //弹出编辑页面
    handleEdit(row) {
      this.editVisible = true;
      this.form = {
        id: row.id,
        username: row.username,
        password: row.password,
        sex: row.sex,
        phoneNum: row.phoneNum,
        email: row.email,
        birth: row.birth,
        location: row.location,
        introduction: row.introduction,
      };
    },
    // 保存编辑页面修改的数据
    editSave() {
      let date = new Date(this.form.birth);
      let datetime =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      let params = new URLSearchParams();
      params.append("id", this.form.id);
      params.append("username", this.form.username);
      params.append("password", this.form.password);
      params.append("sex", this.form.sex);
      params.append("phoneNum", this.form.phoneNum);
      params.append("email", this.form.email);
      params.append("birth", datetime);
      params.append("location", this.form.location);
      params.append("introduction", this.form.introduction);

      updateConsumer(params)
        .then((res) => {
          if (res.code == 1) {
            this.getData();
            this.notify("编辑成功", "success");
          } else {
            this.notify("编辑失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
      this.editVisible = false;
    },
    //更新图片
    uploadPicUrl(id) {
      return `${this.$store.state.HOST}/consumer/updateConsumerAvator?id=${id}`;
    },
    //删除一名用户
    deleteRow() {
      delConsumer(this.idx)
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
    //转向该用户的收藏列表
    getCollect(id) {
      this.$router.push({ path: "/Collect", query: { id } });
    },
  },
};
</script>
<style lang="less" scoped>
.table {
  min-width: 800px;
}

.container {
  padding: 20px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 5px;
}

.consumer-img {
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
</style>
