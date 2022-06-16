<template>
  <div class="search">
    <div
      class="icon"
      @click="showInput"
    />
    <div class="input">
      <input
        type="text"
        v-model="query"
        placeholder="Search"
        autocomplete="off"
        @keydown.enter="search"
      >
    </div>
    <span
      class="clear"
      @click="clear"
    />
  </div>
</template>

<script lang="ts" setup>
import { useRouter } from 'vue-router'
import { ref, nextTick } from 'vue'
const router = useRouter()
const query = ref('')

const showInput = () => {
  nextTick(() => {
    const node = document.querySelector('.search')
    if (node) {
      node.classList.toggle('active')
    }
  })
}

const search = () => {
  router.push({
    path: '/search',
    query: {
      query: query.value
    }
  })
}

const clear = () => {
  query.value = ''
}
</script>

<style lang="scss" scoped>
$width: 30px;
$height: 30px;

$iconWidth: 10px;
$iconHeight: 10px;

.search {
  position: relative;
  width: $width;
  height: $height;
  background-color: #fff;
  border-radius: 60px;
  transition: 0.5s;
  box-shadow: 0 0 0 2px #2573ef;
  overflow: hidden;
}

.search .icon {
    position: absolute;
    top: 0;
    left: 0;
    width: $width;
    height: $height;
    background: #fff;
    border-radius: 60px;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
    cursor: pointer;
    margin: 0;
}

.search .icon::before {
    content: '';
    position: absolute;
    width: $iconWidth;
    height: $iconHeight;
    border: 3px solid #287dfc;
    border-radius: 50%;
    transform: translate(-2px, -2px)
}

.search .icon::after {
    content: '';
    position: absolute;
    width: 3px;
    height: 8px;
    background-color: #287dfc;
    transform: translate(6px, 6px) rotate(315deg);
}

.search.active {
  width: 280px;

  .clear {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    width: 12px;
    height: 12px;
    right: 15px;
    border-radius: 50%;
    border: rgb(156, 156, 156) solid 1px;
    cursor: pointer;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .clear::before {
    position: absolute;
    content: '';
    width: 1px;
    height: 8px;
    background-color: rgb(196, 194, 194);
    transform: rotate(45deg);
  }

  .clear::after {
    position: absolute;
    content: '';
    width: 1px;
    height: 8px;
    background-color: rgb(196, 194, 194);
    transform: rotate(315deg);
  }
}

.search .input {
  position: relative;
  width: 210px;
  height: $height;
  left: 30px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.search .input input {
  position: absolute;
  top: 0;
  width: 100%;
  height: 100%;
  border: none;
  outline: none;
  font-size: 16px;
  padding: 10px 0;
  box-sizing: border-box;
}

</style>
