<template>
  <div class="comment">
    <h2>评论</h2>
    <div class="comment-msg">
      <div class="comment-img">
        <img :src="attachImageUrl(avator)" alt="" />
      </div>
      <el-input
        class="comment-input"
        type="textarea"
        :rows="4"
        placeholder="请输入内容"
        v-model="textarea"
      ></el-input>
    </div>
    <el-button type="primary" class="sub-btn" @click="postComment"
      >评论</el-button
    >
    <p>精彩评论：共{{ commentList.length }}条评论</p>
    <hr />
    <ul class="popular" v-for="(item, index) in commentList" :key="index">
      <li>
        <div class="popular-img">
          <img :src="attachImageUrl(userPic[index])" alt="" />
        </div>
        <div class="popular-msg">
          <ul>
            <li class="name">{{ userName[index] }}</li>
            <li class="time">{{ item.createTime }}</li>
            <li class="time">{{ item.content }}</li>
          </ul>
        </div>
        <div class="up" ref="up" @click="postUp(item.id, item.up, index)">
          <svg>
            <use xlink:href="#icon-zan"></use>
          </svg>
          {{ item.up }}
        </div>
      </li>
    </ul>
  </div>
</template>
<script>
import { mapGetters } from "vuex";
import { mixin } from "@/mixins/index.js";
import {
  setComment,
  setLike,
  getAllComment,
  getUserOfId,
} from "@/assets/api/index";
export default {
  mixins: [mixin],
  name: "Comment",
  props: [
    "playId", //歌曲或歌单id
    "type", //0歌曲，1歌单
  ],

  computed: {
    ...mapGetters([
      "id", //当前歌曲或歌单的id
      "avator", //当前登录用户的头像
      "Login", //用户是否登录
      "userId", //当前登录的用户id
    ]),
  },
  mounted() {
    this.getComment();
  },
  data() {
    return {
      textarea: "", //评论内容
      commentList: [], //存放评论列表
      userPic: [], //用户头像列表
      userName: [], //用户名称列表
    };
  },
  methods: {
    //提交评论
    postComment() {
      if (this.Login) {
        let params = new URLSearchParams();
        //0歌曲，1歌单
        if (this.type == 0) {
          params.append("songId", this.playId);
        } else {
          params.append("songListId", this.playId);
        }
        params.append("userId", this.userId);
        params.append("type", this.type);
        params.append("content", this.textarea);
        setComment(params)
          .then((res) => {
            if (res.code == 1) {
              this.notify("评论成功", "success");

              this.textarea = "";
              this.getComment();
            } else {
              this.notify("评论失败", "error");
            }
          })
          .catch((err) => {
            this.notify("评论失败", "error");
          });
      } else {
        this.rank = null;
        this.notify("请先登录", "warning");
      }
    },
    //获取评论列表
    getComment() {
      getAllComment(this.type, this.playId)
        .then((res) => {
          this.commentList = res;
          for (let item of res) {
            this.getUsers(item.userId);
          }
        })

        .catch((err) => {
          this.notify("评论加载失败", "error");
        });
    },
    //根据用户id获取用户的头像和名称
    getUsers(id) {
      getUserOfId(id)
        .then((res) => {
          this.userPic.push(res.avator);
          this.userName.push(res.username);
        })
        .catch((err) => {
          this.notify("出错了", "error");
        });
    },
    //给某条评论点赞
    postUp(id, up, index) {
      if (this.Login) {
        let params = new URLSearchParams();
        params.append("id", id);
        params.append("up", up + 1);

        setLike(params)
          .then((res) => {
            if (res.code == 1) {
              this.$refs.up[index].children[0].style.color = "#2796cd";
              this.getComment();
            } else {
              this.notify("点赞失败", "error");
            }
          })
          .catch((err) => {
            this.notify("点赞失败", "error");
          });
      } else {
        this.rank = null;
        this.notify("请先登录", "warning");
      }
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/comment.css";
</style>
