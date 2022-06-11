<template>
  <el-icon
    @click="handleCollapse"
    :size="25"
  >
    <Fold v-show="!$store.state.isCollapse" />
    <Expand v-show="$store.state.isCollapse" />
  </el-icon>
</template>

<script lang="ts" setup>
import { useStore } from '@/store'

const store = useStore()

const handleCollapse = () => {
  store.commit('setIsCollapse', !store.state.isCollapse)
}
</script>

<style lang="scss" scoped></style>
