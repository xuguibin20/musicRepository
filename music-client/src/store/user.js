const user = {
  state: {
    dialogFormVisible: false, //登录注册框的显示与隐藏

    userId: "", //用户id
    userName: "", //用户账号
    avator: "", //用户头像
  },
  getters: {
    dialogFormVisible: (state) => state.dialogFormVisible,

    userId: (state) => state.userId,
    userName: (state) => state.userName,
    avator: (state) => state.avator,
  },
  mutations: {
    setdialogFormVisible: (state, dialogFormVisible) => {
      state.dialogFormVisible = dialogFormVisible;
      window.sessionStorage.setItem(
        "dialogFormVisible",
        JSON.stringify(dialogFormVisible)
      );
    },

    setuserId: (state, userId) => {
      state.userId = userId;
      window.sessionStorage.setItem("userId", JSON.stringify(userId));
    },
    setuserName: (state, userName) => {
      state.userName = userName;
      window.sessionStorage.setItem("userName", JSON.stringify(userName));
    },
    setAvator: (state, avator) => {
      state.avator = avator;
      window.sessionStorage.setItem("avator", JSON.stringify(avator));
    },
  },
};

export default user;
