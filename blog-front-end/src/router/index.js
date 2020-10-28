import VueRouter from 'vue-router';
import Vue from 'vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    name: 'Home',
    component: ()=>import(/* webpackChunkName: "home" */"@/views/home.vue"),
    meta:{
      title: "HFL博客",
    },
  },
  {
    path: '/category/:name',
    name: 'category',
    component: () => import(/* webpackChunkName: "category" */ '@/views/category.vue'),
    meta: {
      title: "文章分类",
    },
  },
  {
    path: '/tag/:name',
    name:'tag',
    component:()=>import('@/views/tag.vue'),
    meta:{
      title: "文章标签",
    },
  },
  {
    path: '/timeline',
    name:'timeline',
    component:()=>import('@/views/timeline.vue'),
    meta:{
      title: "时间线",
    }
  },
  {
    path: '/message',
    name:'message',
    component:()=>import('@/views/message.vue'),
    meta:{
      title: "留言板",
    }
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

router.beforeEach((to,from,next)=>{
  console.log(from);
  if(to.meta.title){
      document.title=to.meta.title;
  }
  next();
});

export default router;
