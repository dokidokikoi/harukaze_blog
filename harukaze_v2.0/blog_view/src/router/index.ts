import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import AppLayout from '@/admin/layout/AppLayout.vue'
import articleRoutes from './modules/admin/article'
import messageRoutes from './modules/admin/message'
import linkRoutes from './modules/admin/link'
import permissionRoutes from './modules/admin/permission'
import userRoutes from './modules/admin/user'
import logRoutes from './modules/admin/log'
import nprogress from 'nprogress'
import 'nprogress/nprogress.css'

const routes: RouteRecordRaw[] = [
  {
    path: '/admin',
    component: AppLayout,
    children: [
      {
        path: '', // 默认子路由
        name: 'home',
        component: () => import('../admin/home/index.vue')
      },
      articleRoutes,
      messageRoutes,
      linkRoutes,
      permissionRoutes,
      userRoutes,
      logRoutes
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../admin/login/index.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(), // 路由模式
  routes // 路由规则
})

router.beforeEach(() => {
  nprogress.start()
})

router.afterEach(() => {
  nprogress.done()
})

export default router
