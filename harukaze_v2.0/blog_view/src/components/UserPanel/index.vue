<template>
  <el-card class="host">
    <img
      class="avatar"
      :src="info?.host.avatar"
      @click="toAbout"
    >
    <p style="font-size: 1.2em; font-weight: 800;">
      The life still going on
    </p>
    <div class="report">
      <a href="#">
        <span class="category">文章</span>
        <span class="num">{{ articleCount }}</span>
      </a>
      <a href="#">
        <span class="category">标签</span>
        <span class="num">{{ tagCount }}</span>
      </a>
      <a href="#">
        <span class="category">分类</span>
        <span class="num">{{ categoryCount }}</span>
      </a>
    </div>

    <div class="contact">
      <a href="#">
        <svg-icon
          name="qq"
          class="icon"
        />
      </a>
      <a href="https://github.com/dokidokikoi">
        <svg-icon
          name="github"
          class="icon"
        />
      </a>
      <a href="mailto:harukaze_doki@163.com">
        <svg-icon
          name="email"
          class="icon"
        />
      </a>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { getInfo } from '@/api/common'
import { IInfo } from '@/api/types/common'
import { getTagCount } from '@/api/tag'
import { getCategoryCount } from '@/api/category'
import { getArticleCount } from '@/api/article'
import { useRouter } from 'vue-router'

const info = ref<IInfo>()
const articleCount = ref(0)
const tagCount = ref(0)
const categoryCount = ref(0)

const init = async () => {
  const { data } = await getInfo()
  info.value = data
  let res = await getTagCount()
  tagCount.value = res.data
  res = await getCategoryCount()
  categoryCount.value = res.data
  res = await getArticleCount()
  articleCount.value = res.data
}
init()

const router = useRouter()
const toAbout = () => {
  router.push('/about')
}

</script>

<style lang='scss' scoped>
.el-card {
  width: 300px;
  height: 400px;

  ::v-deep .el-card__body {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}

.avatar {
  border-radius: 50%;
  width: 150px;
  height: 150px;
  object-fit: cover;

  &:hover {
    cursor: pointer;
  }
}

.report {
  display: flex;
  justify-content: space-between;
  width: 200px;
  margin: auto;
  margin-top: 25px;
  margin-bottom: 15px;
  a {
    display: block;
    cursor: pointer;
    text-decoration: none;
    color: black;

    span {
      display: block;
      text-align: center;
      font-weight: bold;
    }
  }
}

.contact {
  width: 200px;
  margin: 20px auto;
  display: flex;
  justify-content: space-around;
  align-items: center;

  a {
    text-decoration: none;
    color: black;
  }
}
</style>
