import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'log',
  name: 'log',
  meta: {
    title: '日志'
  },
  component: RouterView,
  children: [
    {
      path: '',
      component: () => import('@/admin/log/index.vue')
    }
  ]
}

export default routes
