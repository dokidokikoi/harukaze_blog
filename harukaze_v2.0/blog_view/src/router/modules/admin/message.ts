import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'message',
  name: 'message',
  meta: {
    title: '留言'
  },
  component: RouterView,
  children: [
    {
      path: '',
      component: () => import('@/admin/message/index.vue')
    }
  ]
}

export default routes
