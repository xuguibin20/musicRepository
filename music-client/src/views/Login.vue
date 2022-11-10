<template>
  <div class="main">
    <div class="nav1">
      <transition name="fade2" appear>
        <div class="nav1-left">
          <img src="@/assets/img/Login/footer.jpg" alt="" />
        </div>
      </transition>
      <div class="nav1-right fade1">
        <el-button type="primary" round @click="emerge">登录/注册</el-button>
      </div>
    </div>
    <div class="wrapper">
      <div class="content1">
        <h1 class="fade1">
          <span style="color: rgb(95, 138, 255)">音乐,</span>陪你做生活的演奏师
        </h1>

        <transition name="fade2" appear>
          <div class="searchBox">
            <div class="searchBox-content">
              <div class="radius">
                <span> <img src="@/assets/img/Login/search.png" /> </span>
                <div style="display: inline-block; width: 80%">
                  <input
                    type="text"
                    placeholder="好歌好曲等你来发现"
                    @keyup.enter="goSearch()"
                    v-model="keywords"
                  />
                </div>
              </div>
            </div>
          </div>
        </transition>

        <div class="shadow"></div>
        <div class="footer fade1">
          <h3>
            <span style="color: rgb(95, 138, 255)">音乐,</span>
            探索更多，发现更多
          </h3>
          <el-button plain @click="dialogVisible = true">了解更多</el-button>
        </div>
        <div class="swiper1">
          <div class="swiper1-content">
            <div class="margin div6">
              <div class="slider move6">
                <a href=""><img src="@/assets/img/Login/a.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/b.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/c.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/d.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/b.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 100px">
            <div class="margin div5">
              <div class="slider move5">
                <a href=""><img src="@/assets/img/Login/e.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/f.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/g.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/h.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/e.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/f.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 160px">
            <div class="margin div3">
              <div class="slider move3">
                <a href=""><img src="@/assets/img/Login/i.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/j.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/k.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/l.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/i.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/j.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 220px">
            <div class="margin div1">
              <div class="slider move1">
                <a href=""><img src="@/assets/img/Login/m.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/n.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/o.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/p.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/m.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/n.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 200px">
            <div class="margin div2">
              <div class="slider move2">
                <a href=""><img src="@/assets/img/Login/q.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/r.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/s.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/t.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/q.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/r.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 120px">
            <div class="margin div4">
              <div class="slider move4">
                <a href=""><img src="@/assets/img/Login/u.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/v.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/w.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/x.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/u.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/v.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content" style="margin-top: 100px">
            <div class="margin div5">
              <div class="slider move5">
                <a href=""><img src="@/assets/img/Login/y.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/z.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a1.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a2.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/y.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/z.jpg" alt="" /></a>
              </div>
            </div>
          </div>
          <div class="swiper1-content">
            <div class="margin div7">
              <div class="slider move7">
                <a href=""><img src="@/assets/img/Login/a3.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a4.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a5.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a6.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a3.jpg" alt="" /></a>
                <a href=""><img src="@/assets/img/Login/a4.jpg" alt="" /></a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <User></User>
    <el-dialog width="450px" center :visible.sync="dialogVisible">
      <el-form>
        <el-form-item>
          <div>
            <img
              src="@/assets/img/author.jpg"
              style="margin: 0 auto; width: 400px; height: 400px"
            />
          </div>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { mixin } from "@/mixins/index";
export default {
  name: "Login",
  mixins: [mixin],

  computed: {
    ...mapGetters(["dialogFormVisible"]),
  },

  data() {
    return {
      dialogVisible: false,

      keywords: "", //搜索关键词
    };
  },
  methods: {
    //转向搜索页面
    goSearch() {
      this.$router.push({
        path: "/Search",
        query: { keywords: this.keywords },
      });
    },
    //登录注册框出现
    emerge() {
      this.$store.commit("setdialogFormVisible", true);
    },
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/login.css";
@import "@/assets/css/move.css";
@import "@/assets/css/fade.css";
</style>
