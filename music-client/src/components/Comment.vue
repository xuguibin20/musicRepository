<template>
  <div class="comment">
    <h2>评论</h2>
    <div class="comment-msg">
      <div class="comment-img">
        <img :src="attachImageUrl(avator)" alt="" />
      </div>
    </div>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mixin } from "@/mixins/index.js";
export default {
  mixins: [mixin],
  name: "Comment",
  props: [
    "playId", //歌曲或歌单id
    "type", //0歌曲，1歌单
  ],
  data() {
    return {};
  },
  computed: {
    ...mapGetters([
      "id", //当前歌曲或歌单的id
      "avator", //当前登录用户的头像
      "Login", //用户是否登录
      "userId", //当前登录的用户id
    ]),
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/comment.css";
</style>
