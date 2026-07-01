<template>
  <el-card>
    <template #header>课程分类</template>
    <el-row :gutter="20">
      <el-col v-for="c in courses" :key="c.id" :span="6">
        <el-card shadow="hover" style="margin-bottom:16px;cursor:pointer" @click="$router.push({path:'/questions',query:{courseId:c.id}})">
          <h3>{{ c.name }}</h3>
          <p style="color:#999">{{ c.description }}</p>
        </el-card>
      </el-col>
    </el-row>
  </el-card>
</template>
<script>
import { getCourses } from '../api/resource'
export default {
  data() { return { courses: [] } },
  async mounted() { this.courses = (await getCourses()).data.data || [] }
}
</script>
