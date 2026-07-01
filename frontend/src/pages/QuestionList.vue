<template>
  <div>
    <div class="toolbar">
      <el-select v-model="courseId" placeholder="课程筛选" clearable @change="load"><el-option v-for="c in courses" :key="c.id" :label="c.name" :value="c.id" /></el-select>
      <el-input v-model="keyword" placeholder="搜索..." style="width:250px" @keyup.enter="load" />
      <el-button type="primary" @click="$router.push('/questions/new')">发布问题</el-button>
    </div>
    <el-card v-for="q in list" :key="q.id" style="margin-bottom:12px;cursor:pointer" @click="$router.push('/questions/'+q.id)">
      <div class="q-title">{{ q.title }}</div>
      <div class="q-meta">{{ q.username }} · {{ q.courseName }} · {{ q.answerCount || 0 }}回答 · {{ q.likeCount || 0 }}赞</div>
    </el-card>
    <el-pagination v-if="total>0" layout="prev,pager,next" :total="total" :page-size="10" @current-change="pageChange" style="justify-content:center" />
  </div>
</template>
<script>
import { getQuestions } from '../api/question'
import { getCourses } from '../api/resource'
export default {
  data() { return { list: [], total: 0, courses: [], courseId: null, keyword: '', page: 1 } },
  async mounted() {
    this.keyword = this.$route.query.keyword || ''
    this.courseId = this.$route.query.courseId || null
    const cRes = await getCourses()
    this.courses = cRes.data.data || []
    this.load()
  },
  methods: {
    async load() {
      const res = await getQuestions({ courseId: this.courseId, keyword: this.keyword, page: this.page, size: 10 })
      this.list = res.data.data?.list || []
      this.total = res.data.data?.total || 0
    },
    pageChange(p) { this.page = p; this.load() }
  }
}
</script>
<style scoped>
.toolbar { display: flex; gap: 10px; margin-bottom: 20px; }
.q-title { font-size: 16px; font-weight: bold; }
.q-meta { color: #999; font-size: 12px; margin-top: 8px; }
</style>
