<template>
  <div id="app-layout">
    <el-container>
      <el-header class="app-header">
        <div class="logo" @click="$router.push('/')">校园互助学习平台</div>
        <div class="header-right">
          <el-input v-model="searchKeyword" placeholder="搜索问题..." size="small" style="width:200px" @keyup.enter="search" />
          <el-button v-if="!token" type="primary" size="small" @click="$router.push('/login')">登录</el-button>
          <el-button v-if="!token" size="small" @click="$router.push('/register')">注册</el-button>
          <el-dropdown v-if="token">
            <span class="user-dropdown">{{ username }} <el-icon><ArrowDown /></el-icon></span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="$router.push('/profile')">个人中心</el-dropdown-item>
                <el-dropdown-item @click="$router.push('/resources')">资源列表</el-dropdown-item>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-main><router-view /></el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() { return { searchKeyword: '' } },
  computed: {
    token() { return localStorage.getItem('token') },
    username() { return localStorage.getItem('username') || '用户' }
  },
  methods: {
    search() {
      if (this.searchKeyword) this.$router.push({ path: '/questions', query: { keyword: this.searchKeyword } })
    },
    logout() {
      localStorage.removeItem('token')
      localStorage.removeItem('username')
      this.$router.push('/')
      location.reload()
    }
  }
}
</script>

<style>
.app-header {
  display: flex; align-items: center; justify-content: space-between;
  background: #fff; border-bottom: 1px solid #eee; padding: 0 20px; height: 56px;
}
.logo { font-size: 18px; font-weight: bold; color: var(--primary); cursor: pointer; }
.header-right { display: flex; align-items: center; gap: 10px; }
.user-dropdown { cursor: pointer; }
</style>
