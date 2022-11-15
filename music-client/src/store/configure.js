const configure = {
  state: {
    HOST: "http://127.0.0.1:8888", //后台访问地址根目录
    showAside: false, //是否显示歌曲列表
    Login: false, //用户是否登录
    isCollect: false, //当前歌曲是否已经收藏
  },
  getters: {
    isCollect: (state) => {
      let isCollect = state.isCollect;
      if (!isCollect.length) {
        isCollect = JSON.parse(
          window.sessionStorage.getItem("isCollect") || null
        );
      }
      return isCollect;
    },
    Login: (state) => {
      let Login = state.Login;
      if (!Login.length) {
        Login = JSON.parse(window.sessionStorage.getItem("Login") || null);
      }
      return Login;
    },
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
    setisCollect: (state, isCollect) => {
      state.isCollect = isCollect;
      window.sessionStorage.setItem("isCollect", JSON.stringify(isCollect));
    },
    setShowAside: (state, showAside) => {
      state.showAside = showAside;
      window.sessionStorage.setItem("showAside", JSON.stringify(showAside));
    },
    setLogin: (state, Login) => {
      state.Login = Login;
      window.sessionStorage.setItem("Login", JSON.stringify(Login));
    },
  },
};

export default configure;
