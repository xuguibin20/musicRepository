<template>
  <div class="the-header">
    <div class="header-logo" @click="goHome">
      <svg class="icon">
        <use xlink:href="#icon-erji"></use>
      </svg>
      <span>music</span>
    </div>
    <ul class="navbar navbar1">
      <li v-for="item in navMsg" :key="item.path" @click="goPage(item.path)">
        {{ item.name }}
      </li>
    </ul>
    <ul class="navbar search-position">
      <li>
        <div class="header-search">
          <input
            type="text"
            placeholder="搜索音乐"
            @keyup.enter="goSearch()"
            v-model="keywords"
          />
          <div class="search-btn" @click="goSearch()">
            <svg class="icon">
              <use xlink:href="#icon-sousuo"></use>
            </svg>
          </div>
        </div>
      </li>
    </ul>
    <div class="navbar navbar1 right-position" v-show="!Login">
      <ul>
        <li v-for="(item, index) in User" :key="index" @click="emerge()">
          {{ item.name }}
        </li>
      </ul>
    </div>
    <div class="navbar navbar1 right-position" v-show="Login">
      <div id="user">
        <img :src="attachImageUrl(avator)" />
      </div>
      <!-- 后期改样式，用这个样式navbar navbar1 right-position的盒子，高度变成竖直方向就有下拉菜单了 -->
      <ul class="menu">
        <li
          v-for="(item, index) in menuList"
          :key="index"
          @click="goMenuList(item.path)"
        >
          {{ item.name }}
        </li>
      </ul>
    </div>
    <div class="btn-fullscreen" @click="handleFullScreen">
      <el-tooltip
        :content="fullscreen ? `取消全屏` : `全屏`"
        placement="bottom"
      >
        <i class="el-icon-rank"></i
      ></el-tooltip>
    </div>
  </div>
</template>

<script>
import { navMsg, User, menuList } from "@/assets/data/header.js";
import { mapGetters } from "vuex";
export default {
  name: "TheHeader",
  computed: {
    ...mapGetters(["Login", "avator"]),
  },
  data() {
    return {
      navMsg: [], //左侧导航栏
      User: [], //右侧导航栏
      menuList: [], //用户下拉菜单
      keywords: "", //搜索关键字
      fullscreen: false,
    };
  },
  created() {
    this.navMsg = navMsg;
    this.User = User;
    this.menuList = menuList;
  },
  mounted() {
    document.querySelector("#user").addEventListener(
      "click",
      (e) => {
        document.querySelector(".menu").classList.add("show");
        e.stopPropagation(); //阻止冒泡
      },
      false
    );
  },
  methods: {
    //提示信息
    notify(title, type) {
      this.$notify({
        title: title,
        type: type,
      });
    },
    goHome() {
      this.$router.push("/Home");
    },
    goPage(path) {
      if (!this.Login && path == "/MyMusic") {
        this.notify("请先登录", "warning");
      } else {
        this.$router.push({ path: path });
      }
    },
    goSearch() {
      this.$router.push({
        path: "/Search",
        query: { keywords: this.keywords },
      });
    },
    //获取图片地址
    attachImageUrl(srcUrl) {
      return srcUrl
        ? this.$store.state.configure.HOST + "/" + srcUrl
        : this.$store.state.configure.HOST + "/img/mn.jpg";
    },
    //登录注册框显示
    emerge() {
      this.$store.commit("setdialogFormVisible", true);
    },
    //用户信息与退出
    goMenuList(path) {
      if (path == 0) {
        this.$store.commit("setLogin", false);
        this.$router.go(0); //刷新页面
      } else {
        this.$router.push({ path: path });
      }
    },
    //全屏事件
    handleFullScreen() {
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen(); //safari,Chrome
        } else if (document.webkitCancelFullScreen) {
          //firefox
          document.webkitCancelFullScreen();
        } else if (document.mozCancelFullScreen) {
          //ie
          document.mozCancelFullScreen();
        } else if (document.msExitFullScreen) {
          document.msExitFullScreen();
        }
      } else {
        let element = document.documentElement;
        if (element.requestFullscreen) {
          element.requestFullscreen();
          //safari,Chrome
        } else if (element.webkitRequestFullScreen) {
          //firefox
          element.webkitRequestFullScreen();
        } else if (element.mozRequestFullScreen) {
          //ie
          element.mozRequestFullScreen();
        } else if (element.msRequestFullScreen) {
          element.msRequestFullScreen();
        }
      }
      this.fullscreen = !this.fullscreen;
    },
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/the-header.css";
</style>
