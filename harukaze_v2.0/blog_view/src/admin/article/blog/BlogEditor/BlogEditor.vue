<template>
  <app-card style="margin-bottom: 20px;">
    <template #header>
      文章概要
      <el-button
        type="primary"
        @click="postArticle"
        :loading="confirmLoading"
      >
        发布
      </el-button>
    </template>
    <el-form
      ref="form"
      :model="articleParam"
      :disabled="listLoading"
      :rules="formRules"
      label-width="80px"
    >
      <el-form-item
        label="文章标题"
        prop="title"
      >
        <el-input
          v-model="articleParam.title"
          placeholder="请输入文章标题"
        />
      </el-form-item>
      <el-form-item
        label="文章分类"
        prop="categoryId"
      >
        <el-select
          v-model="articleParam.categoryId"
          placeholder="请选择"
          clearable
        >
          <el-option
            v-for="item in categoryList"
            :key="item.id"
            :label="item.categoryName"
            :value="item.id"
          />
        </el-select>
        <el-button
          type="primary"
          plain
          style="margin-left: 20px;"
          :icon="Plus"
          @click="categoryDialogVisible = true"
        />
      </el-form-item>
      <el-form-item
        label="文章标签"
        prop="tags"
      >
        <el-select
          v-model="articleParam.tags"
          multiple
          placeholder="请选择"
          clearable
          style="width: 50%;"
        >
          <el-option
            v-for="item in tagList"
            :key="item.id"
            :label="item.tagName"
            :value="item.id"
          />
        </el-select>
        <el-button
          type="primary"
          plain
          style="margin-left: 20px;"
          :icon="Plus"
          @click="tagDialogVisible = true"
        />
      </el-form-item>
      <el-form-item
        label="文章简介"
        prop="summary"
      >
        <el-input
          v-model="articleParam.summary"
          :rows="2"
          type="textarea"
          placeholder="简介..."
        />
      </el-form-item>
      <el-form-item>
        <slot />
      </el-form-item>
    </el-form>
  </app-card>
  <!-- markdown 编辑器 -->
  <md-editor
    @get-content="getContent"
    :param="articleParam"
  />

  <app-dialog-form
    :confirm="handleAddCategory"
    v-model="categoryDialogVisible"
    @close="handleClose"
  >
    <el-form
      :model="categoryParam"
      :rules="categoryFormRules"
      ref="categoryForm"
      label-width="100px"
    >
      <el-form-item
        label="分类名"
        prop="categoryName"
      >
        <el-input
          style="width=50%"
          v-model="categoryParam.categoryName"
          placeholder="请输入分类名"
        />
      </el-form-item>
      <el-form-item label="分类描述">
        <el-input
          v-model="categoryParam.categoryDesc"
          placeholder="请输入分类描述"
        />
      </el-form-item>
    </el-form>
  </app-dialog-form>

  <app-dialog-form
    :confirm="handleAddTag"
    v-model="tagDialogVisible"
    @close="handleClose"
  >
    <el-form
      :model="tagParam"
      :rules="tagFormRules"
      ref="tagForm"
      label-width="100px"
    >
      <el-form-item
        label="标签名"
        prop="tagName"
      >
        <el-input
          style="width=50%"
          v-model="tagParam.tagName"
          placeholder="请输入标签名"
        />
      </el-form-item>
    </el-form>
  </app-dialog-form>
</template>

<script lang="ts" setup>
import { IArticleParam } from '@/api/types/article/article'
import { ICategory } from '@/api/types/article/category'
import { onMounted, PropType, reactive, ref } from 'vue'
import * as categoryApi from '@/api/category'
import * as tagApi from '@/api/tag'
import { ITag } from '@/api/types/article/tag'
import { Plus } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { IElForm } from '@/types/element-plus'

const props = defineProps({
  articleParam: {
    type: Object as PropType<IArticleParam>,
    // eslint-disable-next-line vue/require-valid-default-prop
    default: {
      id: '',
      title: '',
      summary: '',
      cover: '',
      weight: 1,
      state: 0,
      body: {
        id: '',
        content: '',
        contentHtml: '',
        articleId: '',
        catalog: ''
      },
      categoryId: '',
      tags: []
    }
  }
})

