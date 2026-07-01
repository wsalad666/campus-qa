<template>
  <div class="form-page">
    <el-card style="max-width:400px;margin:60px auto">
      <template #header><h2 style="text-align:center">注册</h2></template>
      <el-form :model="form" label-width="80px">
        <el-form-item label="学号"><el-input v-model="form.studentId" /></el-form-item>
        <el-form-item label="昵称"><el-input v-model="form.username" /></el-form-item>
        <el-form-item label="密码"><el-input v-model="form.password" type="password" /></el-form-item>
        <el-form-item><el-button type="primary" @click="doRegister" style="width:100%">注册</el-button></el-form-item>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { register } from '../api/user'
export default {
  data() { return { form: { studentId: '', username: '', password: '' } } },
  methods: {
    async doRegister() {
      try {
        await register(this.form)
        this.$message.success('注册成功')
        this.$router.push('/login')
      } catch(e) { this.$message.error('注册失败') }
    }
  }
}
</script>
