import Vue from "vue";
import App from "./App.vue";
import TheHeader from "@/components/TheHeader.vue";
import ScrollTop from "@/components/ScrollTop.vue";
import TheFooter from "@/components/TheFooter.vue";
import PlayBar from "@/components/PlayBar.vue";
import TheList from "@/components/TheList.vue";
import User from "@/components/User.vue";
import router from "@/router/index.js";
import store from "@/store/index.js";
import Element from "element-ui";

import "element-ui/lib/theme-chalk/index.css";
import "@/assets/css/main.css";
import "@/assets/js/iconfont.js";
import "@/assets/js/iconfont1.js";
import "@/assets/js/iconfont2.js";
import "@/assets/js/iconfont3.js";

Vue.config.productionTip = false;
Vue.use(Element, { size: "small", zIndex: 3000 });

Vue.component("TheHeader", TheHeader);
Vue.component("ScrollTop", ScrollTop);
Vue.component("TheFooter", TheFooter);
Vue.component("PlayBar", PlayBar);
Vue.component("TheList", TheList);
Vue.component("User", User);

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
