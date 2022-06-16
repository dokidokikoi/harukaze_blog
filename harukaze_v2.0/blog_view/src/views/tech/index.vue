<template>
  <div class="container">
    <app-card class="blog_content">
      <template
        #header
      >
        <h2 style="margin: 0; text-align: center;">
          技术文章
        </h2>
      </template>
      <tech-item
        v-for="item in articleList"
        :key="item.id"
        :article="item"
      />

      <blog-pagination
        v-if="!(totalCount !== 0 && articleList.length >= totalCount)"
        :load-list="loadList"
        v-model:page="searchParam.page"
      />
    </app-card>
  </div>
</template>

<script lang="ts" setup>
import { IArticleDetail, ISearchParam } from '@/api/types/article/article'
import { onMounted, reactive, ref } from 'vue'
import * as articleApi from '@/api/article'
import TechItem from './TechItem/index.vue'
import { useStore } from '@/store'

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
  const { data } = await articleApi.getArticleList(searchParam).finally(() => {
    store.commit('loading', false)
  })
  articleList.value = articleList.value.concat(data.list)
  totalCount.value = data.totalCount
}
onMounted(() => {
  loadList()
})
</script>

<style lang='scss' scoped>
.container {
  background-color: #e9eef3;
}

.blog_content {
  width: 700px;
  padding: 10px;
  position: relative;
  background: transparent;
  box-shadow: none;
  border: none;
}

::v-deep .el-card__body {
  padding-top: 0;
}

.blog_header {
  font-size: 1.3em;
  position: absolute;
  top: -50px;
  left: 0;

  span {
    font-size: 1.1em;
    font-weight:bold;
  }
}

.el-pagination {
    margin-top: 20px;
}
</style>
