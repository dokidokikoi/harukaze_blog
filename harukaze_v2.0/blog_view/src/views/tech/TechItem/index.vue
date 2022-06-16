<template>
  <tr
    class="blog"
    v-if="article?.state===0"
  >
    <div class="avatar">
      <el-avatar
        size="large"
        :src="article?.author.avatar"
      />
    </div>
    <div class="content first">
      <div class="meta">
        <span style="font-size:13px">@{{ article?.author.nickname }}</span>
        <div
          class="blog_date"
          style="font-size:13px"
        >
          <el-icon style="margin-right: 2px;">
            <Clock />
          </el-icon>
          <span>{{ formatDate(article?.createDate) }}</span>
        </div>
      </div>
      <router-link
        :to="`/blog/${article?.id}`"
        class="title"
      >
        {{ article?.title }}
      </router-link>
      <p>{{ article?.summary }}</p>
      <router-link
        :to="`/blog/${article?.id}`"
        class="img"
        :style="`background-image: url(${article?.cover})`"
      />
      <div class="blog_footer">
        <el-icon :size="15">
          <View />
        </el-icon>
        <span>{{ article?.viewCounts }}</span>
        <el-icon :size="15">
          <ChatDotRound />
        </el-icon>
        <span>{{ article?.commentCounts }}</span>
      </div>
    </div>
  </tr>
</template>

<script lang="ts" setup>
import { formatDate } from '@/utils/time'

defineProps({
  article: {
    type: Object
  }
})
</script>

<style lang='scss' scoped>
.blog {
  padding: 10px 30px;
  width: 100%;
  transform: translateX(5px);
  overflow: visible;

  .avatar {
    position: absolute;
    top: 8px;
    left: -18px;
    width: 20px;
  }

  div.content {
    box-sizing: border-box;
    width: 92%;
    margin-left: 48px;
    margin-bottom: 20px;
    padding-top: 10px;
    padding-right: 20px;
    border-top: solid 1px rgb(198, 197, 197);

    .meta {
      .blog_date {
        display: inline-block;
        margin-left: 15px;
      }

      span {
        font-style: italic;
        font: 0.1em;
      }
    }

    .title {
      text-decoration: none;
      font-size: 1.6em;
      color: black;
      line-height: 40px;
      word-wrap:break-word;
      margin-bottom: 30px;
      font-weight: bold;
    }

    .title:hover {
      color: red;
    }
  }

  .img {
    display: block;
    height: 260px;
    min-width: 600px;
    background-position: center;
    background-size: cover;
    transform: scale(1);
    transition: all 0.35s ease-in-out;
    margin-bottom: 10px;
    box-shadow: 0px 10px 15px #2416573d;
    border-radius: 5px;
  }

  .img:hover {
    transform: scale(1.1);
    transition: all 0.35s ease-in-out;
    cursor: pointer;
  }
}

.blog_footer {
  display: flex;
  align-items: center;
}

.blog_footer span {
    font-size: 10px;
    margin-left: 5px !important;
}

.blog_footer i{
    margin-left: 15px;
}
</style>
