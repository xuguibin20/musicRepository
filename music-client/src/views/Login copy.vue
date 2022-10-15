<template>
  <div class="login-body">
    <div class="header">
      <div class="header-left"></div>
      <div class="header-right">
     <el-button type="primary" round @click="dialogFormVisible = true">登录/注册</el-button> 
        <!-- <el-dialog title="登录" :visible.sync="dialogFormVisible">
    <el-form :model="form">
    <el-form-item label="账号">
      <el-input v-model="form.username" autocomplete="off"size="mini"mini></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password" autocomplete="off" size="mini"mini></el-input>
    </el-form-item>
    </el-form>
   <div slot="footer" class="dialog-footer">
    <el-button @click="dialogFormVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
   </div>
    </el-dialog> --> 
      </div>
    </div>
    <h1>音乐，陪你做生活的演奏师</h1>

      <div class="login-search-box">
        <span>
          <span>
            <img />
          </span>
          <input v-model="keywords" placeholder="请输入内容" ></input>
        </span>
      </div>

    <div class="swiper">
<div class="position" style="margin-top: -56px;"></div>
<div class="position"></div>
<div class="position"></div>
<div class="position"></div>
<div class="position"></div>
<div class="position"></div>
<div class="position"></div>
<div class="position"style="margin-top: -56px;"></div>
    </div>
  </div>
</template>
<script>
export default {
  name: "Login",
  data() {
    return {
      keywords: "", //搜索关键字
      dialogFormVisible:'',
      form:{
        username:"",password:""
      }
    };
  },
  methods:{

  },
};
</script>
<style lang="less" scoped>

.header {
  position: fixed;
  height: 80px;
}


h1 {
  margin-top: 60px;
  margin-bottom: 30px;
  font-family: "Courier New", Courier, monospace;
  font-size: 70px;
  text-align: center;
  height: 70px;
  line-height: 70px;
}

.login-search-box {
  position: relative;


  height: 56px; 
  width: 570px;
  z-index: 10;
  padding: 0 24px;
  border-radius: 32px;
  box-shadow: 0 8px 48px 8px rgb(0 0 0 / 8%);
  font-size: 16px;
  background: #fff;
}
.login-search-box input{
height:100%;
border-style:none

}
.swiper{
    position: absolute;
    float: left ;
  padding: auto;
  background-color: rgb(158, 157, 157);

display: flex;
// height: 600px;
   justify-content: center;
  align-items: center;
    width: 100%;
    height: 63vh;
}
.position{
margin-left:20px;  

  width: 200px;
  height: 100%;
  background-color: rgb(255, 166, 166);
}


</style>
