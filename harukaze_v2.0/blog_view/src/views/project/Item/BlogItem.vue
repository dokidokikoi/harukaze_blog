<template>
  <ul class="blog_list">
    <template
      v-for="item in articleList"
      :key="item.id"
    >
      <li
        v-if="item.state===0"
        class="blog_item"
      >
        <router-link
          :to="`/blog/${item.id}`"
          class="blog"
        >
          <div class="cover">
            <img
              style="width: 100%; height: 100%;"
              :src="item.cover"
            >
          </div>
          <div class="content font">
            <span class="font meta">
              <el-icon><Clock /></el-icon>
              {{ formatNanoDate(item.createDate) }}
            </span>
            <h3
              class="title"
            >
              {{ item.title }}
            </h3>
            <p
              class="meta"
              style="margin-right: 10px;"
            >
              <el-icon><View /></el-icon>
              {{ item.viewCounts }} 浏览
              <el-icon style="margin-left: 10px;">
                <ChatDotRound />
              </el-icon>
              {{ item.commentCounts }} 评论
            </p>
            <p style="text-align: left;">
              {{ item.summary }}
            </p>
            <p class="meta footer">
              <svg-icon
                name="category"
                class="icon"
                style="margin-right: 5px;"
              />
              {{ item.category.categoryName }}
              <svg-icon
                name="tag"
                class="icon"
                style="margin-left: 10px; margin-right: 5px;"
              />
              <span
                v-for="(tag, index) in item?.tags"
                :key="tag.id"
              >
                {{ tag.tagName }}
                <span v-if="index < item.tags.length-1">、</span>
              </span>
            </p>
            <div class="avatar">
            <!-- <el-avatar
              size="large"
              :src="item.author.avatar"
            />
            <span style="font-size:13px; margin-left:7px; margin-bottom:5px;font-style: italic;">@{{ item.author.nickname }}</span> -->
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
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;

  li {
    margin: 0;
    padding: 0;
    margin-bottom: 20px;
    border: none;
    height: 300px;

    a {
      text-decoration: none;
      color: #000;
    }
  }

  .blog {
    box-sizing: border-box;
    height: 100%;
    background-color: #fff;
    border: 1px solid #EBEEF5;
    box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    border-radius: 10px;
    display: flex;
    position: relative;
    overflow: hidden;
    border: none;

    .cover {
      width: 55%;
      height: 100%;
      overflow: hidden;

      img {
        object-fit: cover;
        transition: all .6s;
      }
    }

    .content {
      box-sizing: border-box;
      padding: 10px 20px;
      width: 45%;
      position: relative;

      .avatar {
        display: flex;
        align-items: flex-end;
      }

      h3:hover {
        color: red;
      }
    }

    .content::after {
      content: '';
      position: absolute;
      top: 0;
      right: 0;
      background-color: pink;
      width: 4px;
      height: 0px;
      border-radius: 2px;
      transition: .5s;
    }
  }
}

.icon {
  width: 17px;
  height: 17px;
}

.meta {
  color: #888;
  font-size: 15px;
}

.font {
  text-align: right;
}

.footer {
  position: absolute;
  bottom: 0;
}

.blog:hover {
  cursor: pointer;
  box-shadow: 0 2px 12px 0 rgb(0 0 0 / 20%);

  .content::after {
    height: 100%;
    box-shadow: #ffc0cb 0px 0px 6px;
  }

  .cover img {
    transform: scale(1.2) rotate(5deg);
  }
}

.blog_list li:nth-child(even) {
  .blog .cover {
    order: 2;
  }

  .font {
    text-align: left;
  }

  .blog .content::after {
    left: 0px;
  }
}
</style>
