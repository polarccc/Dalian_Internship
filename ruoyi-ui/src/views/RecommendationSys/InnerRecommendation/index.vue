<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="内推ID" prop="recLogId">
        <el-input
          v-model="queryParams.recLogId"
          placeholder="请输入内推ID"
          clearable
          @keyup.enter.native="handleQuery"
        />

        <el-form-item label="批准状态">
          <el-select
            v-model="queryParams.recCondition"
            placeholder="请选择批准状态"
            clearable
            @keyup.enter.native="handleQuery"
          >
            <el-option
              v-for="item in recConditionOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
  
      </el-form-item>
      <el-form-item label="内推人姓名" prop="recName">
        <el-input
          v-model="queryParams.recName"
          placeholder="请输入内推人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->
    <el-form 
    :model="queryParams" 
    ref="queryForm" 
    size="small" 
    :inline="true" 
    v-show="showSearch" 
    label-width="88px"
    class="query-form"
  >
    <el-row :gutter="20">
      <el-col :span="6">
        <el-form-item label="内推ID" prop="recLogId">
          <el-input
            v-model="queryParams.recLogId"
            placeholder="请输入内推ID"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="批准状态" prop="recCondition">
          <el-select
            v-model="queryParams.recCondition"
            placeholder="请选择批准状态"
            clearable
            @keyup.enter.native="handleQuery"
          >
            <el-option
              v-for="item in recConditionOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="内推人姓名" prop="recName">
          <el-input
            v-model="queryParams.recName"
            placeholder="请输入内推人姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
      </el-col>

      <el-col :span="6" class="form-actions">
        <el-form-item>
          <el-button 
            type="primary" 
            icon="el-icon-search" 
            size="mini" 
            @click="handleQuery"
          >
            搜索
          </el-button>
          <el-button 
            icon="el-icon-refresh" 
            size="mini" 
            @click="resetQuery"
          >
            重置
          </el-button>
        </el-form-item>
      </el-col>
    </el-row>
  </el-form>
    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['RecommendationSys:InnerRecommendation:add']"
        >新增</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['RecommendationSys:InnerRecommendation:edit']"
        >管理员审核</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['RecommendationSys:InnerRecommendation:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['RecommendationSys:InnerRecommendation:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="InnerRecommendationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="内推ID" align="center" prop="recLogId" />
      <el-table-column label="内推人姓名" align="center" prop="recName" />
      <el-table-column label="发布人姓名" align="center" prop="pubName" />

      <el-table-column label="内推人情况" align="center" prop="candidateCondition" />
      <el-table-column label="内推结果" align="center" prop="recResult" />
      <el-table-column label="内推审核状态" align="center" prop="recCondition" />
      <el-table-column label="审核意见" align="center" prop="recTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['RecommendationSys:InnerRecommendation:edit']"
          >审核</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['RecommendationSys:InnerRecommendation:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改内推历史记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内推人姓名" prop="recName">
          <el-input v-model="form.recName" placeholder="请输入内推人姓名" readonly />
        </el-form-item>
        <el-form-item label="发布人姓名" prop="pubName">
          <el-input v-model="form.pubName" placeholder="请输入发布人姓名" readonly/>
        </el-form-item>
        <el-form-item label="内推人情况" prop="candidateCondition">
          <el-input v-model="form.candidateCondition" type="textarea" placeholder="请输入内容" readonly/>
        </el-form-item>
        <!-- 阅读简历pdf文件 -->
        <!-- <el-form-item label="内推人简历pdf文件" prop="recResume">
          <file-upload v-model="form.recResume" :disabled="isReadOnly"/>
        </el-form-item> -->
        <el-form-item label="内推人简历pdf文件" prop="recResume">
          <template v-if="form.recResume">
            <div>
              <el-button type="primary" @click="openResume">查看简历</el-button>
            </div>
          </template>
          <template v-else>
            <div>
              <el-button plain disabled style="background-color: #f0f0f0; border-color: #dcdcdc; color: #a0a0a0;">暂无文件</el-button>
            </div>
          </template>
        </el-form-item>
  
        <el-form-item label="内推结果" prop="recResult">
          <el-input v-model="form.recResult" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="内推审核状态" prop="recCondition">
          <el-select v-model="form.recCondition" placeholder="请选择状态" >
            <el-option
              v-for="item in statusOptions"
              :key="item.value"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核意见" prop="recTime">
          <el-input v-model="form.recTime" type="textarea" placeholder="请输入审核意见" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInnerRecommendation, getInnerRecommendation, delInnerRecommendation, addInnerRecommendation, updateInnerRecommendation } from "@/api/RecommendationSys/InnerRecommendation";
import { getUserProfile } from "@/api/system/user";

export default {
  name: "InnerRecommendation",
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
      // 内推历史记录表格数据
      InnerRecommendationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recLogId: null,
        recName: null,
      },
      // 表单参数
      form: {
        recondition:'',
        recCondition: ''
      },
      statusOptions:[
        { value: '待审核', label: '待审核' },
        { value: '已通过', label: '已通过' },
        { value: '未通过', label: '未通过' }
      ],
      recConditionOptions: [
        { value: '待审核', label: '待审核' },
        { value: '已通过', label: '已通过' },
        { value: '未通过', label: '未通过' }
      ],
      // 表单校验
      rules: {
        // recName: [
        //   { required: true, message: "内推人姓名不能为空", trigger: "blur" }
        // ],
        // pubName: [
        //   { required: true, message: "发布人姓名不能为空", trigger: "blur"}
        // ],
        // candidateCondition: [
        //   { required: true, message: "内推人情况不能为空", trigger: "blur" }
        // ],
        // recResult: [
        //   { required: true, message: "内推结果不能为空", trigger: "blur" }
        // ],
        recTime:[{
          required: true, message: "审核意见不能为空", trigger: "blur"
        }]
      }
    };
  },
  created() {
    this.getList();
    this.getUser();
  },
  methods: {
    getUser(){
      getUserProfile()
        .then(response => {
          if (response.data) {
            this.user = response.roleGroup;
            console.log('获取的用户名:', response.roleGroup); // 打印获取到的用户名
            //this.getList();
          } else {
            console.error('获取用户数据失败: 响应数据为空');
          }
        })
        .catch(error => {
          console.error('获取用户数据失败:', error);
        });
    },
    getFullPath(filePath) {
      const serverUrl = 'http://localhost/dev-api'; // 确保这是你的服务器地址
      return `${serverUrl}${filePath}`;
    },
    openResume() {
    window.open(this.getFullPath(this.form.recResume), '_blank');
  },
    /** 查询内推历史记录列表 */
    getList() {
      this.loading = true;
      listInnerRecommendation(this.queryParams).then(response => {
        this.InnerRecommendationList = response.rows;
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
        recLogId: null,
        recName: null,
        pubName: null,
        candidateCondition: null,
        recResult: null,
        recCondition: null,
        recTime: null,
        recResume: null,
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
      this.ids = selection.map(item => item.recLogId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加内推历史记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recLogId = row.recLogId || this.ids
      getInnerRecommendation(recLogId).then(response => {

        if(this.user === "超级管理员"){
          this.form = response.data;
          this.open = true;
          this.title = "审核内推";
        }else{
          this.$message.error('您只能浏览而无权审核');
        }
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recLogId != null) {
            updateInnerRecommendation(this.form).then(response => {
              this.$modal.msgSuccess("审核成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInnerRecommendation(this.form).then(response => {
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
      const recLogIds = row.recLogId || this.ids;
      this.$modal.confirm('是否确认删除内推历史记录编号为"' + recLogIds + '"的数据项？').then(function() {
        return delInnerRecommendation(recLogIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('RecommendationSys/InnerRecommendation/export', {
        ...this.queryParams
      }, `InnerRecommendation_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
