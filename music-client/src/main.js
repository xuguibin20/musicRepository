import Vue from 'vue'
import App from './App.vue'
import router from '@/router/index.js';
import store from '@/store/index.js';
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/main.css'
import '@/assets/js/iconfont.js'
import '@/assets/js/iconfont1.js'
import '@/assets/js/iconfont2.js'
import '@/assets/js/iconfont3.js'
import animate from "animate.css";

Vue.config.productionTip = false
Vue.use(Element, { size: 'small', zIndex: 3000 });
Vue.use(animate);

new Vue({
  router,
    store,
  render: h => h(App),
}).$mount('#app')
