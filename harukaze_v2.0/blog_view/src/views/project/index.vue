<template>
  <div class="home-container">
    <app-banner
      :title="'项目'"
      :img="'src/assets/projectBannar.png'"
    />

    <div class="main">
      <BlogItem :article-list="articleList" />

      <blog-pagination
        v-if="!(totalCount !== 0 && articleList.length >= totalCount)"
        :load-list="loadList"
        v-model:page="searchParam.page"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { getArticleList } from '@/api/article'
import { IArticleDetail, ISearchParam } from '@/api/types/article/article'
import { useStore } from '@/store'
import { reactive, ref } from 'vue'
import BlogItem from './Item/BlogItem.vue'

const searchParam = reactive<ISearchParam>({
  key: '',
  categoryId: '',
  tags: [],
  limit: 5,
  page: 1,
  sort: {
    time: '',
    view: '',
    comment: ''
  }
})

const store = useStore()

const totalCount = ref(0)
const articleList = ref<IArticleDetail[]>([])
const loadList = async () => {
  if (totalCount.value !== 0 && articleList.value.length >= totalCount.value) return
  store.commit('loading', true)
  const { data } = await getArticleList(searchParam).finally(() => {
    store.commit('loading', false)
  })
  articleList.value = articleList.value.concat(data.list)
  totalCount.value = data.totalCount
}
loadList()

</script>

<style lang='scss' scoped>
.main {
  max-width: 800px;
  margin: auto;
}
</style>
