<template>
  <div class="setting-box">
    <TheHeader></TheHeader>

    <div class="setting">
      <div class="leftCol">
        <div class="settingsMainHeader">设置</div>
        <ul class="setting-aside">
          <li
            v-for="(item, index) in settingList"
            :key="index"
            :class="{ activeColor: activeName == item.name }"
            @click="handleClick(item)"
          >
            {{ item.name }}
          </li>
        </ul>
      </div>
      <div class="contentCol">
        <component :is="componentSrc"></component>
      </div>
    </div>
    <ScrollTop></ScrollTop>
    <PlayBar></PlayBar>
    <User></User>
  </div>
</template>

<script>
import Info from "@/components/Info.vue";
import Upload from "@/components/Upload.vue";

import { mixin } from "@/mixins/index.js";

import { mapGetters } from "vuex";
import { getUserOfId } from "@/assets/api/index";
export default {
  name: "Setting",
  components: {
    Info,
    Upload,
  },
  mixins: [mixin],
  computed: {
    ...mapGetters([
      "Login", //用户是否登录
      "userId", //当前登录的用户id
    ]),
  },
  data() {
    return {
      settingList: [
        { name: "个人信息", path: "Info" },
        { name: "修改头像", path: "Upload" },
      ],
      activeName: "个人信息",
      componentSrc: "Info",
    };
  },
  mounted() {},
  methods: {
    //获取个人信息
    getMsg(userId) {
      getUserOfId(userId)
        .then((res) => {
          this.avator = res.avator;
          this.username = res.username;
          if (res.sex == 0) {
            this.userSex = "女";
          } else if (res.sex == 1) {
            this.userSex = "男";
          } else {
            this.userSex = "不明";
          }
          this.birth = this.attachBirth(res.birth);
          this.location = res.location;

          this.introduction = res.introduction;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleClick(item) {
      this.activeName = item.name;
      this.componentSrc = item.path;
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/setting.css";
</style>
