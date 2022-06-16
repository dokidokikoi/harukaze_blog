<template>
  <div class="home-container">
    <app-banner
      :title="'归档'"
      :img="'src/assets/archivesBannar.png'"
    />
  </div>
  <div class="main">
    <p class="header">
      时间轴
    </p>
    <time-stamp
      v-if="articleList.length"
      :article-list="articleList"
    />
  </div>
</template>

<script lang="ts" setup>
import { getAllArticle } from '@/api/article'
import { IArticleDetail } from '@/api/types/article/article'
import { useStore } from '@/store'
import { ref } from 'vue'
import TimeStamp from './TimeStamp/index.vue'

const store = useStore()
const articleList = ref<IArticleDetail[]>([])
const loadList = async () => {
  store.commit('loading', true)
  const { data } = await getAllArticle().finally(() => {
    store.commit('loading', false)
  })
  articleList.value = data
}
loadList()

</script>

<style lang='scss' scoped>
.main {
  max-width: 800px;
  margin: auto;

  .header {
    font-size: 1.5em;
    font-weight: 500;
    color: #606060;
    text-align: center;
    margin: 10px 0;
    padding: 10px 40px 30px 40px;
    box-sizing: border-box;

    span {
      color: orange;
    }
  }
}
</style>
