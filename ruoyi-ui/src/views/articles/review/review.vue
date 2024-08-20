<style>
.list-item {
  border-bottom: 2px solid #ededed;
  background: #fff;
}
.so-items-normal {
  padding: 16px 24px;
}
.so-items-normal .item-hd {
  line-height: 22px;
  margin-bottom: 1px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  width: 100%;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 1;
  position: relative;
}
.title {
  font-size: 21px;
  vertical-align: top;
}
.substr {
  display: inline-block;
  vertical-align: sub;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}
a.block-title:link {
  color: #222226;
}
.so-items-normal .item-bd .item-bd__cont {
  display: flex;
  align-items: flex-start;
  flex-shrink: 0;
  color: #555666;
  line-height: 24px;
  overflow-x: auto;
}
.so-items-normal .item-bd .bdc-lt {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  overflow-y: hidden;
}
items-normal .item-bd .bdc-lt p {
  color: #555666;
  word-break: break-all;
}
.row2 {
  display: inline-block;
  line-height: 24px;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
}
.so-items-normal .item-ft {
  margin-top: 8px;
}
.so-items-bottom {
  display: flex;
  height: 20px;
  justify-content: space-between;
  align-items: center;
  color: #555666;
  line-height: 22px;
}
.so-items-bottom .btm-lt[data-v-4f38bfda] {
  display: flex;
}
.so-items-bottom .btm-rt .user[data-v-4f38bfda] {
  position: relative;
  cursor: pointer;
  overflow: hidden;
  display: inline-block;
  text-overflow: ellipsis;
  white-space: nowrap;
  line-height: 20px;
  vertical-align: top;
  text-decoration: none;
  color: #555666;
}
.so-items-bottom .btm-rt .time[data-v-4f38bfda] {
  line-height: 22px;
  margin-left: 16px;
}
.row2 {
  -webkit-line-clamp: 3;
}
.block-title {
  font-weight: bold;
}
</style>

<template>
  <div class="app-container">
    <div class="list-item" v-for="article in articlesList" :key="article.id">
      <div v-if="article.auditStatus == 0">
        <div class="so-items-normal">
          <div class="item-hd">
            <h3 class="title substr">
              <!-- <a class="block-title so-item-report">{{ article.articleTitle }}</a> -->
              <router-link
                class="so-item-report block-title"
                :to="{
                  path: './details',
                  query: { articleId: article.id, review: 'review' },
                }"
                >{{ article.articleTitle }}</router-link
              >
            </h3>
          </div>
          <div class="item-bd">
            <div class="item-bd_cont">
              <div class="bdc-lt">
                <p class="row2">
                  <!-- <mavon-editor ref="md" v-model="article.articleContent" /> -->
                  {{ article.articleContent }}
                </p>
                <div class="item-ft">
                  <div class="so-items-bottom">
                    <div class="btm-lt">{{ article.autherName }}</div>
                    <div class="btm-rt">{{ article.createTime }}</div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->
  </div>
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
  name: "Articles",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 博文表格数据
      articlesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        userId: null,
        articleTitle: null,
        articleContent: null,
        auditStatus: null,
        isDeleted: null,
        createTime: null,
        updateTime: null,
        autherName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "作者id不能为空", trigger: "blur" },
        ],
        articleTitle: [
          { required: true, message: "文章标题不能为空", trigger: "blur" },
        ],
        articleContent: [
          { required: true, message: "文章内容不能为空", trigger: "blur" },
        ],
        auditStatus: [
          {
            required: true,
            message: "是否通过审核不能为空",
            trigger: "change",
          },
        ],
        isDeleted: [
          { required: true, message: "是否删除不能为空", trigger: "blur" },
        ],
        createTime: [
          { required: true, message: "文章发布时间不能为空", trigger: "blur" },
        ],
        updateTime: [
          { required: true, message: "文章更新时间不能为空", trigger: "blur" },
        ],
        autherName: [
          { required: true, message: "作者名称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    console.log(this.$store.state.user);
  },
  beforeRouteEnter(to, from, next) {
    next((vm) => {
      vm.$nextTick(() => {
        vm.getList();
      });
    });
  },
  methods: {
    /** 查询博文列表 */
    getList() {
      this.loading = true;
      listArticles(this.queryParams).then((response) => {
        this.articlesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        userId: null,
        articleTitle: null,
        articleContent: null,
        auditStatus: null,
        isDeleted: null,
        createTime: null,
        updateTime: null,
        autherName: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加博文";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getArticles(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改博文";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateArticles(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArticles(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm('是否确认删除博文编号为"' + ids + '"的数据项？')
        .then(function () {
          return delArticles(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/articles/export",
        {
          ...this.queryParams,
        },
        `articles_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
