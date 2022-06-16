<template>
  <div class="banner">
    <div
      class="bg"
      :style="`background-image: url(${img});`"
    >
      <h1 class="title">
        {{ title }}
      </h1>
      <div class="mask" />
    </div>
  </div>
</template>

<script lang="ts" setup>
defineProps({
  title: String,
  img: String
})

</script>

<style lang='scss' scoped>
.banner {
  height: 60vh;
  position: relative;
  .bg {
    position: absolute;
    top: -60px;
    // bottom: 0;
    width: 100%;
    height: 110%;
    background: url('@/assets/projectBannar.png');
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    background-origin: border-box;

    display: flex;
    justify-content: center;
    align-items: center;
    color: aliceblue;

    .title {
      margin-top: 100px;
      font-size: 2.5em;
      z-index: 1;
    }
  }
  .mask {
    background-color: rgba(0, 0, 0, 0.3);
    top: 0;
    position: absolute;
    height: 100%;
    width: 100%;
  }
}
</style>
