//左侧导航栏
const navMsg = [
  { name: "首页", path: "/Home" },
  { name: "歌单", path: "/SongList" },
  { name: "歌手", path: "/Singer" },
  { name: "我的音乐", path: "/MyMusic" },
];

//右侧导航栏
const User = [{ name: "登录" }, { name: "注册" }];

//用户下拉菜单
const menuList = [
  { name: "个人资料", path: "/Setting" },
  { name: "退出", path: 0 },
];

export { navMsg, User, menuList };
