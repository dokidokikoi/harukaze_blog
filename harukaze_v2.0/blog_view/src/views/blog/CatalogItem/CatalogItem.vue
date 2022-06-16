<template>
  <ol>
    <li
      v-for="(item, index) in catalog"
      :key="index"
    >
      <span
        @click="handle(item.text)"
        :class="cataHighLight === item.text ? 'active' : ''"
      >{{ item.text }}</span>
      <catalog-item
        v-if="item.children"
        :catalog="item.children"
        :cata-high-light="cataHighLight"
        @to-title="handle"
      />
    </li>
  </ol>
</template>

<script lang="ts" setup>
import { PropType } from 'vue'
import { ICatalog } from './catalog'

defineProps({
  catalog: Object as PropType<ICatalog[]>,
  cataHighLight: String
})

interface EmitsType {
  (e: 'toTitle', id: string): void
}
const emit = defineEmits<EmitsType>()

const handle = (id: string) => {
  emit('toTitle', id)
}
</script>

<style lang='scss' scoped>
ol {
  list-style: none;
  padding-left: 20px;
}

ol li:hover {
  cursor: pointer;
}

.active {
  color: purple
}

</style>
