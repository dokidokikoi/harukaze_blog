<template>
  <el-menu
    router
    :collapse-transition="true"
    :default-active="$route.path"
    :unique-opened="true"
    class="el-menu-vertical-demo"
    background-color="#304156"
    text-color="#fff"
    :collapse="$store.state.isCollapse"
  >
    <el-menu-item index="/admin">
      <el-icon><HomeFilled /></el-icon>
      <span>首页</span>
    </el-menu-item>
    <el-sub-menu index="/admin/article">
      <template #title>
        <el-icon><Notebook /></el-icon>
        <span>文章</span>
      </template>
      <el-menu-item index="/admin/article/blog">
        <el-icon><WindPower /></el-icon>
        文章列表
      </el-menu-item>
      <el-menu-item index="/admin/article/category">
        <el-icon><WindPower /></el-icon>
        文章分类
      </el-menu-item>
      <el-menu-item index="/admin/article/tag">
        <el-icon><WindPower /></el-icon>
        文章标签
      </el-menu-item>
    </el-sub-menu>
    <el-menu-item index="/admin/message">
      <el-icon><Message /></el-icon>
      <span>留言</span>
    </el-menu-item>
    <el-menu-item index="/admin/link">
      <el-icon><Link /></el-icon>
      <span>友链</span>
    </el-menu-item>
    <el-sub-menu index="/admin/permission">
      <template #title>
        <el-icon><Lock /></el-icon>
        <span>权限</span>
      </template>
      <el-menu-item index="/admin/permission/role">
        <el-icon><WindPower /></el-icon>
        <span>角色</span>
      </el-menu-item>
      <el-menu-item index="/admin/permission/permission">
        <el-icon><WindPower /></el-icon>
        权限规则
      </el-menu-item>
    </el-sub-menu>
    <el-menu-item index="/admin/user">
      <el-icon><User /></el-icon>
      <span>用户</span>
    </el-menu-item>
    <el-menu-item index="/admin/log">
      <el-icon><DataLine /></el-icon>
      <span>日志</span>
    </el-menu-item>
  </el-menu>
</template>

<script lang="ts" setup>

</script>

<style lang='scss' scoped>
.el-menu {
  border-right: none;
}

.el-menu:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

.el-menu.el-menu--collapse span{
  display: none !important;
}

::-webkit-scrollbar {
  display: none;
}
</style>
