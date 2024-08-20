<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <!-- 发布文章 -->
      <div class="article-title-container">
        <el-input
          size="medium"
          v-model="article.articleTitle"
          placeholder="输入文章标题"
        />
        <el-button
          type="danger"
          size="medium"
          @click="openDialog"
          style="margin-left: 10px"
          >发布文章</el-button
        >
      </div>

      <!-- 文章内容 -->
      <mavon-editor
        ref="md"
        v-model="article.articleContent"
        style="height: calc(100vh - 260px)"
      />
    </div>
    <!-- 弹出框确认发布 -->
    <el-dialog
      :title="`确认发布：${article.articleTitle}`"
      :visible.sync="showDialog"
      width="30%"
    >
      <span slot="footer">
        <el-button @click="handleCancel">取 消</el-button>
        <el-button type="primary" @click="handleSubmit">确认</el-button>
      </span>
    </el-dialog>
  </el-card>
</template>



<script>
import {
  listArticles,
  getArticles,
  delArticles,
  addArticles,
  updateArticles,
} from "@/api/articles/articles";
export default {
  created() {
    // console.log(this.$route.query.articleId);
    const articleId = this.$route.query.articleId;
    if (articleId) {
      getArticles(articleId).then((res) => {
        this.article = res.data;
      });
    }
  },
  name: "Addnotice",
  data() {
    return {
      showDialog: false,
      article: {
        articleContent: "",
        articleTitle: "",
      },
    };
  },

  methods: {
    // 打开文章信息填写框
    openDialog() {
      if (
        this.assertNotEmpty(this.article.articleTitle, "请填写标题") &&
        this.assertNotEmpty(this.article.articleContent, "请填写内容")
      ) {
        this.showDialog = true;
      }
    },

    assertNotEmpty(target, msg) {
      if (!target) {
        this.$message({
          message: msg,
          type: "warning",
        });
        return false;
      }
      return true;
    },
    handleCancel() {
      this.showDialog = false;
    },

    handleSubmit() {
      this.showDialog = true;
      if (this.$route.query.articleId) {
        // console.log("修改操作");
        this.article.auditStatus = 0;
        this.article.updateTime = this.$moment().format("YYYY-MM-DD HH:mm:ss");
        // console.log(this.article);
        updateArticles(this.article).then((response) => {
          this.$notify({
            title: "提示",
            message: "修改成功",
            type: "success",
          });
          //刷新
        });
      } else {
        this.article.userId = this.$store.state.user.id;
        this.article.auditStatus = 0;
        this.article.isDeleted = 0;
        this.article.createTime = this.$moment().format("YYYY-MM-DD HH:mm:ss");
        this.article.updateTime = this.$moment().format("YYYY-MM-DD HH:mm:ss");
        this.article.autherName = this.$store.state.user.name; /*"李田所"*/
        // console.log(this.$store.state.user.nickName);
        addArticles(this.article).then((response) => {
          this.$notify({
            title: "提示",
            message: "发布成功",
            type: "success",
          });
          //刷新
        });
      }
      this.showDialog = false;
    },
  },
};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.box-card {
  width: 98%;
  margin: 1%;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.clearfix span {
  font-weight: 600;
}
.article-title-container {
  display: flex;
  align-items: center;
  margin-bottom: 1.25rem;
  margin-top: 1.25rem;
}
</style>
