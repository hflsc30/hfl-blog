<template>
  <div>
    <el-carousel
      indicator-position="none"
      height="400px"
      arrow="nerver"
      :interval="5000"
    >
      <el-carousel-item
        v-for="item in blogInfo.covers"
        :key="item"
      >
        <div class="item-box">
          <img
            :src="item"
            class="carimg"
          >
          <div class="desc-box">
            <h1>{{ blogInfo.title }}</h1>
            <p>{{ blogInfo.desc }}</p>
          </div>
        </div>
      </el-carousel-item>
    </el-carousel>
    <el-row :gutter="20">
      <el-col
        :span="14"
        :offset="2"
      >
        <article-item
          v-for="article in pageInfo.records"
          :key="article.id"
          :article="article"
        />
      </el-col>
      <el-col :span="6">
        <blog-info />
      </el-col>
    </el-row>
    <!-- 页面回到顶部 -->
    <el-backtop>
      <div
        style="{
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0,0,0, .12);
        text-align: center;
        line-height: 30px;
        color: #1989fa;
        }"
      >
        回到顶部
      </div>
    </el-backtop>
    <!-- 分页 -->
    <el-pagination
      background
      @current-change="handleCurrentChange"
      :current-page.sync="pageInfo.current"
      :page-size="pageInfo.size"
      layout="prev, pager, next, jumper"
      :total="pageInfo.total"
      :hide-on-single-page="true"
    />
  </div>
</template>

<script>
import request from "@/http/request";
import {mapState} from "vuex";
import ArticleItem from "@/components/articleItem";
export default {
  name: 'Home',
  data(){
    return {
      /*carousel: {
        title: "HFL博客",
        desc: "万事胜意",
        urls:[
          "https://tvax1.sinaimg.cn/mw1024/bfe05ea9ly1fxgu8jys3fj21hc0u0k0j.jpg",
          "https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgunx09dtj21hc0u0q81.jpg",
          "https://tvax1.sinaimg.cn/large/bfe05ea9ly1fxgv2t92yyj21hc0u0qb9.jpg"
        ]
      },*/
      pageInfo: {},
      // tagList: []
    }
  },
  computed: mapState(["blogInfo"]),
  components: {
    ArticleItem,
    BlogInfo: () => import("@/components/blogInfo.vue")
  },
  methods:{
    handleCurrentChange(page) {
      this.getPageArticles(page, 5);
    },
    getPageArticles(page, limit) {
      request
          .findArticles(page, limit)
          .then(res => {
            if (res.code === 0) {
              this.pageInfo = res.data;
            } else {
              this.$notify.error({
                title: "提示",
                message: res.msg
              });
            }
          })
          .catch(err => {
            console.log(err);
            this.$notify.error({
              title: "提示",
              message: "网络忙，文章获取失败"
            });
          });
    }
  },
  created() {
    this.getPageArticles(1,5);
  }
}
</script>

<style scoped>
.item-box {
  position: relative;
  width: 100%;
  height: 100%;
}
.carimg {
  width: 100%;
  height: 100%;
  overflow: hidden;
  object-fit: cover;
}
.desc-box {
  position: absolute;
  bottom: 0;
  left: 50%;
  top: 50%;
  width: 500px;
  height: 40px;
  margin-left: -250px;
  margin-top: -20px;
  text-align: center;
}
.el-card {
  margin-top: 20px;
}
.article-info {
  margin-top: 10px;
  color: #909399;
  font-size: 13px;
}
.article-icon,
.article-icon .tag {
  color: #909399;
  font-size: 13px;
  margin-right: 10px;
  text-decoration: none;
}
.article-icon .tag:hover {
  color: #409eff;
  cursor: pointer;
}
.tabloid {
  color: #606266;
  font-size: 14px;
  margin-bottom: 10px;
}
.el-pagination {
  margin: 20px 0;
  text-align: center;
}
</style>
