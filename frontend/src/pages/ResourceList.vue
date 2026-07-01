<template>
  <div>
    <div class="toolbar">
      <el-select v-model="courseId" placeholder="课程筛选" clearable @change="load"><el-option v-for="c in courses" :key="c.id" :label="c.name" :value="c.id" /></el-select>
      <el-button type="primary" @click="$router.push('/resources/upload')">上传资源</el-button>
    </div>
    <el-table :data="list" stripe>
      <el-table-column prop="title" label="名称" />
      <el-table-column prop="courseName" label="课程" width="120" />
      <el-table-column label="大小" width="100"><template #default="s">{{ (s.row.fileSize/1024).toFixed(0) }} KB</template></el-table-column>
      <el-table-column prop="downloadCount" label="下载" width="80" />
      <el-table-column label="操作" width="120"><template #default="s"><el-button size="small" @click="del(s.row)">删除</el-button></template></el-table-column>
    </el-table>
  </div>
</template>
<script>
import { getResources, deleteResource, getCourses } from '../api/resource'
export default {
  data() { return { list: [], courses: [], courseId: null } },
  async mounted() {
    this.courses = (await getCourses()).data.data || []
    this.load()
  },
  methods: {
    async load() { this.list = (await getResources({ courseId: this.courseId, page:1, size:50 })).data.data?.list || [] },
    async del(row) {
      await deleteResource(row.id, localStorage.getItem('userId'))
      this.load()
    }
  }
}
</script>
<style scoped>.toolbar { display: flex; gap: 10px; margin-bottom: 20px; }</style>
