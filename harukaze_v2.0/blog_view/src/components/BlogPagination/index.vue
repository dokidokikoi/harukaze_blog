<template>
  <div class="pagination">
    <span @click="previous">
      Previous
    </span>
  </div>
</template>

<script lang="ts" setup>
import { PropType } from 'vue'

const props = defineProps({
  page: { // 页码
    type: Number,
    default: 1
  },
  loadList: {
    type: Function as PropType<(...args: any[]) => void>,
    default: () => {}
  }
})

interface EmitsType {
  (e: 'update:page', page: number): void
}
const emit = defineEmits<EmitsType>()

const previous = () => {
  emit('update:page', props.page + 1)
  console.log(props.page)
  props.loadList()
}

</script>

<style lang='scss' scoped>
.pagination {
  margin-top: 30px;
  display: flex;
  justify-content: center;

  span {
    border: solid 1px #bdbdbd;
    border-radius: 25px;
    padding: 10px 20px;
    text-align: center;
    color: #a3a1a1;
    font-size: 1.2em;
    transition: all 0.3s;

    &:hover {
      cursor: pointer;
      border-color: #FE9600;
      color: #FE9600;;
      box-shadow: 0px 0px 4px #FE9600;
    }
  }
}
</style>
