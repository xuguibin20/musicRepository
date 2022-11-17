<template>
  <div class="upload">
    <p class="title">修改头像</p>
    <hr />
    <div class="section">
      <el-upload
        :action="uploadUrl()"
        :show-file-list="false"
        :on-success="handleAvatorSuccess"
        :before-upload="beforeAvatorUpload"
        drag
      >
        <i class="el-icon-upload"></i>
        <div>将文件拖到此处，或<span style="color: blue">修改头像</span></div>
        <div slot="tip">只能上传jpg/png文件,且不能超过10MB</div>
      </el-upload>
    </div>
  </div>
</template>
<script>
import { mixin } from "@/mixins/index.js";

import { mapGetters } from "vuex";

export default {
  name: "Upload",
  mixins: [mixin],
  computed: {
    ...mapGetters([
      "userId", //登录用户id
    ]),
  },
  methods: {
    //上传地址
    uploadUrl() {
      //更新图片
      return `${this.$store.state.configure.HOST}/consumer/updateConsumerAvator?id=${this.userId}`;
    },

    //上传成功
    handleAvatorSuccess(res, file) {
      if (res.code == 1) {
        this.$store.commit("setAvator", res.avator);
        this.notify("修改成功", "success");
      } else {
        this.notify("修改失败", "error");
      }
    },

    beforeAvatorUpload(file) {
      const isJPG = file.type == "image/jpeg";
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isJPG) {
        this.notify("上传的头像图片只能是JPG格式", "error");
        return false;
      }
      if (!isLt10M) {
        this.notify("上传的头像图片不能大于10M", "error");
        return false;
      }
      return true;
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/upload.css";
</style>
