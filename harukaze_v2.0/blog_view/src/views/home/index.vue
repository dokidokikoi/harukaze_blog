<template>
  <div class="home-container">
    <div class="banner">
      <div class="bg" />
      <div
        class="bounce down"
        id="index"
        @click="startRead"
      >
        <el-icon style="color: white">
          <ArrowDown />
        </el-icon>
      </div>
    </div>
    <div class="body">
      <el-space>
        <user-panel class="panel" />
      </el-space>
      <el-space>
        <tech-blog />
      </el-space>
      <el-space style="flex-direction: column;">
        <tag-panel />
        <category-panel />
      </el-space>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { nextTick } from 'vue'
import techBlog from '../tech/index.vue'

const startRead = () => {
  nextTick(() => {
    const node = document.getElementById('index')
    if (node) {
      node.scrollIntoView({
        behavior: 'smooth',
        block: 'start'
      // inline: 'nearest'
      })
    }
  })
}
</script>

<style lang='scss' scoped>
.banner {
  height: 92vh;
  position: relative;

  .bg {
    position: absolute;
    // top: -60px;
    bottom: 0;
    width: 100%;
    height: 110%;
    background: url('@/assets/bc1.jpg');
    background-repeat: no-repeat;
    background-size: cover;
  }
}

.down {
  animation: bounce 2s infinite;
  animation-duration: 3s;
  font-size: 25px;
  position: absolute;
  bottom: 5px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 2px solid #fff;
}

.down:hover {
  animation: none;
  cursor: pointer;
  box-shadow: 0 0 20px 0 white;
  transition: all .2s;
}

@keyframes bounce {
  0%, 20%, 50%, 80%, 100% {
  transform: translate(-50%, 0);
  }
  40% {
  transform: translate(-50%, -30px);
  }
  60% {
  transform: translate(-50%, -15px);
  }
}

.body {
  width: 1350px;
  margin: auto;
  // display: flex;
}

.panel {
  flex-flow: wrap;
  align-self: baseline;
}
</style>
