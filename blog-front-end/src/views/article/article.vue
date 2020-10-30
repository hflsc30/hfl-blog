<template>
  <div>
    <div>
      <el-row>
        <div style="height: 40px;display: flex;align-items: center">
          <div>
            <el-page-header
              @back="goBack"
              content="详情页面"
            />
          </div>
        </div>
        <el-col
          :span="20"
          :offset="2"
        >
          <el-card class="markdown-body">
            <h1>{{ article.articleTitle }}</h1>
            <blockquote>{{ '作者:' + article.author + ' | 创建时间: ' + $moment(article.insertTime).format('YYYY-MM-DD HH:ss') + ' | 浏览数: ' + article.clickCount + ' | 标签: ' + article.tags }}</blockquote>
            <VueShowdown
              :markdown="article.articleContent"
              flavor="vanilla"
              :options="{ emoji: true,tables: true}"
              :extensions="extensions"
            />
          </el-card>
        </el-col>
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
      </el-row>
    </div>
  </div>
</template>

<script>
import request from "@/http/request";
import showdownHighlight from "showdown-highlight";
export default {
  name: "Article",
  data(){
    return{
      article:{ articleContent: ""},
      extensions: [showdownHighlight],
    };
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    }
  },
  created() {
    request.getArticle(this.$route.params.id).then(res=>{
      if(res.code ===0){
        this.article = res.data;
      }else {
        this.$notify.error({
          title: '提示',
          message: res.msg,
        });
      }
    }).catch(err=>{
      console.log(err);
      this.$notify.error({
        title: '提示',
        message: "网络繁忙，文章获取失败",
      })
    })
  }
}
</script>

<style scoped>
.el-card {
  margin-top: 15px;
  padding: 20px;
}
</style>
