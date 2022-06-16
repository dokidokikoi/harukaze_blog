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
        <el-form-item label="分类名">
          <el-input
            v-model="searchParam.key"
            placeholder="请输入分类名关键字"
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
          @click="tagDialogVisible = true"
        >
          新增分类
        </el-button>
      </template>
      <el-table
        :data="tagList"
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="ID"
        />
        <el-table-column
          prop="tagName"
          label="标签名"
        />
        <el-table-column
          label="操作"
          fixed="right"
          align="center"
        >
          <template #default="{ row }">
            <el-tooltip
              effect="dark"
              content="编辑标签"
              placement="top-end"
            >
              <el-button
                type="primary"
                @click="editTag(row)"
              >
                <el-icon><Edit /></el-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip
              effect="dark"
              content="删除标签"
              placement="top-start"
            >
              <el-button
                type="danger"
                style="margin-left: 10px;"
                @click="deleteTag(row.id)"
              >
                <el-icon><Delete /></el-icon>
              </el-button>
            </el-tooltip>
          </template>
        </el-table-column>
      </el-table>

      <app-pagination
        v-model:page="searchParam.page"
        v-model:limit="searchParam.limit"
        :list-count="listCount"
        :load-list="loadList"
      />
    </app-card>

    <app-dialog-form
      :confirm="isUpdate ? handleUpdateTag : handleAddTag"
      v-model="tagDialogVisible"
      @close="handleClose"
    >
      <el-form
        :model="tagParam"
        :rules="tagFormRules"
        ref="tagForm"
        label-width="100px"
      >
        <el-form-item
          label="标签名"
          prop="tagName"
        >
          <el-input
            style="width=50%"
            v-model="tagParam.tagName"
            placeholder="请输入分类名"
          />
        </el-form-item>
      </el-form>
    </app-dialog-form>
  </page-container>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue'
import * as tagApi from '@/api/tag'
import { onMounted, reactive, ref } from 'vue'
import { ICommonParam } from '@/api/types/common'
import { IElForm } from '@/types/element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'
import { ITag } from '@/api/types/article/tag'

const listLoading = ref(false)
const searchParam = reactive<ICommonParam>({
  key: '',
  limit: 5,
  page: 1
})

const tagList = ref<ITag[]>([])
const listCount = ref(0)

const isUpdate = ref(false)
const tagDialogVisible = ref(false)

const tagForm = ref<IElForm | null>(null)
const tagFormRules = reactive({
  tagName: [
    { required: true, message: '请输入标签名称', trigger: 'blur' }
  ]
})

const tagParam = reactive<ITag>({
  id: '',
  tagName: ''
})

onMounted(() => {
  loadList()
})

const loadList = async () => {
  listLoading.value = true
  const { data } = await tagApi.getTagList(searchParam).finally(() => {
    listLoading.value = false
  })

  tagList.value = data.list
  listCount.value = data.totalCount
}

const handleUpdateTag = async () => {
  // 表单验证
  const valid = await tagForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await tagApi.editTag(tagParam).finally(() => {
    loadList()
    tagDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('修改分类成功')
  }
}

const handleAddTag = async () => {
  // 表单验证
  const valid = await tagForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await tagApi.addTag(tagParam).finally(() => {
    loadList()
    tagDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('新增分类成功')
  }
}

const handleClose = () => {
  tagParam.id = ''
  tagParam.tagName = ''

  isUpdate.value = false
}

const editTag = (row: any) => {
  tagParam.id = row.id
  tagParam.tagName = row.tagName

  isUpdate.value = true
  tagDialogVisible.value = true
}

const deleteTag = async (id: string) => {
  ElMessageBox.confirm(
    '该操作不可逆，是否删除?',
    'Warning',
    {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning'
    }
  )
    .then(() => {
      tagApi.delTag(id).then(data => {
        if (data.code === 200) {
          ElMessage.success('删除标签成功')
        }
      }).finally(() => {
        loadList()
      })
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '操作取消'
      })
    })
}

</script>

<style lang='scss' scoped>

</style>
