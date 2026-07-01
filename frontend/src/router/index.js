import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  { path: '/', component: () => import('../pages/Home.vue'), meta: { title: '首页' } },
  { path: '/login', component: () => import('../pages/Login.vue'), meta: { title: '登录' } },
  { path: '/register', component: () => import('../pages/Register.vue'), meta: { title: '注册' } },
  { path: '/profile', component: () => import('../pages/Profile.vue'), meta: { title: '个人中心' } },
  { path: '/profile/edit', component: () => import('../pages/ProfileEdit.vue'), meta: { title: '修改资料' } },
  { path: '/questions', component: () => import('../pages/QuestionList.vue'), meta: { title: '问题列表' } },
  { path: '/questions/new', component: () => import('../pages/QuestionNew.vue'), meta: { title: '发布问题' } },
  { path: '/questions/:id', component: () => import('../pages/QuestionDetail.vue'), meta: { title: '问题详情' } },
  { path: '/resources', component: () => import('../pages/ResourceList.vue'), meta: { title: '资源列表' } },
  { path: '/resources/upload', component: () => import('../pages/ResourceUpload.vue'), meta: { title: '上传资源' } },
  { path: '/courses', component: () => import('../pages/CourseList.vue'), meta: { title: '课程分类' } },
  { path: '/admin', component: () => import('../pages/admin/AdminDashboard.vue'), meta: { title: '后台管理' } },
]

const router = createRouter({ history: createWebHistory(), routes })
export default router
