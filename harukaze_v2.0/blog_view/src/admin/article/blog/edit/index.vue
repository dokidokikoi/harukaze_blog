<template>
  <blog-editor
    v-if="isShow"
    :article-param="articleParam"
    @post-blog="postBlog"
  >
    <img-upload
      @get-img="getImg"
      :img="articleParam.cover"
    />
  </blog-editor>
</template>

<script lang="ts" setup>
import { IArticleParam } from '@/api/types/article/article'
import { onMounted, reactive, ref } from 'vue'
import BlogEditor from '../BlogEditor/BlogEditor.vue'
import * as articleApi from '@/api/article'
import { ElMessage } from 'element-plus'
import { getItem, removeItem } from '@/utils/storage'
import { useRouter } from 'vue-router'
import { useStore } from '@/store'

const store = useStore()
const router = useRouter()
const isShow = ref(false)

onMounted(() => (
  store.commit('setArticleType', 'editArticle')
))

const articleParam = reactive<IArticleParam>({
  id: '',
  title: '',
  summary: '',
  cover: '',
  weight: 1,
  state: 0,
  body: {
    id: '',
    content: '',
    contentHtml: '',
    articleId: '',
    catalog: ''
  },
  categoryId: '',
  tags: []
})

const getArticleById = async (id: string) => {
  const { data } = await articleApi.getArticleDetail(id)

  return data
}

const init = async () => {
  const param = getItem<IArticleParam>('editArticle')
  console.log(router.currentRoute.value.params.id)
  if (param && param.id === router.currentRoute.value.params.id) {
    articleParam.id = param.id
    articleParam.title = param.title
    articleParam.summary = param.summary
    articleParam.cover = param.cover
    articleParam.weight = param.weight
    articleParam.state = param.state
    articleParam.body = {
      id: param.body.id,
      content: param.body.content,
      contentHtml: param.body.contentHtml,
      catalog: param.body.catalog,
      articleId: param.body.articleId
    }
    articleParam.categoryId = param.categoryId
    articleParam.tags = param.tags
  } else {
    const detail = await getArticleById(router.currentRoute.value.params.id.toString())
    articleParam.id = detail.id
    articleParam.title = detail.title
    articleParam.summary = detail.summary
    articleParam.cover = detail.cover
    articleParam.weight = detail.weight
    articleParam.state = detail.state
    articleParam.categoryId = detail.category.id
    articleParam.tags = detail.tags.map(item => item.id)
    if (detail.body) {
      articleParam.body = {
        id: detail.body.id,
        content: detail.body.content,
        contentHtml: detail.body.contentHtml,
        catalog: detail.body.catalog,
        articleId: detail.body.articleId
      }
    }
  }
  isShow.value = true
}
init()

const getImg = (url: string) => {
  articleParam.cover = url
}

const postBlog = async (articleParam: IArticleParam) => {
  const data = await articleApi.updateBlog(articleParam)

  if (data.code === 200) {
    ElMessage.success('发布成功')
    removeItem(store.state.articleType)
  }
}
</script>

<style lang='scss' scoped>

</style>
