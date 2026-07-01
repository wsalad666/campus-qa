<template>
  <div class="admin">
    <div v-if="!adminToken">
      <el-card style="max-width:400px;margin:60px auto">
        <template #header>管理员登录</template>
        <el-form>
          <el-form-item label="账号"><el-input v-model="loginForm.username" /></el-form-item>
          <el-form-item label="密码"><el-input v-model="loginForm.password" type="password" /></el-form-item>
          <el-form-item><el-button type="primary" @click="doAdminLogin">登录</el-button></el-form-item>
        </el-form>
      </el-card>
    </div>

    <div v-else>
      <el-row :gutter="20" style="margin-bottom:20px">
        <el-col :span="6"><el-statistic title="总问题数" :value="stats.questionCount||0" /></el-col>
        <el-col :span="6"><el-statistic title="总资源数" :value="stats.resourceCount||0" /></el-col>
        <el-col :span="6"><el-statistic title="今日新增" :value="stats.todayQuestions||0" /></el-col>
      </el-row>

      <el-card style="margin-bottom:20px"><template #header>课程分类管理</template>
        <el-form :inline="true"><el-form-item><el-input v-model="newCourse.name" placeholder="课程名" /></el-form-item><el-form-item><el-input v-model="newCourse.description" placeholder="描述" /></el-form-item><el-form-item><el-button type="primary" @click="addCourse">新增</el-button></el-form-item></el-form>
        <el-table :data="courses"><el-table-column prop="name" label="名称" /><el-table-column prop="description" label="描述" /><el-table-column label="操作" width="120"><template #default="s"><el-button size="small" type="danger" @click="delCourse(s.row.id)">删除</el-button></template></el-table-column></el-table>
      </el-card>

      <el-card><template #header>违规问答管理</template>
        <el-table :data="violations"><el-table-column prop="title" label="标题" /><el-table-column prop="username" label="发布者" /><el-table-column label="操作" width="120"><template #default="s"><el-button size="small" type="danger" @click="doViolation(s.row.id)">下架</el-button></template></el-table-column></el-table>
      </el-card>
    </div>
  </div>
</template>
<script>
import { adminLogin, getCourses, addCourse, deleteCourse, homeStats, violationQuestion } from '../../api/resource'
import { getQuestions } from '../../api/question'
export default {
  data() { return { adminToken: '', loginForm: { username:'admin',password:'admin123' }, newCourse: { name:'',description:'' }, courses: [], stats: {}, violations: [] } },
  async mounted() { this.adminToken = localStorage.getItem('adminToken') || ''; if (this.adminToken) await this.loadAll() },
  methods: {
    async doAdminLogin() {
      const res = await adminLogin(this.loginForm.username, this.loginForm.password)
      this.adminToken = res.data.data.token
      localStorage.setItem('adminToken', this.adminToken)
      await this.loadAll()
    },
    async loadAll() {
      const [c,s,q] = await Promise.all([getCourses(), homeStats(), getQuestions({page:1,size:100})])
      this.courses = c.data.data || []
      this.stats = s.data.data || {}
      this.violations = (q.data.data?.list||[]).filter(v=>v.status===0)
    },
    async addCourse() { await addCourse(this.newCourse); this.newCourse={name:'',description:''}; await this.loadAll() },
    async delCourse(id) { await deleteCourse(id); await this.loadAll() },
    async doViolation(id) { await violationQuestion(id); await this.loadAll(); this.$message.success('已下架') }
  }
}
</script>
