<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="内推人姓名" prop="recName">
        <el-input
          v-model="queryParams.recName"
          placeholder="请输入内推人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布人姓名" prop="pubName">
        <el-input
          v-model="queryParams.pubName"
          placeholder="请输入发布人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内推时间" prop="recTime">
        <el-date-picker clearable
          v-model="queryParams.recTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择内推时间">
        </el-date-picker>
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
        <el-form-item label="内推人姓名" prop="recName">
          <el-input
            v-model="queryParams.recName"
            placeholder="请输入内推人姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="发布人姓名" prop="pubName">
          <el-input
            v-model="queryParams.pubName"
            placeholder="请输入发布人姓名"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
      </el-col>

      <el-col :span="6">
        <el-form-item label="内推时间" prop="recTime">
          <el-date-picker 
            v-model="queryParams.recTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择内推时间"
            clearable
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['recommendationItem:recommendationItem:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['recommendationItem:recommendationItem:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['recommendationItem:recommendationItem:remove']"
        >删除</el-button> -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['recommendationItem:recommendationItem:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recommendationItemList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="内推人姓名" align="center" prop="recName" />
      <el-table-column label="发布人姓名" align="center" prop="pubName" />
      <el-table-column label="内推人情况简介" align="center" prop="recCondition" />
      <el-table-column label="内推人简历pdf文件" align="center" prop="recResume" />
      <el-table-column label="内推时间" align="center" prop="recTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="内推意向" align="center" prop="recExpect" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['recommendationItem:recommendationItem:edit']"
          >修改</el-button>
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['recommendationItem:recommendationItem:remove']"
          >删除</el-button> -->
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

    <!-- 添加或修改内推发布对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内推人姓名" prop="recName">
          <el-input v-model="form.recName" placeholder="请输入内推人姓名" />
        </el-form-item>
        <!-- 增加的遍历功能 -->
        <el-form-item label="发布人姓名" prop="pubName">
          <el-input v-model="form.pubName" placeholder="  "  readonly/>
        </el-form-item>
        <el-form-item label="内推人情况简介" prop="recCondition">
          <el-input v-model="form.recCondition" placeholder="请输入内推人情况简介" />
        </el-form-item>
        <el-form-item label="内推人简历pdf文件" prop="recResume">
          <file-upload v-model="form.recResume"/>
        </el-form-item>
        <el-form-item label="内推时间" prop="recTime">
          <el-date-picker clearable
            v-model="form.recTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择内推时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="内推意向" prop="recExpect">
          <el-input v-model="form.recExpect" placeholder="请输入内推意向" />
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
import { listRecommendationItem, getRecommendationItem, delRecommendationItem, addRecommendationItem, updateRecommendationItem } from "@/api/recommendationItem/recommendationItem";
import { getUserProfile } from "@/api/system/user";

export default {
  // name: "Profile",
  // data(){
  //   return{
  //       form:{
  //         pubName: '', //用于绑定到输入框
  //       },
  //       user: {},
  //       roleGroup: {},
  //       postGroup: {},
  //       activeTab: "userinfo"
  //   };
  // },
  // created() {
  //   this.getUser();
  // },
  // methods: {
  //   getUser() {
  //     getUserProfile()
  //       .then(response => {
  //         this.user = response.data;  // 获取用户信息
  //         this.roleGroup = response.data.roleGroup;
  //         this.postGroup = response.data.postGroup;
  //         this.form.pubName = this.user.name || '';  // 将姓名填入表单中的输入框
  //       })
  //       .catch(error => {
  //         console.error('获取用户数据失败:', error);
  //       });
  //   }
  // },
  
  name : "CombinedComponent",
  //name: "RecommendationItem",
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
      // 内推发布表格数据
      recommendationItemList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recName: null,
        pubName: null,
        recCondition: null,
        recResume: null,
        recTime: null,
        recExpect: null
      },
      // 表单参数
      form: {
        recName:'',
        pubName:'',
        recCondition:'',
        recResume:'',
        recTime:'',
        recExpect:''
      },
      // 表单校验
      rules: {
        recName: [
          { required: true, message: "内推人姓名不能为空", trigger: "blur" }
        ],
        // pubName: [
        //   { required: true, message: "发布人姓名不能为空", trigger: "blur" }
        // ],
        recCondition: [
          { required: true, message: "内推人情况简介不能为空", trigger: "blur" }
        ],
        recResume: [
          { required: true, message: "内推人简历pdf文件不能为空", trigger: "blur" }
        ],
        recTime: [
          { required: true, message: "内推时间不能为空", trigger: "blur" }
        ],
        recExpect: [
          { required: true, message: "内推意向不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getUser();
  },
  methods: {
    /**新增-姓名自动填入输入框*/
    getUser(){
      getUserProfile()
        .then(response => {
          if (response.data) {
            this.user = response.data;
            this.$set(this.form,'pubName',response.data.userName || '');
            // this.form.pubName = response.data.userName || '';  // 将姓名填入表单中的输入框
            console.log('获取的用户名:', response.data.userName); // 打印获取到的用户名
            console.log('赋值给 form.pubName:', this.form.pubName); // 打印赋值后的 pubName
          } else {
            console.error('获取用户数据失败: 响应数据为空');
          }
          //this.form.pubName = response.data|| '';  // 将姓名填入表单中的输入框
        })
        .catch(error => {
          console.error('获取用户数据失败:', error);
        });
    },
    /** 查询内推发布列表 */
    getList() {
      this.loading = true;
      listRecommendationItem(this.queryParams).then(response => {
        this.recommendationItemList = response.rows;
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
      const pubName = this.form.pubName; // 保存当前的 pubName 值
      this.form = {
        recName: null,
        pubName: pubName,
        recCondition: null,
        recResume: null,
        recTime: null,
        recExpect: null
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
      this.ids = selection.map(item => item.recName)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加内推发布";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recName = row.recName;
      const pubName = row.pubName;
      getRecommendationItem(recName,pubName).then(response => {
        if(response.data.pubName === this.user.userName){
          this.form = response.data;
          this.open = true;
          this.title = "修改内推发布";
        }else{
          this.$message.error('您无权修改此条目');
        }
        
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recName != null) {
            updateRecommendationItem(this.form).then(response => {
              this.$modal.msgSuccess("操作成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecommendationItem(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    // handleDelete(row) {
    //   const recNames = row.recName || this.ids;
    //   this.$modal.confirm('是否确认删除内推内推姓名为"' + recNames + '"的数据项？').then(function() {
    //     return delRecommendationItem(recNames);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {});
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download('recommendationItem/recommendationItem/export', {
        ...this.queryParams
      }, `recommendationItem_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
