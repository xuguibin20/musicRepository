<template>
  <div class="">
    <!-- 登录框 -->
    <el-dialog
      width="600px"
      center
      :visible.sync="dialogFormVisible"
      :before-close="handleClose"
    >
      <el-form :model="form" :rules="rules">
        <div class="dialogBtn">
          <el-form-item style="display: inline-block">
            <el-button
              type="primary"
              style="width: 100px; margin: 0 10px"
              plain
              autofocus
              @click="login"
              >登录</el-button
            >
          </el-form-item>
          <el-form-item style="display: inline-block">
            <el-button
              type="primary"
              style="width: 100px; margin: 0 10px"
              plain
              @click="register"
              >注册</el-button
            >
          </el-form-item>
        </div>

        <el-form-item prop="Lusername" Label="用户名">
          <el-input
            v-model="form.Lusername"
            style="width: 350px; margin-left: 20%"
            placeholder="账号"
          ></el-input>
        </el-form-item>
        <el-form-item prop="Lpassword" Label="密码">
          <el-input
            v-model="form.Lpassword"
            type="password"
            style="width: 350px; margin-left: 20%"
            placeholder="密码"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          style="width: 350px; height: 40px"
          round
          type="primary"
          @click="loginSuccess"
          >登录</el-button
        >
      </div>
    </el-dialog>
    <!-- 注册框 -->
    <el-dialog
      width="600px"
      center
      :visible.sync="dialogregisterFormVisible"
      :before-close="handleClose"
    >
      <el-form :model="registerform" :rules="rules">
        <div class="dialogBtn">
          <el-form-item style="display: inline-block">
            <el-button
              type="primary"
              style="width: 100px; margin: 0 10px"
              plain
              @click="login"
              >登录</el-button
            >
          </el-form-item>
          <el-form-item style="display: inline-block">
            <el-button
              type="primary"
              style="width: 100px; margin: 0 10px"
              plain
              @click="register"
              >注册</el-button
            >
          </el-form-item>
        </div>

        <el-form-item prop="Rusername" Label="用户名">
          <el-input
            v-model="registerform.Rusername"
            style="width: 350px; margin-left: 20%"
            placeholder="账号"
          ></el-input>
        </el-form-item>
        <el-form-item prop="Rpassword" Label="密码">
          <el-input
            v-model="registerform.Rpassword"
            type="password"
            style="width: 350px; margin-left: 20%"
            placeholder="密码"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          style="width: 350px; height: 40px"
          round
          type="primary"
          @click="registerSuccess"
          >注册</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { mapGetters } from "vuex";

import { registerConsumer, loginConsumer } from "@/assets/api/index";
import { rules } from "@/assets/data/form";
export default {
  name: "User",
  computed: {
    ...mapGetters(["dialogFormVisible"]),
  },
  created() {
    this.rules = rules;
  },
  data() {
    return {
      dialogregisterFormVisible: false,
      form: {
        Lusername: "",
        Lpassword: "",
      },
      registerform: {
        Rusername: "",
        Rpassword: "",
      },
      rules: {}, //表单提交的检验规则
    };
  },

  methods: {
    //关闭窗口
    handleClose() {
      this.$store.commit("setdialogFormVisible", false);
      this.dialogregisterFormVisible = false;
    },
    login() {
      this.$store.commit("setdialogFormVisible", true);
      this.dialogregisterFormVisible = false;
    },
    register() {
      this.$store.commit("setdialogFormVisible", false);
      this.dialogregisterFormVisible = true;
    },
    loginSuccess() {
      let _this = this;
      let params = new URLSearchParams();
      params.append("username", this.form.Lusername);
      params.append("password", this.form.Lpassword);
      loginConsumer(params)
        .then((res) => {
          if (res.code == 1) {
            _this.$notify({
              title: "登录成功",
              type: "success",
            });
            this.$store.commit("setdialogFormVisible", false);
            this.$store.commit("setLogin", true);
            setTimeout(() => {
              this.$store.commit("setuserId", res.userMsg.id);
              this.$store.commit("setuserName", res.userMsg.username);
              this.$store.commit("setAvator", res.userMsg.avator);
              this.$router.go(0); //刷新页面
              // _this.$router.push({ path: "/Home" });
            }, 1500);
          } else {
            _this.$notify({
              title: "账号或密码错误",
              type: "error",
            });
          }
        })
        .catch((err) => {
          _this.$notify({
            title: "账号或密码错误",
            type: "error",
          });
        });
    },
    registerSuccess() {
      let _this = this;
      let params = new URLSearchParams();
      params.append("username", this.registerform.Rusername);
      params.append("password", this.registerform.Rpassword);
      registerConsumer(params)
        .then((res) => {
          if (res.code == 1) {
            _this.$notify({
              title: "注册成功",
              type: "success",
            });
            this.dialogregisterFormVisible = false;
            this.$store.commit("setLogin", true);
            setTimeout(() => {
              //    this.$store.commit("setuserId", res.userMsg.id);
              // this.$store.commit("setuserName", res.userMsg.username);
              // this.$store.commit("setAvator", res.userMsg.avator);
              //判断当前页面是否为首页，不是则跳转，是则刷新
              // if()
              _this.$router.push({ path: "/Home" });
            }, 1500);
          } else {
            _this.$notify({
              title: "注册失败",
              type: "error",
            });
          }
        })
        .catch((err) => {
          _this.$notify({
            title: "账号已存在",
            type: "error",
          });
        });
    },
  },
};
</script>
<style lang='less' scoped>
</style>