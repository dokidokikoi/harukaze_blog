<template>
  <div
    class="top"
    @click="Gotop"
  />
</template>

<script lang="ts" setup>

const Gotop = () => {
  const scroll = document.querySelector('html')
  scroll.scrollTop = 0
}

</script>

<style lang='scss' scoped>
.top {
  height: 270px;
  width: 250px;
  position: fixed;
  bottom: 0px;
  right: 40px;
  border-start-start-radius: 190px;
  border-start-end-radius: 190px;
  background-color: aqua;
  cursor: pointer;
  text-align: center;
  background: url("@/assets/shiroha.png") no-repeat;
  background-position-y: 10px;
  background-position-x: -40px;
  background-size: 130%;
  z-index: 1000;
}

.top:hover {
  background-image: url("@/assets/shiroha_syouku.png");
  background-repeat: no-repeat;
  background-position: center;
  background-size: cover;
}
</style>
