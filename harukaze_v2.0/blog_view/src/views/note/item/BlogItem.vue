<template>
  <ul class="blog_list">
    <template
      v-for="item in articleList"
      :key="item.id"
    >
      <li
        v-if="item.state===0"
        class="note"
      >
        <router-link
          :to="`/blog/${item.id}`"
        >
          <div
            class="img"
            :style="`background-image: url(${item.cover})`"
          />
          <div class="mask" />
          <h2>{{ item.title }}</h2>

          <div class="note_content">
            <p class="describe">
              {{ item.summary }}
            </p>
            <div class="attach">
              <el-icon><Clock /></el-icon>
              <span style="font-size: 13px; margin-right: 10px">
                {{ formatNanoDate(item.createDate) }}
              </span>
              <el-icon><View /></el-icon>
              <span style="font-size: 13px; margin-right: 10px">
                {{ item.viewCounts }} 浏览
              </span>
              <el-icon style="margin-left: 10px;">
                <ChatDotRound />
              </el-icon>
              <span style="font-size: 13px; margin-right: 10px">
                {{ item.commentCounts }}
              </span>
            </div>
          </div>
        </router-link>
      </li>
    </template>
  </ul>
</template>

<script lang="ts" setup>
import { IArticleDetail } from '@/api/types/article/article'
import { PropType } from 'vue'
import { formatNanoDate } from '@/utils/time'

defineProps({
  articleList: Object as PropType<IArticleDetail[]>
})

</script>

<style lang='scss' scoped>
.blog_list {
  list-style: none;
  margin: 0;
  padding: 0;
  .note {
    width: 100%;
    height: 300px;
    color: white;
    border: none;
    position: relative;
    border-radius: 4px;
    margin: 20px 0;
    overflow: hidden;

    .img {
      width: 100%;
      height: 100%;
      position: absolute;
      background-repeat: no-repeat;
      background-position: center;
      background-size: cover;
      transform: scale(1);
      transition: all 0.35s ease-in-out;
    }

    a {
      color: #fff;
      text-decoration: none;
    }

    .mask {
      position: absolute;
      top: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, .2);
      z-index: 0;
    }

    h2 {
      position: absolute;
      top: 40%;
      text-align: center;
      left: 0;
      right: 0;
      margin: 0 30px;
      transform: translateY(0);
      transition: all 0.35s ease-in-out;
    }

    .note_content {
      position: absolute;
      opacity: 0;
      color: white;
      display: flex;
      flex-direction: column;
      bottom: 20px;
      width: 100%;

      .describe {
        text-align: left;
        padding-bottom: 10px;
        padding-left: 20px;
        border-bottom: solid 1px white;
        font-size: 0.9em;
      }

      .attach {
        display: flex;
        align-items: center;
        padding-left: 20px;

        i {
          margin-right: 3px;
          font-size: 1em;
        }
      }
    }
  }
}

.note:hover {
  cursor: pointer;

  .note {
    opacity: 0.5;
  }

  .note_content, h2 {
    transition: all 0.35s ease-in-out;
  }

  h2 {
    transform: translateY(10px);
    z-index: 10;
  }

  .note_content {
    opacity: 1;
    transform: translateY(-10px);
    z-index: 10;
    transition: all 0.35s ease-in-out;
  }

  .img {
    transform: scale(1.1);
    transition: all 0.35s ease-in-out;
  }

  .mask {
    background-color: rgba(2, 2, 2, .5);
  }
}
</style>
