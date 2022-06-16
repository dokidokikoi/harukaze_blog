<template>
  <div class="home-container">
    <app-banner
      :title="'留言'"
      :img="'src/assets/messageBannar.png'"
    />

    <div class="main">
      <app-comment />
    </div>
  </div>
</template>

<script lang="ts" setup>

</script>

<style lang='scss' scoped>
.main {
  max-width: 800px;
  margin: auto;
}
</style>
