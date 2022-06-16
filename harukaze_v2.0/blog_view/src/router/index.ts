import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import articleRoutes from './modules/admin/article'
import messageRoutes from './modules/admin/message'
import linkRoutes from './modules/admin/link'
import permissionRoutes from './modules/admin/permission'
import userRoutes from './modules/admin/user'
import logRoutes from './modules/admin/log'
import nprogress from 'nprogress'
import 'nprogress/nprogress.css'
import { store } from '@/store'
import { ElMessage } from 'element-plus'

const routes: RouteRecordRaw[] = [
  {
    path: '/admin',
    component: () => import('@/admin/layout/AppLayout.vue'),
    children: [
      {
        path: '', // 默认子路由
        name: 'home',
        component: () => import('../admin/home/index.vue')
      },
      articleRoutes,
      permissionRoutes,
      userRoutes,
      logRoutes,
      messageRoutes,
      linkRoutes
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../admin/login/index.vue')
  },
  {
    path: '/',
    component: import('@/views/layout/AppLayout.vue'),
    children: [
      {
        path: '', // 默认子路由
        name: 'index',
        component: () => import('../views/home/index.vue')
      },
      {
        path: 'project',
        name: 'project',
        component: () => import('../views/project/index.vue')
      },
      {
        path: 'archives',
        name: 'archives',
        component: () => import('../views/archives/index.vue')
      },
      {
        path: 'note',
        name: 'note',
        component: () => import('../views/note/index.vue')
      },
      {
        path: 'message',
        name: 'viewMessage',
        component: () => import('../views/message/index.vue')
      },
      {
        path: 'link',
        name: 'viewLink',
        component: () => import('../views/link/index.vue')
      },
      {
        path: 'about',
        name: 'about',
        component: () => import('../views/about/index.vue')
      },
      {
        path: 'blog/:id',
        name: 'blog',
        component: () => import('@/views/blog/index.vue')
      },
      {
        path: 'search',
        name: 'search',
        component: () => import('@/views/search/index.vue')
      }
    ]
  },
  {
    path: '/:catchAll(.*)',
    name: '404',
    component: () => import('@/views/404/index.vue')
  }
]

const router = createRouter({
  // history: createWebHashHistory(), // 路由模式
  history: createWebHistory(),
  routes, // 路由规则
  scrollBehavior (to, from, savedPosition) {
    // return desired position
    return { top: 0 }
  }
})

router.beforeEach((to, from, next) => {
  nprogress.start()
  const patt = /\/admin\/*/
  if (to.name === 'login') {
    next()
  } else if (patt.test(to.fullPath) && !store.state.user) {
    // router.push({
    //   name: 'login',
    //   query: {
    //     redirect: router.currentRoute.value.fullPath
    //   }
    // })
    ElMessage.info('请登录')
    next('/login')
  } else {
    next()
  }
  next()
})

router.afterEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  const body = document.querySelector('.el-main')
  if (body) body.scrollTop = 0
  nprogress.done()
})

export default router
