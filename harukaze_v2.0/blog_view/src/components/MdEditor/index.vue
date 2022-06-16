<template>
  <md-editor
    v-model="state.text"
    @on-upload-img="onUploadImg"
    @on-get-catalog="onGetCatalog"
    @on-html-changed="onHtmlChanged"
    @on-save="onSave"
    @on-change="handleChange"
    :show-code-row-number="true"
    height="800px"
    style="height: 600px"
  />
</template>

<script lang="ts" setup>
import { onMounted, reactive } from 'vue'
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { setItem } from '@/utils/storage'
import { useStore } from '@/store'
import { ElMessage } from 'element-plus'
import { policy } from '@/api/common'
import axios from 'axios'
import { ossUrl } from '@/utils/constants'
import { getUUID } from '@/utils'

const props = defineProps({
  param: Object
})

const dataObj = reactive({
  policy: '',
  signature: '',
  key: '',
  ossaccessKeyId: '',
  dir: '',
  host: '',
  expire: '',
  file: ''
  // callback:'',
})

const store = useStore()

const state = reactive({
  text: '',
  html: '',
  catalogList: []
})

onMounted(() => {
  state.text = props.param?.body?.content
})

const onUploadImg = async (files: any[], callback: (arg0: any[]) => void) => {
  const { data } = await policy()
  dataObj.policy = data.policy
  dataObj.signature = data.signature
  dataObj.ossaccessKeyId = data.accessid
  // eslint-disable-next-line no-template-curly-in-string
  dataObj.key = data.dir + getUUID() + '_${filename}'
  dataObj.dir = data.dir
  dataObj.host = data.host
  dataObj.expire = data.expire
  dataObj.file = files[0]
  // console.log('响应的数据222。。。', dataObj)
  axios({
    method: 'POST',
    url: ossUrl,
    data: dataObj,
    headers: {
      'Content-Type': 'multipart/form-data'
    }
  })
  // eslint-disable-next-line n/no-callback-literal
  callback([`${dataObj.host}/${dataObj.key.replace('${filename}', files[0].name)}`])
}

const onGetCatalog = (list: []) => {
  state.catalogList = list
}

const onHtmlChanged = (h: string) => {
  state.html = h
}

const onSave = (h: string) => {
  setItem(store.state.articleType, props.param)
  ElMessage.success('保存成功')
}

interface EmitsType {
  (e: 'getContent', content: string, html: string, catalog: any): void
}

const emit = defineEmits<EmitsType>()
const handleChange = () => {
  emit('getContent', state.text, state.html, state.catalogList)
}

</script>

<style lang='scss' scoped>

</style>
