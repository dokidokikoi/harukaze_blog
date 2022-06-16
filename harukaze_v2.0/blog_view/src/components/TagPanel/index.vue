<template>
  <el-card class="tag_content">
    <div class="title">
      <svg-icon
        name="tag"
        class="icon"
      />
      <span>Tags</span>
    </div>
    <div class="content">
      <router-link
        :to="`/search?tag=${item.id}`"
        v-for="item in tagList"
        :key="item.id"
        style="margin: 10px 10px 10px 0"
      >
        {{ item.tagName }}
      </router-link>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { ITag } from '@/api/types/article/tag'
import { ref } from 'vue'
import { getTagList } from '@/api/tag'

const tagList = ref<ITag[]>([])

const loadTagList = async () => {
  const { data } = await getTagList({
    key: '',
    page: 1,
    limit: 1000
  })
  tagList.value = data.list
}
loadTagList()

</script>

<style lang='scss' scoped>
.tag_content {
  box-sizing: border-box;
  width: 300px;
  margin: 0;
  margin-bottom: 20px;
}

.title {
  border-bottom: solid 1px gray;
  padding-bottom: 5px;

  span {
    color: rgb(53, 52, 52);
    margin-left: 5px;
  }
}

.content {
  box-sizing: border-box;
  padding: 0 30px;
  margin: auto;
  display: flex;
  flex-wrap: wrap;

  a {
    text-decoration: none;
  }
}
</style>
