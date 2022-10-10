import Vue from 'vue';
import Vuex from 'vuex';
Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    HOST: 'http://127.0.0.1:8888',
    isPlay: false,//是否播放中
    url: '',//歌曲地址
    idL:'',//歌曲id
  },
//取值
  getters: {
    isPlay: state => state.isPlay,
    url: state => state.url,
    id:state=>state.id,
  },
  //设置值
  mutations: {
    setisPlay: (state, isPlay) => { state.isPlay = isPlay },//这里的isPlay是外界传过来的值，不是getter里的
    setUrl: (state, url) => { state.url = url },//这里的url是外界传过来的值，不是getter里的
    setId:(state, id) => { state.id = id },//这里的id是外界传过来的值，不是getter里的
  }

});

export default store;