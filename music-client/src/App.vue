<template>
  <div id="app">
    <!-- <TheHeader></TheHeader> -->
    <router-view />
    <!-- <ScrollTop></ScrollTop> -->
    <!-- <TheFooter></TheFooter> -->
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import ScrollTop from "@/components/ScrollTop.vue";
import TheFooter from "@/components/TheFooter.vue";
export default {
  name: "App",
  components: { TheHeader, ScrollTop, TheFooter },
};
</script>

<style lang="less" scoped>
// @import "@/assets/css/app.css";
* {
  margin: 0;
  padding: 0;
}

#app {
  opacity: 1;
  height: 100vh;
}
</style>
