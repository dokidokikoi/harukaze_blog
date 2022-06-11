<template>
  <h1>首页</h1>
  <el-button>测试按钮</el-button>
</template>

<script lang="ts" setup>
import { onMounted, ref } from 'vue'
import { getLoginInfo } from '@/api/common'
import type { ILoginInfo } from '@/api/types/common'

const list = ref<ILoginInfo['slide']>([])

// const mark = import.meta.env.VITE_APP_TITLE

onMounted(() => {
  getLoginInfo().then(res => {
    list.value = res.slide
    console.log(list)
  })
})
</script>

<style lang='scss' scoped>
// @import "@/styles/variables.scss"

h1 {
  background-color: $color
}

</style>
