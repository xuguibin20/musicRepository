<template>
  <div>
    <!-- 数据的gutter间距 -->
    <el-row :gutter="20" class="mgb20">
      <!-- 用户总数 -->
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-content-center">
              <div class="grid-nim">{{ consumerCount }}</div>
              <div>用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <!-- 歌曲总数 -->
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-content-center">
              <div class="grid-nim">{{ songCount }}</div>
              <div>歌曲总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <!-- 歌手数量 -->
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-content-center">
              <div class="grid-nim">
                {{ singerCount }}
              </div>
              <div>歌手数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <!-- 歌单数量 -->
      <el-col :span="6">
        <el-card>
          <div class="grid-content">
            <div class="grid-content-center">
              <div class="grid-nim">{{ songListCount }}</div>
              <div>歌单数量</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!-- 图形的gutter间距 -->
    <el-row :gutter="20" class="mgb20">
      <!-- 用户性别比例 -->
      <el-col :span="12">
        <h3 class="mgb20">用户性别</h3>
        <div style="background-color: white">
          <ve-pie :data="consumerSex" :theme="options"> </ve-pie>
        </div>
      </el-col>
      <!-- 歌曲类型比例 -->
      <el-col :span="12">
        <h3 class="mgb20">歌曲类型</h3>
        <div style="background-color: white">
          <ve-histogram :data="songStyle"> </ve-histogram>
        </div>
      </el-col>
      <!-- 歌手性别比例 -->
      <el-col :span="12">
        <h3 class="mgb20">歌手性别</h3>
        <div style="background-color: white">
          <ve-pie :data="singerSex"> </ve-pie>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {
  getAllConsumer, //用户总数
  getAllSinger, // 歌手数量
  getAllSongList, // 歌单数量
  getAllSong, // 歌曲总数
} from "@/api/index";
export default {
  name: "InfoPage",
  data() {
    return {
      Style: [], //歌单风格
      consumerCount: 0, //用户总数
      songCount: 0, // 歌曲总数
      singerCount: 0, // 歌手数量
      songListCount: 0, // 歌单数量
      consumer: [], //所有用户
      song: [], //所有歌曲
      singer: [], //所有歌手
      songList: [], //所有歌单
      //用户性别
      consumerSex: {
        columns: ["性别", "总数"],
        rows: [
          { 性别: "女", 总数: 0 },
          { 性别: "男", 总数: 0 },
        ],
      },
      //歌手性别
      singerSex: {
        columns: ["性别", "总数"],
        rows: [
          { 性别: "女", 总数: 0 },
          { 性别: "男", 总数: 0 },
          { 性别: "组合", 总数: 0 },
          { 性别: "不明", 总数: 0 },
        ],
      },
      options: {
        color: ["#87cefa", "#ffc0cb"],
      },
      songStyle: {
        columns: ["风格", "总数"],
        rows: [
          { 风格: "华语", 总数: 0 },
          { 风格: "粤语", 总数: 0 },
          { 风格: "欧美", 总数: 0 },
          { 风格: "日语", 总数: 0 },
          { 风格: "韩语", 总数: 0 },
          { 风格: "轻音乐", 总数: 0 },
          { 风格: "BGM", 总数: 0 },
          { 风格: "乐器", 总数: 0 },
        ],
      },
    };
  },
  //页面加载的时候就开始执行
  created() {},
  // 页面元素加载完后再执行
  mounted() {
    this.getConsumer();
    this.getSong();
    this.getSinger();
    this.getSongList();
  },
  methods: {
    //获取性别数量
    setSex(sex, val) {
      let count = 0;
      for (let item of val) {
        if (sex == item.sex) {
          count++;
        }
      }
      return count;
    },
    //获取歌曲风格
    getByStyle(style) {
      for (let item of this.songStyle.rows) {
        if (style.includes(item["风格"])) {
          item["总数"]++;
        }
      }
    },
    getConsumer() {
      getAllConsumer().then((res) => {
        this.consumer = res;
        this.consumerCount = res.length;
        this.consumerSex.rows[0]["总数"] = this.setSex(0, this.consumer);
        this.consumerSex.rows[1]["总数"] = this.setSex(1, this.consumer);
      });
    },
    getSong() {
      getAllSong().then((res) => {
        this.song = res;
        this.songCount = res.length;
      });
    },
    getSinger() {
      getAllSinger().then((res) => {
        this.singer = res;
        this.singerSex.rows[0]["总数"] = this.setSex(0, this.singer);
        this.singerSex.rows[1]["总数"] = this.setSex(1, this.singer);
        this.singerSex.rows[2]["总数"] = this.setSex(2, this.singer);
        this.singerSex.rows[3]["总数"] = this.setSex(3, this.singer);
        this.singerCount = res.length;
      });
    },
    getSongList() {
      getAllSongList().then((res) => {
        this.songList = res;
        for (let item of res) {
          this.getByStyle(item.style);
        }
        this.songListCount = res.length;
      });
    },
  },
};
</script>
<style lang="less" scoped>
.grid-content {
  display: flex;
  align-items: center;
  height: 50px;
}

.grid-content-center {
  flex: 1;
  text-align: center;
  font-size: 14px;
  color: darkgray;
}

.grid-nim {
  font-size: 30px;
  font-weight: bold;
}
</style>
