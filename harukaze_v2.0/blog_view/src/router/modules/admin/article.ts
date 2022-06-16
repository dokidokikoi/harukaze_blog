import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
  path: 'article',
  name: 'article',
  meta: {
    title: '文章'
  },
  component: RouterView,
  children: [
    {
      path: 'blog',
      name: 'bloglist',
      meta: {
        title: '文章列表'
      },
      component: () => import('@/admin/article/blog/list/index.vue')
    },
    {
      path: 'write',
      name: 'write',
      meta: {
        title: '写文章'
      },
      component: () => import('@/admin/article/blog/write/index.vue')
    },
    {
      path: 'edit/:id',
      name: 'edit',
      meta: {
        title: '修改文章'
      },
      component: () => import('@/admin/article/blog/edit/index.vue')
    },
    {
      path: 'tag',
      name: 'tag',
      meta: {
        title: '文章标签'
      },
      component: () => import('@/admin/article/tag/index.vue')
    },
    {
      path: 'category',
      name: 'category',
      meta: {
        title: '文章分类'
      },
      component: () => import('@/admin/article/category/index.vue')
    }
  ]
}

export default routes
