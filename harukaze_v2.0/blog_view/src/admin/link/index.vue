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
        <el-form-item label="友链名">
          <el-input
            v-model="searchParam.key"
            placeholder="请输入友链名关键字"
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
          @click="linkDialogVisible = true"
        >
          新增分类
        </el-button>
      </template>
      <el-table
        :data="linkList"
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="ID"
        />
        <el-table-column
          prop="name"
          label="友链名"
        />
        <el-table-column
          prop="avatar"
          label="头像"
          align="center"
        >
          <template #default="{row}">
            <img
              v-show="row.avatar"
              :src="row.avatar"
              style="object-fit: cover; width: 100px; height: 100px"
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="siteDesc"
          label="友链简介"
        />
        <el-table-column
          prop="url"
          label="网址"
        />
        <el-table-column
          prop="createDate"
          label="创建时间"
        >
          <template #default="{row}">
            {{ formatDate(row.createDate) }}
          </template>
        </el-table-column>
        <el-table-column
          prop="state"
          label="状态"
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
              :loading="isLoad === scope.row.id"
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
              content="编辑友链"
              placement="top-start"
            >
              <el-button
                type="primary"
                @click="editLink(row)"
              >
                <el-icon><Edit /></el-icon>
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
      :confirm="isUpdate ? handleUpdateLink : handleAddLink"
      v-model="linkDialogVisible"
      @close="handleClose"
    >
      <el-form
        :model="linkParam"
        :rules="linkFormRules"
        ref="linkForm"
        label-width="100px"
      >
        <el-form-item
          label="友链名"
          prop="name"
        >
          <el-input
            style="width=50%"
            v-model="linkParam.name"
            placeholder="请输入友链名"
          />
        </el-form-item>
        <el-form-item
          label="友链网址"
          prop="url"
        >
          <el-input
            style="width=50%"
            v-model="linkParam.url"
            placeholder="请输入友链网址"
          />
        </el-form-item>
        <el-form-item
          label="友链头像"
          prop="avatar"
        >
          <img-upload
            :img="linkParam.avatar"
            :app-width="'200'"
            :app-height="'200'"
            @get-img="getImg"
          />
        </el-form-item>
        <el-form-item
          label="友链简介"
          prop="siteDesc"
        >
          <el-input
            style="width=50%"
            type="textarea"
            v-model="linkParam.siteDesc"
            placeholder="请输入友链简介(一句话)"
          />
        </el-form-item>
      </el-form>
    </app-dialog-form>
  </page-container>
</template>

<script lang="ts" setup>
import { ICommonParam } from '@/api/types/common'
import { IFriendLink } from '@/api/types/friendLink'
import { onMounted, reactive, ref } from 'vue'
import * as linkApi from '@/api/friendLink'
import { Search } from '@element-plus/icons-vue'
import { formatDate } from '@/utils/time'
import { IElForm } from '@/types/element-plus'
import { ElMessage } from 'element-plus'

const searchParam = reactive<ICommonParam>({
  key: '',
  page: 1,
  limit: 5
})

const linkParam = reactive<IFriendLink>({
  id: '',
  name: '',
  url: '',
  avatar: '',
  siteDesc: '',
  state: '',
  createDate: ''
})

const listLoading = ref(false)
const linkList = ref<IFriendLink[]>([])
const listCount = ref(0)

onMounted(() => {
  loadList()
})
const loadList = async () => {
  listLoading.value = true
  const { data } = await linkApi.getLinkList(searchParam).finally(() => {
    listLoading.value = false
  })

  linkList.value = data.list
  listCount.value = data.totalCount
}

// 从上传组件获取图片url
const getImg = (url: string) => {
  // console.log(url)
  linkParam.avatar = url
}

const isLoad = ref('0')
const handleUpdateStatus = async (row: any) => {
  isLoad.value = row.id
  const data = await linkApi.setLinkState({ id: row.id, flag: row.state !== 0 }).finally(() => {
    isLoad.value = '0'
  })

  if (data.code === 200) {
    ElMessage.success('修改状态成功')
    loadList()
  }
}

const isUpdate = ref(false)

const linkDialogVisible = ref(false)
const editLink = (row: any) => {
  isUpdate.value = true
  linkParam.id = row.id
  linkParam.avatar = row.avatar
  linkParam.name = row.name
  linkParam.siteDesc = row.siteDesc
  linkParam.url = row.url

  linkDialogVisible.value = true
}
const handleUpdateLink = async (row: any) => {
  // 表单验证
  const valid = await linkForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await linkApi.editLink(linkParam)

  if (data.code === 200) {
    ElMessage.success('修改成功')
    loadList()
    linkDialogVisible.value = false
  }
}

const linkFormRules = reactive({
  name: [
    { required: true, message: '请输入友链名称', trigger: 'blur' }
  ],
  url: [
    { required: true, message: '请输入友链网址', trigger: 'blur' }
  ],
  siteDesc: [
    { required: true, message: '请输入友链简介', trigger: 'blur' }
  ]
})
const linkForm = ref<IElForm | null>(null)
const handleAddLink = async () => {
  // 表单验证
  const valid = await linkForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await linkApi.addLink(linkParam)

  if (data.code === 200) {
    ElMessage.success('添加成功')
    loadList()
    linkDialogVisible.value = false
  }
}

const handleClose = () => {
  linkParam.id = ''
  linkParam.avatar = ''
  linkParam.name = ''
  linkParam.siteDesc = ''
  linkParam.url = ''

  isUpdate.value = false
}

</script>

<style lang='scss' scoped>

</style>
