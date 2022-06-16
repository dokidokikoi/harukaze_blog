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
        <el-form-item label="关键字">
          <el-input
            v-model="searchParam.key"
            placeholder="请输入模块名、ip、操作关键字"
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
      <template #header />
      <el-table
        :data="logList"
        style="width: 100%"
      >
        <el-table-column
          prop="id"
          label="ID"
        />
        <el-table-column
          prop="ip"
          label="ip地址"
        />
        <el-table-column
          prop="module"
          label="模块"
        />
        <el-table-column
          prop="method"
          label="方法"
        />
        <el-table-column
          prop="address"
          label="地址"
        />
        <el-table-column
          prop="operation"
          label="操作"
        />
        <el-table-column
          prop="time"
          label="执行时间"
        >
          <template #default="{row}">
            {{ row.time }}ms
          </template>
        </el-table-column>
        <el-table-column
          prop="userId"
          label="用户id"
        />
        <el-table-column
          prop="createDate"
          label="创建日期"
        >
          <template #default="{row}">
            {{ formatDate(row.createDate) }}
          </template>
        </el-table-column>
        <el-table-column
          prop="browser"
          label="浏览器"
        />
        <el-table-column
          prop="os"
          label="操作系统"
        />
        <el-table-column
          label="操作"
          fixed="right"
          align="center"
        >
          <template #default="{ row }">
            <el-tooltip
              effect="dark"
              content="删除日志"
              placement="top-end"
            >
              <el-button
                type="danger"
                @click="delLog(row)"
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
  </page-container>
</template>

<script lang="ts" setup>
import { ICommonParam } from '@/api/types/common'
import { ILog } from '@/api/types/log'
import { onMounted, reactive, ref } from 'vue'
import * as logApi from '@/api/log'
import { Search } from '@element-plus/icons-vue'
import { formatDate } from '@/utils/time'
import { ElMessage, ElMessageBox } from 'element-plus'

const listLoading = ref(false)
const searchParam = reactive<ICommonParam>({
  key: '',
  limit: 5,
  page: 1
})

const listCount = ref(0)
const logList = ref<ILog[]>([])
const loadList = async () => {
  listLoading.value = true
  const { data } = await logApi.getlogList(searchParam).finally(() => {
    listLoading.value = false
  })

  logList.value = data.list
  listCount.value = data.totalCount
}
onMounted(() => {
  loadList()
})

const delLog = async (row: ILog) => {
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
      logApi.delLog(row.id).then(data => {
        if (data.code === 200) {
          ElMessage.success('删除日志成功')
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
