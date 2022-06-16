<template>
  <el-form
    :model="commentParam"
    :rules="rules"
    label-position="top"
    ref="commentForm"
  >
    <el-form-item
      label="评论"
      prop="content"
    >
      <el-input
        type="textarea"
        v-model="commentParam.content"
        :autosize="{ minRows: 4, maxRows: 4}"
        placeholder="不写点什么吗..."
      />
    </el-form-item>
    <el-form-item
      label="昵称"
      prop="author.name"
      style="width: 45%"
    >
      <el-input
        placeholder="姓名或昵称"
        v-model="commentParam.author.name"
      />
    </el-form-item>
    <el-form-item>
      <el-button
        type="success"
        style="width: 100px"
        @click="recover"
        :loading="isLoad"
      >
        点击发表
      </el-button>
      <slot />
    </el-form-item>
  </el-form>
</template>

<script lang="ts" setup>
import { ICommentParam } from '@/api/types/comment'
import { PropType, reactive, ref } from 'vue'
import * as commentApi from '@/api/comment'
import { ElMessage } from 'element-plus'
import { IElForm } from '@/types/element-plus'

const props = defineProps({
  parentId: {
    type: String,
    default: ''
  },
  articleId: {
    type: String,
    default: ''
  },
  toUserName: {
    type: String,
    default: ''
  },
  toUseId: {
    type: String,
    default: ''
  },
  loadList: {
    type: Function as PropType<(...args: any[]) => void>,
    default: () => {}
  },
  cancel: {
    type: Function as PropType<(...args: any[]) => void>,
    default: () => {}
  }
})

const commentParam = reactive<ICommentParam>({
  content: '',
  articleId: '',
  author: {
    id: '',
    name: ''
  },
  parentId: '',
  toUser: {
    id: '',
    name: ''
  }
})

const init = () => {
  commentParam.parentId = props.parentId
  commentParam.articleId = props.articleId
  commentParam.toUser.name = props.toUserName
  commentParam.toUser.id = props.toUseId ? props.toUseId : ''
}
init()

const rules = reactive({
  content: [
    { required: true, message: '居然什么都不写...', trigger: 'blur' }
  ],
  'author.name': [
    { required: true, message: '无名氏吗...', trigger: 'blur' }
  ]
})

const isLoad = ref(false)
const commentForm = ref<IElForm | null>(null)
const recover = async () => {
  isLoad.value = true
  // 表单验证
  const valid = await commentForm.value?.validate()
  if (!valid) {
    return false
  }

  const data = await commentApi.postComment(commentParam).finally(() => {
    isLoad.value = false
  })

  if (data.code === 200) {
    ElMessage.success('发布成功')
    commentParam.content = ''
    commentParam.author.name = ''
    props.cancel()
    props.loadList()
  }
}

</script>

<style lang='scss' scoped>

</style>
