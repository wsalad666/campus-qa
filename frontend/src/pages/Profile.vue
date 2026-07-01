<template>
  <div class="profile">
    <el-card v-if="user">
      <div class="profile-header">
        <el-avatar :size="80">{{ user.username?.[0] }}</el-avatar>
        <div class="profile-info">
          <h2>{{ user.username }}</h2>
          <p>{{ user.bio || '这个人很懒，什么都没写' }}</p>
          <p>关注 {{ stats.followingCount || 0 }} | 粉丝 {{ stats.followerCount || 0 }}</p>
        </div>
        <el-button type="primary" @click="$router.push('/profile/edit')">编辑资料</el-button>
      </div>
    </el-card>
    <el-tabs style="margin-top:20px">
      <el-tab-pane label="我的问题">
        <div v-for="q in myQuestions" :key="q.id" class="q-item" @click="$router.push('/questions/'+q.id)">{{ q.title }}</div>
      </el-tab-pane>
      <el-tab-pane label="我的收藏">
        <div v-for="f in favorites" :key="f.id" class="q-item">{{ f.questionTitle || f.resourceId }}</div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import { getProfile, myQuestions, getFavorites } from '../api/user'
export default {
  data() { return { user: null, stats: {}, myQuestions: [], favorites: [] } },
  async mounted() {
    const uid = localStorage.getItem('userId')
    if (!uid) { this.$router.push('/login'); return }
    try {
      const res = await getProfile(uid)
      this.user = res.data.data
      const [qRes, fRes] = await Promise.all([myQuestions(uid), getFavorites(uid)])
      this.myQuestions = qRes.data.data || []
      this.favorites = fRes.data.data || []
    } catch(e) {}
  }
}
</script>
<style scoped>
.profile-header { display: flex; align-items: center; gap: 20px; }
.profile-info { flex: 1; }
.q-item { padding: 10px; border-bottom: 1px solid #f0f0f0; cursor: pointer; }
</style>
