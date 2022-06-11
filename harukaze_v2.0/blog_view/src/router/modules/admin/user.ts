import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'user',
  name: 'user',
  meta: {
    title: '用户'
  },
  component: RouterView,
  children: [
    {
      path: '',
      component: () => import('@/admin/user/index.vue')
    }
  ]
}

export default routes
