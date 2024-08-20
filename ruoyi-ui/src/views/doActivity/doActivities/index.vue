<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择活动状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_activity_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>

      <el-form-item label="活动" prop="field106" >
        <el-switch v-model="formData.field106" active-text="已参加" inactive-text="未参加"
        @click="doSwitch"></el-switch>
      </el-form-item>
    </el-form>
    
    


    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['activity:activities:add']"
        >新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['activity:activities:edit']"
        >修改</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['activity:activities:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['activity:activities:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activitiesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="45" align="center" />
      <!-- <el-table-column label="活动id" align="center" prop="id" /> -->
      <el-table-column label="活动名称" align="center" prop="name" width="100"/>
      <el-table-column label="活动时间" align="center" prop="date" >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="时长" align="center" prop="time" width="80"/>
      <el-table-column label="发起人" align="center" prop="organizer" />
      <!-- <el-table-column label="活动内容" align="center" prop="content" /> -->
      <!-- <el-table-column label="活动人数" align="center" prop="max" />
       <el-table-column label="活动地点" align="center" prop="address" />
      <el-table-column label="已报名人数" align="center" prop="account" /> -->

      <el-table-column label="已报名人数/上限人数" align="center" >  
        <template slot-scope="scope">  
          {{ scope.row.account }}/{{ scope.row.max }}  
        </template>  
      </el-table-column>

      <el-table-column label="活动状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_activity_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
          v-if="!formData.field106"
            size="small"
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['doActivity:doActivities:edit']"
            :disabled="scope.row.status == 2"
          >参加</el-button>
          <el-button
          v-if="formData.field106"
            size="small"
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['doActivity:doActivities:edit']"
            :disabled="scope.row.status == 2"
          >详情</el-button>
<!-- 权限相关，重要 -->

          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['activity:activities:edit']"
          >修改</el-button> -->
          <el-button
          v-if="formData.field106"
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['activity:activities:remove']"
            :disabled="scope.row.status == 2"
          >退选</el-button>
          <!-- 2才是已结束 -->
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

    <!-- 添加或修改新建活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入活动名称" disabled/>
        </el-form-item>
        <el-form-item label="活动时间" prop="date">
          <el-date-picker clearable
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择活动时间" disabled>
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动时长" prop="time">
          <el-input v-model="form.time" placeholder="请输入活动时长" disabled/>
        </el-form-item>
        <el-form-item label="活动发起人" prop="organizer">
          <el-input v-model="form.organizer" placeholder="请输入活动发起人" disabled/>
        </el-form-item>
        <el-form-item label="活动内容">
          <!-- <editor v-model="form.content" :min-height="192"/> -->
          <el-input v-model="form.content" type="textarea" placeholder="请输入内容" disabled/>
        </el-form-item>
        <el-form-item label="活动地点" prop="address">
          <el-input v-model="form.address" placeholder="请输入活动地点" disabled/>
        </el-form-item>
        <el-form-item label="活动人数" prop="max">
          <el-input v-model="form.max" placeholder="请输入活动人数" disabled/>
        </el-form-item>
        <el-form-item label="已报名人数" prop="account">
          <el-input v-model="form.account" placeholder="请输入已报名人数" disabled/>
        </el-form-item>
        <el-form-item label="活动状态" prop="status">
          <el-radio-group v-model="form.status" disabled>
            <el-radio
              v-for="dict in dict.type.sys_activity_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span v-if="form.account==form.max" class="small-hint" style="font-size: 15px; color: crimson;">*人数已满</span>
      <div slot="footer" class="dialog-footer"> 
        <el-button v-if="!formData.field106" type="primary" @click="submitForm" :disabled="form.account==form.max">确 定</el-button>
        <el-button v-if="!formData.field106" @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listActivities, getActivities, addActivities, updateActivities } from "@/api/activity/activities";
import { joinActivities,listSelected,listNoSelect,delActivities } from "@/api/doActivity/doActivities";

export default {
  name: "Activities",
  dicts: ['sys_activity_status'],
  watch: {  
    // 观察formData.field106的变化  
    'formData.field106'(newVal, oldVal) {  
      // 当field106的值变化时，执行这里的函数  
      this.doSwitch();
    }  
  },
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
      // 新建活动表格数据
      activitiesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      formData: {
        field106: false,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        date: [
          { required: true, message: "活动时间不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "活动时长不能为空", trigger: "blur" }
        ],
        max: [
          { required: true, message: "活动人数不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

    doSwitch(){
      console.log("field106",this.formData.field106);
      this.getList();
    },

    /** 查询新建活动列表 */
    getList() {
      this.loading = true;
      // listActivities(this.queryParams).then(response => {
      //   this.activitiesList = response.rows;
      //   this.total = response.total;
      //   this.loading = false;
      // });

      if(this.formData.field106==null) {listActivities(this.queryParams).then(response => {
        this.activitiesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      console.log("this.formData.field106==null");
    return;}
    const newForm={
      qf:this.queryParams,
      user_id:this.$store.state.user.id,
    };
      if(this.formData.field106==false) listNoSelect(newForm).then(response => {
        this.activitiesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
      else listSelected(newForm).then(response => {
        this.activitiesList = response.rows;
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
        name: null,
        date: null,
        time: null,
        organizer: null,
        content: null,
        max: null,
        address: null,
        account: null,
        status: null
      };
      this.formData.field106=false;
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加新建活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log(row.status);
      //this.reset();
      const id = row.id || this.ids
      getActivities(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "参加活动";
      });
      
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            // updateActivities(this.form).then(response => {
            //   this.$modal.msgSuccess("修改成功");
            //   this.open = false;
            //   this.getList();
            // });
            console.log({
                form:this.form,
                user_id:this.$store.state.user.id,
                activity_id:this.form.id,
              })
            joinActivities(
              {
                form:this.form,
                user_id:this.$store.state.user.id,
                activity_id:this.form.id,
              }
            ).then(response => {
              this.$modal.msgSuccess("报名成功");
              this.open = false;
              this.getList();
            });
          } else {
            addActivities(this.form).then(response => {
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
      const newObj={
        activity_id:row.id,
        uid:this.$store.state.user.id,
      };
      console.log("newObj",newObj);
      console.log("row",row);
      this.$modal.confirm('是否确认删除新建活动编号为"' + ids + '"的数据项？').then(function() {
        return delActivities(newObj);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('activity/activities/export', {
        ...this.queryParams
      }, `activities_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
