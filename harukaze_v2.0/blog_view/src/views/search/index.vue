<template>
  <div class="home-container">
    <app-banner
      :title="'搜索'"
      :img="'src/assets/searchBannar.png'"
    />

    <div class="main">
      <p class="search_info">
        搜索结果：<span>{{ $route.query.query }}</span>
      </p>
      <item :article-list="articleList" />

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
import { onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import Item from '../project/Item/BlogItem.vue'

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

const router = useRouter()
onMounted(() => {

})

const totalCount = ref(0)
const store = useStore()
const articleList = ref<IArticleDetail[]>([])
const loadList = async () => {
  if (totalCount.value !== 0 && articleList.value.length >= totalCount.value) return
  if (router.currentRoute.value.query.query) {
    searchParam.key = router.currentRoute.value.query.query
  }
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

  .search_info {
    font-size: 1.5em;
    font-weight: 500;
    color: #606060;
    text-align: center;
    margin: 10px 0;
    padding: 10px 40px 30px 40px;
    box-sizing: border-box;

    span {
      color: orange;
    }
  }
}
</style>
