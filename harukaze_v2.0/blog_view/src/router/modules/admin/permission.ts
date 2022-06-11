import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'permission',
  meta: {
    title: '权限'
  },
  component: RouterView,
  children: [
    {
      path: 'permission',
      name: 'permission',
      meta: {
        title: '权限'
      },
      component: () => import('@/admin/permission/permission/index.vue')
    },
    {
      path: 'role',
      name: 'role',
      meta: {
        title: '角色'
      },
      component: () => import('@/admin/permission/role/index.vue')
    }
  ]
}

export default routes
