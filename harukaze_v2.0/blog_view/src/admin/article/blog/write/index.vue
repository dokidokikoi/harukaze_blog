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
import { useStore } from '@/store'

const store = useStore()

// 先获取数据在渲染页面
const isShow = ref(false)
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

onMounted(() => (
  store.commit('setArticleType', 'writeArticle')
))

const init = () => {
  const param = getItem<IArticleParam>('writeArticle')
  console.log(param)
  if (param) {
    articleParam.id = param.id
    articleParam.title = param.title
    articleParam.summary = param.summary
    articleParam.cover = param.cover
    articleParam.weight = param.weight
    articleParam.state = param.state
    articleParam.categoryId = param.categoryId
    articleParam.tags = param.tags
    if (param.body) {
      articleParam.body = {
        id: param.body.id,
        content: param.body.content,
        contentHtml: param.body.contentHtml,
        catalog: param.body.catalog,
        articleId: param.body.articleId
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
  const data = await articleApi.writeBlog(articleParam)

  if (data.code === 200) {
    ElMessage.success('发布成功')
    removeItem(store.state.articleType)
  }
}

</script>

<style lang='scss' scoped>

</style>
