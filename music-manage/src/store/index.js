import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    HOST: "http://127.0.0.1:8888",
    isPlay: false, //是否播放中
    url: "", //歌曲地址
    id: "", //歌曲id
    singerId: "", //歌手id
    songId: "", //歌单歌曲的id
  },
  //取值
  getters: {
    isPlay: (state) => state.isPlay,
    url: (state) => state.url,
    id: (state) => state.id,
    singerId: (state) => state.singerId,
    songId: (state) => state.songId,
  },
  //设置值
  mutations: {
    //这里的isPlay是外界传过来的值，不是getter里的
    setisPlay: (state, isPlay) => {
      state.isPlay = isPlay;
    }, //这里的url是外界传过来的值，不是getter里的
    setUrl: (state, url) => {
      state.url = url;
    }, //这里的id是外界传过来的值，不是getter里的
    setId: (state, id) => {
      state.id = id;
    }, //这里的singerId是外界传过来的值，不是getter里的
    setSingerId: (state, singerId) => {
      state.singerId = singerId;
    },
    setSongId: (state, songId) => {
      state.songId = songId;
    },
  },
});

export default store;
