<template>
  <el-card style="max-width:500px;margin:40px auto">
    <template #header>修改资料</template>
    <el-form :model="form" label-width="80px">
      <el-form-item label="昵称"><el-input v-model="form.username" /></el-form-item>
      <el-form-item label="头像URL"><el-input v-model="form.avatar" /></el-form-item>
      <el-form-item label="简介"><el-input v-model="form.bio" type="textarea" /></el-form-item>
      <el-form-item><el-button type="primary" @click="save">保存</el-button></el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { updateProfile } from '../api/user'
export default {
  data() { return { form: { username: '', avatar: '', bio: '' } } },
  methods: {
    async save() {
      const uid = localStorage.getItem('userId')
      await updateProfile(uid, this.form)
      localStorage.setItem('username', this.form.username)
      this.$message.success('保存成功')
      this.$router.push('/profile')
    }
  }
}
</script>
