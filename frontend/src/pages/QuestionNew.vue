<template>
  <el-card style="max-width:700px;margin:20px auto">
    <template #header>发布问题</template>
    <el-form :model="form" label-width="80px">
      <el-form-item label="标题"><el-input v-model="form.title" /></el-form-item>
      <el-form-item label="课程"><el-select v-model="form.courseId" placeholder="选择课程"><el-option v-for="c in courses" :key="c.id" :label="c.name" :value="c.id" /></el-select></el-form-item>
      <el-form-item label="内容"><el-input v-model="form.content" type="textarea" :rows="6" /></el-form-item>
      <el-form-item><el-button type="primary" @click="publish">发布</el-button></el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { publishQuestion } from '../api/question'
import { getCourses } from '../api/resource'
export default {
  data() { return { form: { title:'', courseId:null, content:'', userId:null }, courses: [] } },
  async mounted() {
    this.courses = (await getCourses()).data.data || []
    this.form.userId = Number(localStorage.getItem('userId'))
  },
  methods: {
    async publish() {
      await publishQuestion(this.form)
      this.$message.success('发布成功')
      this.$router.push('/questions')
    }
  }
}
</script>
