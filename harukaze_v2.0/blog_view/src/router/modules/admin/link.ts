import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'link',
  name: 'link',
  meta: {
    title: '友链'
  },
  component: RouterView,
  children: [
    {
      path: '',
      component: () => import('@/admin/link/index.vue')
    }
  ]
}

export default routes
