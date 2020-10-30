<template>
  <div class="app-container">
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="加载中..."
      border
      fit
      highlight-current-row
    >
      <el-table-column label="标题">
        <template slot-scope="scope">
          {{ scope.row.articleTitle }}
        </template>
      </el-table-column>
      <el-table-column label="作者" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.author }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类型" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.type }}
        </template>
      </el-table-column>
      <el-table-column label="分类" width="110" align="center">
        <template slot-scope="scope">
          {{ scope.row.category }}
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="标签" width="110" align="center">
        <template slot-scope="scope">
          <v-tag v-for="(tag,index)in scope.row.tags " :key="index" size="mini" type="success">
            {{ tag }}
          </v-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="创建时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.insertTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="更新时间" width="200">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.updateTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" width="230">
        <template>
          <el-button type="primary" size="mini">删除</el-button>
          <el-button type="primary" size="mini">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      :hide-on-single-page="true"
      :current-page="pageInfo.current"
      :page-size="pageInfo.size"
      layout="total, prev, pager, next, jumper"
      :total="pageInfo.total"
      @current-change="handleCurrentChange"
    />
  </div>
</template>

<script>
import { findArticleList } from '@/api/article'

export default {
  data() {
    return {
      pageInfo: {
        size: 10,
        current: 1,
        total: 0,
        records: []
      },
      listLoading: true
    }
  },
  created() {
    this.fetchData(1, 10)
  },
  methods: {
    fetchData(page, limit) {
      this.listLoading = true
      findArticleList({ page, limit }).then((res) => {
        this.pageInfo = res.data
        this.listLoading = false
      })
    },
    handleCurrentChange(page) {
      this.fetchData(page, 10)
    }
  }
}
</script>

<style lang="scss" scoped>
.el-tag {
  margin: 0 5px;
}
.el-pagination {
  margin-top: 10px;
}
</style>
