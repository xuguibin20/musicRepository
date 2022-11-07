const configure = {
  state: {
    HOST: "http://127.0.0.1:8888", //后台访问地址根目录
    showAside: false, //是否显示歌曲列表
  },
  getters: {
    showAside: (state) => {
      let showAside = state.showAside;
      if (!showAside.length) {
        showAside = JSON.parse(
          window.sessionStorage.getItem("showAside") || null
        );
      }
      return showAside;
    },
  },
  mutations: {
    setShowAside: (state, showAside) => {
      state.showAside = showAside;
      window.sessionStorage.setItem("showAside", JSON.stringify(showAside));
    },
  },
};

export default configure;
