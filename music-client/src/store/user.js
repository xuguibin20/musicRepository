const user = {
  state: {
    dialogFormVisible: false, //登录注册框的显示与隐藏
  },
  getters: {
    dialogFormVisible: (state) => state.dialogFormVisible,
  },
  mutations: {
    setdialogFormVisible: (state, dialogFormVisible) => {
      state.dialogFormVisible = dialogFormVisible;
      window.sessionStorage.setItem(
        "dialogFormVisible",
        JSON.stringify(dialogFormVisible)
      );
    },
  },
};

export default user;
