<template>
  <div class="main">
    <div class="nav1">
      <div class="nav1-left">
        <img src="@/assets/logo.png" alt="" />
      </div>
      <div class="nav1-right">
        <el-button type="primary" round>登录/注册</el-button>
      </div>
    </div>
    <div class="wrapper">
      <div class="content1">
        <!-- <div class="mark"></div> -->
        <transition name="fade" appear>
          <h1>
            <span style="color: rgb(95, 138, 255)">音乐,</span>做你生活的演奏师
          </h1>
        </transition>
        <transition name="fade1" appear>
          <div class="searchBox">
            <div class="searchBox-content">
              <div class="radius">
                <span>11111 </span>
                <input type="text" placeholder="好歌好曲等你来发现" />
              </div>
            </div>
          </div>
        </transition>

        <div class="shadow"></div>
        <div class="footer"></div>
        <div class="swiper1">
          <div class="swiper1-content">
            <transition name="div6" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/a.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/b.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/c.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/d.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 100px">
            <transition name="div5" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/e.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/f.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/g.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/h.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 160px">
            <transition name="div3" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/i.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/j.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/k.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/l.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 220px">
            <transition name="div1" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/m.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/n.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/o.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/p.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 200px">
            <transition name="div2" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/q.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/r.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/s.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/t.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 120px">
            <transition name="div4" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/u.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/v.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/w.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/x.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content" style="margin-top: 100px">
            <transition name="div5" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/y.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/z.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/a1.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/a2.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
          <div class="swiper1-content">
            <transition name="div7" appear>
              <div class="margin">
                <div class="slider">
                  <a href=""><img src="@/assets/img/Login/a3.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/a4.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/a5.jpg" alt="" /></a>
                  <a href=""><img src="@/assets/img/Login/a6.jpg" alt="" /></a>
                </div>
              </div>
            </transition>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {};
  },
};
</script>
<style lang="less" scoped>
@import "@/assets/css/fade.css";

* {
  margin: 0;
  padding: 0;
}

.main {
  height: 100%;
  background-color: rgb(255, 255, 255);
  overflow: hidden;
}
.nav1 {
  position: fixed;
  z-index: 4;
  line-height: 60px;
  width: 100%;
  height: 60px;
}

.nav1-left {
  height: 60px;
  float: left;
  margin-left: 40px;
}
.nav1-left img {
  height: 100%;
}
.nav1-right {
  height: 60px;
  float: right;
  margin-right: 40px;
}
.nav1-content {
  display: flex;
}
.wrapper {
  height: 100%;
}
.content1 {
  position: relative;
  padding-top: 60px;
}

h1 {
  font-family: "Courier New", Courier, monospace;
  letter-spacing: 15px;
  font-size: 60px;
  text-align: center;
  padding-top: 120px;
  padding-bottom: 30px;
}

.searchBox {
  float: left;
  position: absolute;
  z-index: 1;
  margin: 0 35%;
  background-color: rgb(255, 255, 255);

  width: 30%;
  height: 56px;
}

.searchBox-content {
  width: 100%;
  box-sizing: border-box;
  height: 100%;
  border-radius: 28px;
  line-height: 56px;
  border: 1px solid rgb(233, 232, 232);
  box-shadow: 10px 10px 50px 0 rgb(214, 214, 214);
}
.searchBox-content:hover {
  box-shadow: 10px 10px 50px 0 rgb(212, 231, 252);
}
.radius {
  width: 401.89px;
  height: 100%;
  line-height: 56px;
  margin: 0 auto;
}
.searchBox-content img {
  height: 100%;
}
.searchBox-content input {
  height: 40px;
  border: none;
}
.searchBox-content input:focus {
  color: #000;
  outline: 0;
}
.swiper1 {
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
}
.swiper1-content {
  width: 200px;
  height: 100%;
  margin: 0 10px 0;
}
.margin {
  width: 200px;
  height: 4448px;

  margin-top: 10px;
  overflow: hidden;
}
.slider {
  width: 200px;
  height: 4400px;

  /* transform: translateY(-948px); */
}

.slider img {
  width: 200px;
  height: 300px;
  margin-bottom: 15px;
  border-radius: 15px;
}

.shadow {
  float: left;
  position: absolute;
  z-index: 1;
  margin: 350px 0 0;
  background-image: linear-gradient(0deg, white, white, transparent);
  width: 100%;
  height: 600px;
}
.footer {
  float: left;
  position: absolute;
  z-index: 2;
  margin: 615px 0 0;
  background-color: rgb(255, 255, 255);
  width: 100%;
  height: 80px;
}
</style>
