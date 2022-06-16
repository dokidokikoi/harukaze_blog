<template>
  <el-container>
    <app-loading v-show="$store.state.isLoading" />
    <app-header />
    <el-main>
      <router-view :key="new Date().getTime()" />
      <el-footer>
        <app-footer />
      </el-footer>
    </el-main>
    <transition
      name="showup"
      appear
    >
      <GoTop
        class="gotop"
        v-show="isShowTog"
      />
    </transition>
  </el-container>
</template>

<script lang="ts" setup>
import AppHeader from './AppHeader/index.vue'
import AppFooter from './AppFooter/index.vue'
import GoTop from './GoTop/index.vue'
import { onMounted, ref } from 'vue'

const scrollTop = ref(0)
const isShowTog = ref(false)

onMounted(() => {
  window.addEventListener('scroll', () => {
    scrollTop.value = document.querySelector('html')?.scrollTop
    if (scrollTop.value > 500) isShowTog.value = true
    else isShowTog.value = false
  })
})
</script>

<style lang='scss' scoped>
.el-main {
  padding-top: 60px !important;
  background-color: #e9eef3;
  color: #333;
  padding: 0;
  overflow: visible;
}
.el-container {
  // height: 100vh;
  // min-height: 100vh;
  position: relative;
}

.el-footer {
  bottom: 0;
  width: 100%;
  color: #606060;
  height: auto;
  margin-top: 100px;
}

.showup-enter-active{
  animation: shirohaShowup 1s linear;
}

.showup-leave-active{
  animation: shirohaShowup .5s linear reverse;
}

@keyframes shirohaShowup {
  from{
    transform: translateY(100%);
  }
  to{
    transform: translateY(0);
  }
}
</style>
