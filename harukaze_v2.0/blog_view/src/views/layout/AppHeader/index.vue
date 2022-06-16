<template>
  <el-header id="header">
    <el-space size="large">
      <h2>
        <router-link to="/">
          Harukaze
        </router-link>
      </h2>
      <nav-bar />
    </el-space>
    <el-space size="large">
      <search />
      <user-info />
    </el-space>
  </el-header>
</template>

<script lang="ts" setup>
import { onMounted } from 'vue'
import NavBar from './NavBar.vue'
import Search from './Search.vue'
import UserInfo from './UserInfo.vue'

onMounted(() => {
  window.addEventListener('scroll', () => {
    const header = document.querySelector('#header')
    const main = document.querySelector('html')
    if (main?.scrollTop) header?.classList.toggle('show', main?.scrollTop > 0)
    else header?.classList.remove('show')
  }, true)
})
</script>

<style lang="scss" scoped>
.el-header {
  background: transparent;
  width: 99.4vw;
  margin: 0;
  margin-right: 10px !important;
  position: fixed;
  display: flex;
  justify-content: space-between;
  align-items: center;
  z-index: 1000;
  transition: all 0.2s ease-in-out;
  background-color: rgb(65, 139, 105, 0);

  &:hover {
    background-color: rgb(65, 139, 105, 1);
  }
}
.show {
  background-color: #78808B;
}

h2 {
  margin: 0 40px;
  padding: 15px 0;
  a {
      text-decoration: none;
      color: pink;
  }
}

::v-deep .el-space__item {
  height: 62px;
  align-items: center;
}

</style>
