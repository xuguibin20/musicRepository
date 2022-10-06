import Vue from 'vue';
import App from '@/App.vue';
import router from '@/router/index.js';
import store from '@/store/index.js'
import Element from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import '@/assets/css/main.css'



Vue.config.productionTip = false
Vue.use(Element, { size: 'small', zIndex: 3000 });
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
