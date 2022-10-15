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
        class="changeColor"
        v-for="item in navMsg"
        :key="item.path"
        @click="goPage(item.path, item.name)"
      >
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
    <ul class="navbar right-position">
      <li
        class="changeColor"
        v-for="item in User"
        :key="item.path"
        @click="goPage(item.path, item.name)"
      >
        {{ item.name }}
      </li>
    </ul>
  </div>
</template>

<script>
import { navMsg, User } from "@/assets/data/header.js";
import { mapGetters } from "vuex";
export default {
  name: "TheHeader",
  data() {
    return {
      navMsg: [], //导航栏
      User: [], //右侧导航栏
      keywords: "", //搜索关键字
    };
  },
  created() {
    this.navMsg = navMsg;
    this.User = User;
  },
  computed: {
    ...mapGetters([
      "activeName", //getters里的？   .changeColorl里加上这一句:class="{ active: item.name == activeName }"
    ]),
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
};
</script>

<style lang="less" scoped>
@import "@/assets/css/the-header.css";
.changeColor:hover {
  background-color: black;
  color: aqua;
}

.right-position {
  margin-left: 550px;
}
</style>
