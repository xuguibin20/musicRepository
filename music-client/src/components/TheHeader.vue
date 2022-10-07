<template>
  <div class="the-header">
    <div class="header-logo" @click="goHome">
      <svg class="icon">
        <use xlink:href="#icon-erji"></use>
      </svg>
      <span>music</span>
    </div>
    <ul class="navbar">
      <li
        v-for="item in navMsg"
        :key="item.path"
        @click="goPage(item.path, item.name)"
      >
        {{ item.name }}
      </li>
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
  </div>
</template>

<script>
import { navMsg } from "@/assets/data/header.js";
export default {
  name: "TheHeader",
  data() {
    return {
      navMsg: [], //导航栏
      keywords: "", //搜索关键字
    };
  },

  methods: {
    goHome() {
      this.$router.push("/");
    },
    goPage(path, name) {
      this.$store.commit("setActiveName", name);
      this.$router.push({ path: path });
    },
    goSearch() {
      this.$router.push({
        path: "/Search",
        query: { keywords: this.keywords },
      });
    },
  },
  created() {
    this.navMsg = navMsg;
  },
};
</script>

<style lang="less" scoped>
@import "@/assets/css/the-header.css";
</style>
