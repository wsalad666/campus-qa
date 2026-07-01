<template>
  <div class="form-page">
    <el-card style="max-width:400px;margin:60px auto">
      <template #header><h2 style="text-align:center">登录</h2></template>
      <el-form :model="form" label-width="80px">
        <el-form-item label="学号"><el-input v-model="form.studentId" /></el-form-item>
        <el-form-item label="密码"><el-input v-model="form.password" type="password" /></el-form-item>
        <el-form-item><el-button type="primary" @click="doLogin" style="width:100%">登录</el-button></el-form-item>
        <div style="text-align:center"><router-link to="/register">没有账号？去注册</router-link></div>
      </el-form>
    </el-card>
  </div>
</template>
<script>
import { login } from '../api/user'
export default {
  data() { return { form: { studentId: '', password: '' } } },
  methods: {
    async doLogin() {
      try {
        const res = await login(this.form.studentId, this.form.password)
        const d = res.data.data
        localStorage.setItem('token', d.token)
        localStorage.setItem('username', d.user.username)
        localStorage.setItem('userId', d.user.id)
        this.$router.push('/')
        location.reload()
      } catch(e) { this.$message.error('登录失败') }
    }
  }
}
</script>
<style scoped>.form-page { min-height: 60vh; }</style>
