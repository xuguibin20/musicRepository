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
    <ul class="navbar navbar1 right-position">
      <li v-show="!Login" v-for="item in User" :key="item.path" @click="emerge">
        {{ item.name }}
      </li>
    </ul>
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
import { navMsg, User } from "@/assets/data/header.js";
import { mapGetters } from "vuex";
export default {
  name: "TheHeader",
  computed: {
    ...mapGetters(["Login"]),
  },
  data() {
    return {
      navMsg: [], //左侧导航栏
      User: [], //右侧导航栏
      keywords: "", //搜索关键字
      fullscreen: false,
    };
  },
  created() {
    this.navMsg = navMsg;
    this.User = User;
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
      this.$router.push("/");
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
    //登录注册框显示
    emerge() {
      this.$store.commit("setdialogFormVisible", true);
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
