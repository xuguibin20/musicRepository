<template>
  <div class="signUp">
    <div class="signUp-head">
      <span>用户注册 </span>
    </div>
    <el-form
      :model="registerForm"
      ref="registerForm"
      label-width="70px"
      class="demo-ruleForm"
    >
      <!-- 用户名 -->
      <el-form-item prop="username" label="用户名">
        <el-input v-model="registerForm.username"></el-input>
      </el-form-item>
      <!-- 密码 -->
      <el-form-item prop="password" label="密码">
        <el-input type="password" v-model="registerForm.password"></el-input>
      </el-form-item>
      <!-- 性别 -->
      <el-form-item prop="sex" label="密码">
        <el-radio-group v-model="registerForm.sex">
          <el-radio :label="0">女</el-radio>
          <el-radio :label="1">男</el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 手机号码 -->
      <el-form-item prop="phoneNum" label="手机号码">
        <el-input type="phoneNum" v-model="registerForm.phoneNum"></el-input>
      </el-form-item>
      <!-- 邮箱 -->
      <el-form-item prop="email" label="邮箱">
        <el-input type="email" v-model="registerForm.email"></el-input>
      </el-form-item>
      <!-- 生日 -->
      <el-form-item prop="birth" label="生日">
        <el-date-picker
          v-model="registerForm.birth"
          placeholder="选择日期"
          style="width: 100%"
        ></el-date-picker>
      </el-form-item>
      <!-- 签名 -->
      <el-form-item prop="introduction" label="签名">
        <el-input
          type="introduction"
          v-model="registerForm.introduction"
        ></el-input>
      </el-form-item>
      <!-- 地区 -->
      <el-form-item prop="location" label="地区">
        <el-select
          type="location"
          v-model="registerForm.location"
          style="width: 100%"
        >
          <el-option
            v-for="item in cities"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          ></el-option>
        </el-select>
      </el-form-item>
      <div class="login-btn">
        <el-button @click="goback(-1)">取消</el-button>
        <el-button @click="SignUp" type="primary">确定</el-button>
      </div>
    </el-form>
  </div>
</template>
<script>
import { setConsumer } from "@/assets/api/index";
export default {
  name: "SignUp",
  data() {
    return {
      registerForm: {
        username: "", //用户名
        password: "", //密码
        sex: "", //性别
        phoneNum: "", //手机号码
        email: "", //邮箱
        birth: "", //生日
        introduction: "", //签名
        location: "", //地区
      },
      cities: [
        {
          value: "北京",
          label: "北京",
        },
      ], //城市
    };
  },
  methods: {
    SignUp() {
      let date = this.registerForm.birth;
      let datetime =
        date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
      let params = new URLSearchParams();
      params.append("username", this.registerForm.username);
      params.append("password", this.registerForm.password);
      params.append("sex", this.registerForm.sex);
      params.append("email", this.registerForm.email);
      params.append("phoneNum", this.registerForm.phoneNum);

      params.append("birth", datetime);
      params.append("location", this.registerForm.location);
      params.append("introduction", this.registerForm.introduction);

      setConsumer(params)
        .then((res) => {
          if (res.code == 1) {
            this.notify("修改成功", "success");
          } else {
            this.notify("修改失败", "error");
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/sign-up.css";
</style>
