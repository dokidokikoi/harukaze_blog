<template>
  <page-container>
    <app-card>
      <template #header>
        数据筛选
      </template>
      <el-form
        ref="form"
        :model="searchParam"
        :disabled="listLoading"
        label-width="70px"
      >
        <el-form-item label="文章分类">
          <el-select
            v-model="searchParam.categoryId"
            placeholder="请选择"
            @change="loadList"
            clearable
          >
            <el-option
              v-for="item in categoryList"
              :key="item.id"
              :label="item.categoryName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="文章标签">
          <el-select
            v-model="searchParam.tags"
            multiple
            placeholder="请选择"
            @change="loadList"
            clearable
            style="width: 50%;"
          >
            <el-option
              v-for="item in tagList"
              :key="item.id"
              :label="item.tagName"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="文章标题">
          <el-input
            v-model="searchParam.key"
            placeholder="请输入文章标题关键字"
            clearable
            style="width: 300px;"
            @keydown.enter="loadList"
          >
            <template #append>
              <el-button
                :icon="Search"
                @click="loadList"
              />
            </template>
          </el-input>
        </el-form-item>
      </el-form>
    </app-card>

    <app-card>
      <template #header>
        <el-button
          type="primary"
          @click="toWritePage"
        >
          写文章
        </el-button>
      </template>
      <!-- 表格 -->
      <article-table
        :article-list="articleList"
        :load-list="loadList"
      />
      <app-pagination
        v-model:page="searchParam.page"
        v-model:limit="searchParam.limit"
        :list-count="listCount"
        :load-list="loadList"
      />
    </app-card>
  </page-container>
</template>

<script lang="ts" setup>
import * as articleApi from '@/api/article'
import * as categoryApi from '@/api/category'
import * as tagApi from '@/api/tag'
import { IArticleDetail, ISearchParam } from '@/api/types/article/article'
import { ICategory } from '@/api/types/article/category'
import { onMounted, reactive, ref } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { ITag } from '@/api/types/article/tag'

import ArticleTable from './table/ArticleTable.vue'
import { useRouter } from 'vue-router'

const router = useRouter()

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
const listLoading = ref(false)
const categoryList = ref<ICategory[]>([])
const tagList = ref<ITag[]>([])
const articleList = ref<IArticleDetail[]>([])

const listCount = ref(0)

const loadList = async () => {
  const { data } = await articleApi.getArticleList(searchParam)
  articleList.value = data.list
  listCount.value = data.totalCount
  loadCategoryList()
  loadTagList()
}

const loadCategoryList = async () => {
  const { data } = await categoryApi.getCategoryList({ key: '', page: 1, limit: 1000 })
  categoryList.value = data.list
}

const loadTagList = async () => {
  const { data } = await tagApi.getTagList({ key: '', page: 1, limit: 1000 })
  tagList.value = data.list
}

const toWritePage = (id: string) => {
  router.push('/admin/article/write')
}

onMounted(() => {
  loadList()
})

</script>

<style lang='scss' scoped>

</style>
