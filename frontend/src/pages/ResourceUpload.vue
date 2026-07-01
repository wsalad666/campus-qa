<template>
  <el-card style="max-width:500px;margin:40px auto">
    <template #header>上传资源</template>
    <el-form label-width="80px">
      <el-form-item label="标题"><el-input v-model="title" /></el-form-item>
      <el-form-item label="课程"><el-select v-model="courseId"><el-option v-for="c in courses" :key="c.id" :label="c.name" :value="c.id" /></el-select></el-form-item>
      <el-form-item label="文件"><el-upload :auto-upload="false" :on-change="onFile" :limit="1"><el-button>选择文件</el-button></el-upload></el-form-item>
      <el-form-item><el-button type="primary" @click="upload">上传</el-button></el-form-item>
    </el-form>
  </el-card>
</template>
<script>
import { uploadResource, getCourses } from '../api/resource'
export default {
  data() { return { title:'', courseId:null, file:null, courses:[] } },
  async mounted() { this.courses = (await getCourses()).data.data || [] },
  methods: {
    onFile(f) { this.file = f.raw },
    async upload() {
      const fd = new FormData()
      fd.append('file', this.file)
      fd.append('userId', localStorage.getItem('userId'))
      fd.append('courseId', this.courseId)
      fd.append('title', this.title)
      await uploadResource(fd)
      this.$message.success('上传成功')
      this.$router.push('/resources')
    }
  }
}
</script>
