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
          @click="categoryDialogVisible = true"
        >
          新增分类
        </el-button>
      </template>
      <el-table
        :data="categoryList"
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="ID"
        />
        <el-table-column
          prop="avatar"
          label="封面"
        />
        <el-table-column
          prop="categoryName"
          label="分类名"
        />
        <el-table-column
          prop="categoryDesc"
          label="分类描述"
        />
        <el-table-column
          label="操作"
          fixed="right"
          align="center"
        >
          <template #default="{ row }">
            <el-tooltip
              effect="dark"
              content="编辑分类"
              placement="top-end"
            >
              <el-button
                type="primary"
                @click="editCategory(row)"
              >
                <el-icon><Edit /></el-icon>
              </el-button>
            </el-tooltip>
            <el-tooltip
              effect="dark"
              content="删除分类"
              placement="top-start"
            >
              <el-button
                type="danger"
                style="margin-left: 10px;"
                @click="deleteCategory(row.id)"
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
      :confirm="isUpdate ? handleUpdateCategory : handleAddCategory"
      v-model="categoryDialogVisible"
      @close="handleClose"
    >
      <el-form
        :model="categoryParam"
        :rules="categoryFormRules"
        ref="categoryForm"
        label-width="100px"
      >
        <el-form-item
          label="分类名"
          prop="categoryName"
        >
          <el-input
            style="width=50%"
            v-model="categoryParam.categoryName"
            placeholder="请输入分类名"
          />
        </el-form-item>
        <el-form-item label="分类描述">
          <el-input
            v-model="categoryParam.categoryDesc"
            placeholder="请输入分类描述"
          />
        </el-form-item>
      </el-form>
    </app-dialog-form>
  </page-container>
</template>

<script lang="ts" setup>
import { Search } from '@element-plus/icons-vue'
import { ICategory } from '@/api/types/article/category'
import * as categoryApi from '@/api/category'
import { onMounted, reactive, ref } from 'vue'
import { ICommonParam } from '@/api/types/common'
import { IElForm } from '@/types/element-plus'
import { ElMessage, ElMessageBox } from 'element-plus'

const listLoading = ref(false)
const searchParam = reactive<ICommonParam>({
  key: '',
  limit: 5,
  page: 1
})

const categoryList = ref<ICategory[]>([])
const listCount = ref(0)

const isUpdate = ref(false)
const categoryDialogVisible = ref(false)

const categoryForm = ref<IElForm | null>(null)
const categoryFormRules = reactive({
  categoryName: [
    { required: true, message: '请输入分类名称', trigger: 'blur' }
  ]
})

const categoryParam = reactive<ICategory>({
  id: '',
  avatar: '',
  categoryName: '',
  categoryDesc: ''
})

onMounted(() => {
  loadList()
})

const loadList = async () => {
  listLoading.value = true
  const { data } = await categoryApi.getCategoryList(searchParam).finally(() => {
    listLoading.value = false
  })

  categoryList.value = data.list
  listCount.value = data.totalCount
}

const handleUpdateCategory = async () => {
  // 表单验证
  const valid = await categoryForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await categoryApi.editCategory(categoryParam).finally(() => {
    loadList()
    categoryDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('修改分类成功')
  }
}

const handleAddCategory = async () => {
  // 表单验证
  const valid = await categoryForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await categoryApi.addCategory(categoryParam).finally(() => {
    loadList()
    categoryDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('新增分类成功')
  }
}

const handleClose = () => {
  categoryParam.id = ''
  categoryParam.avatar = ''
  categoryParam.categoryName = ''
  categoryParam.categoryDesc = ''

  isUpdate.value = false
}

const editCategory = (row: any) => {
  categoryParam.id = row.id
  categoryParam.avatar = row.avatar
  categoryParam.categoryName = row.categoryName
  categoryParam.categoryDesc = row.categoryDesc

  isUpdate.value = true
  categoryDialogVisible.value = true
}

const deleteCategory = async (id: string) => {
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
      categoryApi.deleteCategory(id).then(data => {
        if (data.code === 200) {
          ElMessage.success('删除分类成功')
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
