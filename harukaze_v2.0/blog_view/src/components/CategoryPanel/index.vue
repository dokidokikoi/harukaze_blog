<template>
  <el-card class="category_content">
    <div class="title">
      <svg-icon
        name="category"
        class="icon"
      />
      <span>Category</span>
    </div>
    <div class="content">
      <router-link
        :to="`/search?category=${item.id}`"
        v-for="item in categoryList"
        :key="item.id"
        style="margin: 10px 10px 10px 0"
      >
        {{ item.categoryName }}
      </router-link>
    </div>
  </el-card>
</template>

<script lang="ts" setup>
import { ICategory } from '@/api/types/article/category'
import { ref } from 'vue'
import { getCategoryList } from '@/api/category'

const categoryList = ref<ICategory[]>([])
const loadCategoryList = async () => {
  const { data } = await getCategoryList({
    key: '',
    page: 1,
    limit: 1000
  })

  categoryList.value = data.list
}
loadCategoryList()

</script>

<style lang='scss' scoped>
.category_content {
  width: 300px;
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
