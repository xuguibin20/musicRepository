<template>
  <div class="swiper">
    <el-carousel :interval="3000" type="card" height="300px">
      <el-carousel-item v-for="(item, index) in swiperList" :key="index">
        <img :src="item.picImg" />
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import { swiperList } from "@/assets/data/swiper.js";
export default {
  name: "Swiper",
  data() {
    return {};
  },
  created() {
    this.swiperList = swiperList;
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/swiper.css";
</style>
