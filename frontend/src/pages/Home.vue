<template>
  <div class="home">
    <div class="hero">
      <h1>校园互助学习平台</h1>
      <p>互助问答，资源共享，让学习更高效</p>
      <el-input v-model="keyword" placeholder="搜索问题或资源..." size="large" style="max-width:500px" @keyup.enter="$router.push({path:'/questions',query:{keyword}})" />
    </div>
    <el-row :gutter="20">
      <el-col :span="6"><el-card><template #header>课程分类</template><el-tag v-for="c in courses" :key="c.id" style="margin:4px;cursor:pointer" @click="$router.push({path:'/questions',query:{courseId:c.id}})">{{ c.name }}</el-tag></el-card></el-col>
      <el-col :span="12"><el-card><template #header>最新问题</template><div v-for="q in questions" :key="q.id" class="q-item" @click="$router.push('/questions/'+q.id)">{{ q.title }} <span style="color:#999;font-size:12px">{{ q.username }}</span></div></el-card></el-col>
      <el-col :span="6"><el-card><template #header>热门问题</template><div v-for="q in hotQuestions" :key="q.id" class="q-item" @click="$router.push('/questions/'+q.id)">{{ q.title }}</div></el-card></el-col>
    </el-row>
  </div>
</template>
<script>
import { getCourses } from '../api/resource'
import { getQuestions } from '../api/question'
export default {
  data() { return { keyword: '', courses: [], questions: [], hotQuestions: [] } },
  async mounted() {
    try {
      const [cRes, qRes] = await Promise.all([getCourses(), getQuestions({page:1,size:10})])
      this.courses = cRes.data.data || []
      this.questions = qRes.data.data?.list || []
    } catch(e) {}
  }
}
</script>
<style scoped>
.hero { text-align: center; padding: 40px 0; }
.hero h1 { font-size: 28px; color: var(--primary); }
.q-item { padding: 8px 0; border-bottom: 1px solid #f0f0f0; cursor: pointer; }
.q-item:hover { color: var(--primary); }
</style>
