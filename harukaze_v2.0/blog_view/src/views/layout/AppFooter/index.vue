<template>
  <div class="container">
    <div>
      <p style="margin-top: 0">
        &copy; {{ new Date().getFullYear() }} Powered by HaruKaze
      </p>
      <p>
        互联网ICP备案：<a href="https://beian.miit.gov.cn/">湘ICP备2021019471号-1</a>
      </p>
      <p class="time">
        本站已安全运行{{ time }}
      </p>
    </div>
  </div>
</template>

<script lang="ts" setup>

</script>

<style lang='scss' scoped>
.container {
  font-size: 14px;
  padding: 10px;
  margin: auto;
  text-align: center;
}

div p a {
  text-decoration: none;
  color: salmon;

  &:hover {
    color: orange;
  }
}

p {
  font-size: .8em;
}

.time {
  font-size: 1.2em;
}
</style>
