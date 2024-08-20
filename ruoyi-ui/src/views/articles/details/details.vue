<style>
.title {
  text-align: center;
}
.so-items-bottom {
  /* display: flex; */
  /* height: 20px; */
  /* justify-content: space-between; */
  align-items: center;
  color: #555666;
  text-align: center;
  /* line-height: 22px; */
}
.button {
  display: flex;
}
</style>

<template>
  <el-card class="box-card">
    <div slot="header">
      <!-- 文章标题 -->
      <div class="title">
        <h1>{{ article.articleTitle }}</h1>
      </div>
      <!-- 作者与发布时间 -->
      <div class="so-items-bottom">
        <div class="btm-lt">{{ article.autherName }}</div>
        <div class="btm-rt">{{ article.createTime }}</div>
      </div>
      <br />
      <!-- 文章内容 -->
      <div class="content">
        <mavon-editor
          v-model="article.articleContent"
          :toolbarsFlag="false"
          :subfield="false"
          defaultOpen="preview"
          :ishljs="true"
          codeStyle="github-dark"
        />
      </div>
    </div>
    <div class="button" v-if="this.modify == 1 && this.review != 1">
      <el-button type="primary" @click="handleModify()"> 修改 </el-button>
      <el-button type="danger" @click="handleDelete()">删除</el-button>
    </div>
    <div class="button" v-if="this.review == 1">
      <el-button type="primary" @click="handleApprove()"> 审核通过 </el-button>
      <el-button type="danger" @click="handleDisapprove()"
        >审核不通过</el-button
      >
    </div>
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
  data() {
    return {
      showDialog: false,
      article: {
        // articleContent: "深山老林擒恶魔",
        // articleTitle: "深山老林擒恶魔",
        // autherName: "李田所",
        // createTime: "114/5/14",
      },
      review: 0,
      modify: 0,
      admin: 0,
    };
  },
  //根据传入的articleID获得文章内容
  created() {
    // console.log(this.$route.query.articleId);
    const articleId = this.$route.query.articleId;
    // console.log(articleId);
    getArticles(articleId).then((res) => {
      this.article = res.data;
      // console.log(this.article);
      if (this.article.userId == this.getUserId()) {
        this.modify = 1;
      }
    });
    // console.log(this.article.id);
    if (this.$route.query.review) {
      // console.log("review");
      this.review = 1;
    }
    if (this.$route.query.modify) {
      this.modify = 1;
      // console.log(this.modify);
    }
    if (this.$route.query.admin) {
      this.admin = 1;
    }
  },
  name: "Addnotice",

  methods: {
    //获得用户id
    getUserId() {
      return this.$store.state.user.id;
    },
    //修改
    handleModify() {
      const modifyID = this.article.id;
      this.$router.push({
        path: "./modify",
        query: { articleId: this.article.id },
      });
    },
    //删除
    handleDelete() {
      const deleteID = this.article.id;
      this.$modal
        .confirm("是否确认删除？")
        .then(function () {
          return delArticles(deleteID);
        })
        .then(() => {
          this.$modal.msgSuccess("删除成功");
          if (this.admin == 1) {
            this.$router.push({
              path: "./articles_admin",
            });
          } else {
            this.$router.push({
              path: "./articles",
            });
          }
        });
    },
    //审核通过
    handleApprove() {
      this.article.auditStatus = 1;
      updateArticles(this.article).then((response) => {
        this.$notify({
          title: "提示",
          message: "审核完成",
          type: "success",
        });
        this.$router.push({
          path: "./review",
        });
      });
    }, //审核不通过
    handleDisapprove() {
      this.article.auditStatus = 2;
      updateArticles(this.article).then((response) => {
        this.$notify({
          title: "提示",
          message: "审核完成",
          type: "success",
        });
        this.$router.push({
          path: "./review",
        });
      });
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
