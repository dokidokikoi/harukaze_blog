<template>
  <el-upload
    class="avatar-uploader"
    :action="ossUrl"
    list-type="picture"
    :data="dataObj"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
  >
    <img
      v-if="imageUrl"
      :src="imageUrl"
      class="avatar"
      :style="`width: ${appWidth}px; height: ${appHeight}px;`"
    >
    <el-icon
      v-else
      class="avatar-uploader-icon"
      :style="`width: ${appWidth}px; height: ${appHeight}px;`"
    >
      <Plus />
    </el-icon>
  </el-upload>
</template>

<script lang="ts" setup>
import { watch, reactive, ref, watchEffect } from 'vue'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import { policy } from '@/api/common'
import type { UploadProps } from 'element-plus'
import { getUUID } from '@/utils'
import { ossUrl } from '@/utils/constants'

const imageUrl = ref('')
const dataObj = reactive({
  policy: '',
  signature: '',
  key: '',
  ossaccessKeyId: '',
  dir: '',
  host: '',
  expire: ''
  // callback:'',
})

const props = defineProps({
  img: {
    type: String,
    default: ''
  },
  appWidth: {
    type: String,
    default: '600'
  },
  appHeight: {
    type: String,
    default: '300'
  }
})

// eslint-disable-next-line vue/no-setup-props-destructure
watch(
  () => props.img,
  (newProps) => {
    imageUrl.value = newProps
    console.log(imageUrl.value)
  }, { immediate: true }
)

interface EmitsType {
  (e: 'getImg', url: string): void
}
const emit = defineEmits<EmitsType>()
const handleAvatarSuccess: UploadProps['onSuccess'] = (
  response,
  uploadFile
) => {
  // imageUrl.value = URL.createObjectURL(uploadFile.raw!)
  imageUrl.value = `${dataObj.host}/${dataObj.key.replace('${filename}', uploadFile.name)}`
  emit('getImg', imageUrl.value)
  ElMessage.success('上传成功')
}

const beforeAvatarUpload: UploadProps['beforeUpload'] = (rawFile) => {
  if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!')
    return false
  }

  return new Promise((resolve, reject) => {
    policy().then(response => {
      // console.log(response)
      // console.log('响应的数据', response)
      dataObj.policy = response.data.policy
      dataObj.signature = response.data.signature
      dataObj.ossaccessKeyId = response.data.accessid
      // eslint-disable-next-line no-template-curly-in-string
      dataObj.key = response.data.dir + getUUID() + '_${filename}'
      dataObj.dir = response.data.dir
      dataObj.host = response.data.host
      dataObj.expire = response.data.expire
      // console.log('响应的数据222。。。', dataObj)
      resolve(true)
    }).catch(_err => {
      // console.log('failed')
      // eslint-disable-next-line prefer-promise-reject-errors
      reject(false)
    })
  })
}

</script>

<style scoped>
.avatar-uploader .avatar {
  display: block;
  object-fit: cover;
}
</style>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  text-align: center;
}
</style>
