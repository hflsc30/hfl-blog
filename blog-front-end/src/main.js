import Vue from 'vue'
import App from './App.vue'
import router from "@/router/index"
import store from "@/store/index"
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import moment from "moment";
// highlight.js 中自带很多 css 高亮样式,使用 github 样式
import 'highlight.js/styles/github.css';
// 将 markdown 转为 html
import { VueShowdown } from "vue-showdown";
import 'github-markdown-css/github-markdown.css';


Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.$moment = moment
Vue.component('VueShowdown',VueShowdown)

new Vue({
    render: h=>h(App),
    store,
    router,
    created: initBlog,
}).$mount('#app')

function initBlog(){
    store.dispatch('getBlogInfo');
}
