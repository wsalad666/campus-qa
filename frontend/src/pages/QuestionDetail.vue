<template>
  <div class="detail" v-if="q">
    <el-card>
      <template #header>
        <h2>{{ q.title }} <el-tag v-if="q.isResolved" type="success">已解决</el-tag></h2>
        <div class="meta">{{ q.username }} · {{ q.courseName }} · {{ q.likeCount || 0 }}赞 · {{ q.answerCount || 0 }}回答</div>
      </template>
      <div class="content" v-html="q.content"></div>
      <el-button @click="doLike" :type="liked?'danger':'default'" size="small">{{ liked ? '取消赞' : '点赞' }}</el-button>
    </el-card>

    <el-card style="margin-top:20px"><template #header>回答 ({{ answers.length }})</template>
      <div v-for="a in answers" :key="a.id" class="answer">
        <div class="answer-meta">{{ a.username }} <el-tag v-if="a.isAccepted" type="success" size="small">已采纳</el-tag></div>
        <div v-html="a.content"></div>
        <el-button v-if="q.userId==userId&&!q.isResolved" size="small" @click="accept(a.id)">采纳</el-button>
        <div v-for="c in a.comments" :key="c.id" class="comment">{{ c.username }}: {{ c.content }}</div>
        <el-input v-model="commentText[a.id]" placeholder="写评论..." size="small" style="width:300px" @keyup.enter="doComment(a.id)" />
      </div>
    </el-card>

    <el-card style="margin-top:20px">
      <template #header>写回答</template>
      <el-input v-model="answerText" type="textarea" :rows="4" placeholder="输入你的回答..." />
      <el-button type="primary" @click="doAnswer" style="margin-top:10px">提交回答</el-button>
    </el-card>
  </div>
</template>
<script>
import { getQuestionDetail, answerQuestion, commentAnswer, acceptAnswer, likeQuestion, unlikeQuestion } from '../api/question'
export default {
  data() { return { q: null, answers: [], answerText: '', commentText: {}, liked: false, userId: null } },
  async mounted() {
    this.userId = Number(localStorage.getItem('userId'))
    await this.load()
  },
  methods: {
    async load() {
      const res = await getQuestionDetail(this.$route.params.id)
      this.q = res.data.data
    },
    async doAnswer() {
      await answerQuestion(this.q.id, { userId: this.userId, content: this.answerText })
      this.answerText = ''
      this.load()
    },
    async doComment(answerId) {
      await commentAnswer(answerId, { userId: this.userId, content: this.commentText[answerId] })
      this.commentText[answerId] = ''
      this.load()
    },
    async accept(answerId) {
      await acceptAnswer(answerId, this.userId)
      this.load()
    },
    async doLike() {
      if (this.liked) { await unlikeQuestion(this.q.id, this.userId) } else { await likeQuestion(this.q.id, this.userId) }
      this.liked = !this.liked
    }
  }
}
</script>
<style scoped>
.meta { color: #999; font-size: 13px; }
.answer { padding: 12px 0; border-bottom: 1px solid #f0f0f0; }
.answer-meta { font-weight: bold; margin-bottom: 4px; }
.comment { padding: 4px 20px; color: #666; font-size: 13px; }
</style>
