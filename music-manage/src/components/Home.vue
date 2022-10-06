<template>
  <div>
    <TheHeader></TheHeader>
    <TheAside></TheAside>
    <div class="content-box" :class="{ 'content-collapse': collapse }">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import TheHeader from "@/components/TheHeader.vue";
import TheAside from "@/components/TheAside.vue";
import bus from "@/assets/js/bus.js";
export default {
  components: {
    TheHeader,
    TheAside,
  },
  data() {
    return {
      collapse: false,
    };
  },
  created() {
    //通过bus进行组件间的通信，来折叠侧边栏
    bus.$on("collapse", (msg) => {
      this.collapse = msg;
    });
  },
};
</script>

<style scoped>
.content-box {
  position: absolute;
  left: 150px;
  right: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
  -webkit-transition: left 0.3s ease-in-out;
  transition: left 0.3s ease-in-out;
  background: #f0f0f0;
}
</style>