const listLoading = ref(false)
const articleParam = reactive<IArticleParam>(props.articleParam)
// const articleParam = reactive<IArticleParam>({
//   id: props.articleParam.id,
//   title: props.articleParam.title,
//   summary: props.articleParam.summary,
//   cover: props.articleParam.cover,
//   weight: props.articleParam.weight,
//   state: props.articleParam.state,
//   body: {
//     id: props.articleParam.body.id ? props.articleParam.body.id : '',
//     content: props.articleParam.body.content,
//     contentHtml: props.articleParam.body.contentHtml,
//     articleId: props.articleParam.body.articleId,
//     catalog: props.articleParam.body.catalog
//   },
//   categoryId: props.articleParam.categoryId,
//   tags: props.articleParam.tags
// })

const categoryParam = reactive<ICategory>({
  id: '',
  avatar: '',
  categoryName: '',
  categoryDesc: ''
})

const tagParam = reactive<ITag>({
  id: '',
  tagName: ''
})

const categoryList = ref<ICategory[]>([])
const tagList = ref<ITag[]>([])

const categoryDialogVisible = ref(false)
const tagDialogVisible = ref(false)

const categoryFormRules = reactive({
  categoryName: [
    { required: true, message: '请输入分类名称', trigger: 'blur' }
  ]
})
const tagFormRules = reactive({
  tagName: [
    { required: true, message: '请输入标签名称', trigger: 'blur' }
  ]
})

const categoryForm = ref<IElForm | null>(null)
const tagForm = ref<IElForm | null>(null)

onMounted(() => {
  loadCategoryList()
  loadTagList()
})

const loadCategoryList = async () => {
  const { data } = await categoryApi.getCategoryList({ key: '', page: 1, limit: 1000 })
  categoryList.value = data.list
}

const loadTagList = async () => {
  const { data } = await tagApi.getTagList({ key: '', page: 1, limit: 1000 })
  tagList.value = data.list
}

// 从编辑器获取 文本、html、目录
const getContent = (content: string, html: string, dir: any) => {
  articleParam.body.content = content
  articleParam.body.contentHtml = html
  articleParam.body.catalog = JSON.stringify(dir)
  // console.log(JSON.parse(articleParam.body.catalog))
}

const form = ref<IElForm | null>(null)
const formRules = reactive({
  title: [
    { required: true, message: '请输入文章标题', trigger: 'blur' }
  ],
  categoryId: [
    { required: true, message: '请选择商品分类', trigger: 'blur' }
  ],
  tags: [
    { required: true, message: '请选择文章标签', trigger: 'blur' }
  ],
  summary: [
    { required: true, message: '请输入文章简介', trigger: 'blur' }
  ]
})

// eslint-disable-next-line no-use-before-define
const emit = defineEmits<EmitsType>()
interface EmitsType {
  (e: 'postBlog', param: IArticleParam): void
}
const confirmLoading = ref(false)
// 发布文章
const postArticle = async () => {
  // 表单验证
  const valid = await form.value?.validate()
  if (!valid) {
    return false
  }

  confirmLoading.value = true
  emit('postBlog', articleParam)
  confirmLoading.value = false
}

// 添加分类
const handleAddCategory = async () => {
  // 表单验证
  const valid = await categoryForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await categoryApi.addCategory(categoryParam).finally(() => {
    categoryDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('添加分类成功')
    loadCategoryList()
    articleParam.categoryId = data.data.id
  }
}

// 添加标签
const handleAddTag = async () => {
  // 表单验证
  const valid = await tagForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await tagApi.addTag(tagParam).finally(() => {
    tagDialogVisible.value = false
  })

  if (data.code === 200) {
    ElMessage.success('添加标签成功')
    loadTagList()
    articleParam.tags.push(data.data.id)
  }
}

// 处理弹窗关闭事件
const handleClose = () => {
  categoryParam.id = ''
  categoryParam.avatar = ''
  categoryParam.categoryDesc = ''
  categoryParam.categoryName = ''

  tagParam.id = ''
  tagParam.tagName = ''
}

</script>

<style lang='scss' scoped>
::deep(.el-space) {
  display: flex;
  justify-content: space-between;
}
</style>
