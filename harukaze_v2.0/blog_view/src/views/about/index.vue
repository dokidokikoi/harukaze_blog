<template>
  <div class="home-container">
    <app-banner
      :title="'关于'"
      :img="'src/assets/aboutBannar.png'"
    />
  </div>

  <div class="main">
    <h1>站点概述</h1>
    <article>
      <p>
        本站点于2022年01月01日建成，在2022年6月初进行2.0版本的升级。
        不过由于站长不是前端这块料，这个个人博客的前端做起来要比后端艰难许多。但是好在站长比较爱折腾，以后会对页面继续美化，希望点进来的你能够收藏，让我们一起见证。
      </p>
    </article>
    <h1>待实现</h1>
    <ul>
      <li>
        使用更加美观的字体
      </li>
      <li>
        主题切换
      </li>
      <li>
        更加美观的页面
      </li>
      <li>
        视频播放
      </li>
      <li>
        live2d看板娘
      </li>
      <li>
        .......
      </li>
    </ul>
    <h1>我</h1>
    <article>
      <p>
        你好呀！这里是HaruKaze，一个个人博客站点的站长，正在成为程序员。本站点用来分享我的一些学习笔记、编程技术和随笔。欢迎多来看看，说不定下次访问时就大变样了。
      </p>
    </article>
  </div>
</template>

<script lang="ts" setup>

</script>

<style lang='scss' scoped>
.main {
  max-width: 800px;
  margin: auto;

  h1 {
    margin-top: 50px;
    position: relative;

    &::before {
      content: '';
      left: -10px;
      position: absolute;
      height: 100%;
      width: 5px;
      background-color: pink;
    }
  }

  article {

    p {
      text-indent: 2em;
      line-height: 1.6em;
      color: #606060;
    }
  }

  ul {
    li {
      padding-top: 10px;
    }
  }
}
</style>
