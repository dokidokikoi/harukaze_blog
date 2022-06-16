/* eslint-disable vue/require-default-prop */
<template>
  <el-table
    :data="articleList"
    style="width: 100%"
  >
    <el-table-column
      type="expand"
    >
      <template #default="{row}">
        <el-divider content-position="left">
          简介
        </el-divider>
        <el-card style="width: 90%; margin: auto;">
          <p>
            {{ row.summary }}
          </p>
        </el-card>
        <el-divider content-position="left">
          category
        </el-divider>
        <el-tag
          effect="dark"
          style="margin-left: 40px;"
        >
          {{ row.category.categoryName }}
        </el-tag>
        <el-divider content-position="left">
          tags
        </el-divider>
        <el-tag
          v-for="item in row.tags"
          :key="item.id"
          effect="dark"
          type="success"
          style="margin-left: 40px;"
        >
          {{ item.tagName }}
        </el-tag>
        <el-divider content-position="left">
          文章评论
        </el-divider>
        <app-comment
          style="width: 80%; margin: auto"
          :article-id="row.id"
        />
      </template>
    </el-table-column>
    <el-table-column
      prop="id"
      label="ID"
      width="60px"
    />
    <el-table-column
      prop="title"
      label="标题"
    />
    <el-table-column
      prop="viewCounts"
      label="浏览数"
    />
    <el-table-column
      prop="commentCounts"
      label="评论数"
    />
    <el-table-column
      prop="weight"
      label="权重"
    />
    <el-table-column
      prop="weight"
      label="作者"
    >
      <template #default="{ row }">
        <span>{{ row.author.nickname }}</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="weight"
      label="创建时间"
      width="150px"
    >
      <template #default="{ row }">
        <span>{{ formatDate(row.createDate) }}</span>
      </template>
    </el-table-column>
    <el-table-column
      prop="weight"
      label="更新时间"
      width="150px"
    >
      <template #default="{ row }">
        <span>{{ formatDate(row.updateDate) }}</span>
      </template>
    </el-table-column>
    <el-table-column
      label="状态"
      width="150"
    >
      <template #default="scope">
        <el-switch
          v-model="scope.row.state"
          active-color="#13ce66"
          inactive-color="#ff4949"
          :active-value="0"
          :inactive-value="1"
          active-text="发布"
          inactive-text="下撤"
          @change="handleUpdateStatus(scope.row)"
          :loading="isLoad == scope.row.id"
        />
      </template>
    </el-table-column>
    <el-table-column
      label="操作"
      fixed="right"
      align="center"
      width="60"
    >
      <template #default="{ row }">
        <el-tooltip
          effect="dark"
          content="编辑文章"
          placement="top-start"
        >
          <el-button
            type="primary"
            size="small"
            @click="toEditPage(row.id)"
          >
            <el-icon><Edit /></el-icon>
          </el-button>
        </el-tooltip>
        <el-tooltip
          effect="dark"
          content="置顶文章"
          placement="bottom-start"
        >
          <el-badge
            is-dot
            :hidden="row.weight === 1"
          >
            <el-button
              type="info"
              size="small"
              @click="fixedTop(row.id, row.weight)"
              style="margin: 0;"
            >
              <el-icon><Star /></el-icon>
            </el-button>
          </el-badge>
        </el-tooltip>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { IArticleDetail } from '@/api/types/article/article'
import * as articleApi from '@/api/article'
import { PropType, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRouter } from 'vue-router'
import { formatDate } from '@/utils/time'

const props = defineProps({
  // eslint-disable-next-line vue/require-default-prop
  articleList: { // 页码
    type: Object
  },
  loadList: {
    type: Function as PropType<(...args: any[]) => void>,
    default: () => {}
  }
})

const router = useRouter()

const isLoad = ref('0')

const handleUpdateStatus = async (article: IArticleDetail) => {
  isLoad.value = article.id
  const params = {
    id: article.id,
    flag: article.state === 0
  }
  const data = await articleApi.setBlogState(params).finally(() => {
    isLoad.value = '0'
    props.loadList()
  })

  if (data.code === 200) {
    ElMessage.success('修改成功')
  }
}

const toEditPage = (id: string) => {
  router.push('/admin/article/edit/' + id)
}

const fixedTop = async (id: string, weight: number) => {
  const data = await articleApi.fixedTop({
    id,
    weight: weight === 1 ? 10 : 1
  })

  if (data.code === 200) {
    if (weight === 1) {
      ElMessage.success('置顶成功')
    } else {
      ElMessage.info('取消置顶')
    }
    props.loadList()
  }
}

</script>

<style lang='scss' scoped>

</style>
