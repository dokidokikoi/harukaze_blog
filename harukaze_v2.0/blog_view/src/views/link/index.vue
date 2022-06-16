<template>
  <div class="home-container">
    <app-banner
      :title="'友链'"
      :img="'src/assets/linkBannar.png'"
    />
  </div>

  <div class="main">
    <div class="guide">
      <h3>欢迎交换友链</h3>
      <span>请先将本站添加到贵站的友链中，</span>
      <span>并在<router-link to="/message">留言板</router-link>或发送<a href="mailto:harukaze_doki@163.com">邮件(推荐)</a>告诉我你的: </span>
      <ol>
        <li>站点名称</li>
        <li>站点介绍 (尽量精简到一句话)</li>
        <li>主页地址 (https)</li>
        <li>头像地址 (https)</li>
      </ol>
      <span>※</span>

      <span>格式如下:</span>
      <ul>
        <li>Name: harukaze</li>
        <li>desc: </li>
        <li>URL: https://harukaze.top</li>
        <li>Avatar: ...</li>
      </ul>
    </div>

    <h1 class="title">
      友链列表
    </h1>
    <ul class="links">
      <li
        class="link_content"
        :title="item.siteDesc"
        v-for="item in linkList"
        :key="item.id"
      >
        <img
          class="avatar"
          :src="item.avatar"
          alt="no"
        >
        <div class="detail">
          <p>
            {{ item.name }}
          </p>
          <div style="width: 90%; border-bottom: dashed 1px black; margin: 5px;" />
          <p>
            {{ item.siteDesc }}
          </p>
        </div>
      </li>
    </ul>
  </div>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { getLinkList } from '@/api/friendLink'
import { IFriendLink } from '@/api/types/friendLink'
import { useStore } from '@/store'

const store = useStore()

const linkList = ref<IFriendLink[]>([])
const loadLinkList = async () => {
  store.commit('loading', true)
  const { data } = await getLinkList({
    key: '',
    page: 1,
    limit: 100
  }).finally(() => {
    store.commit('loading', false)
  })

  linkList.value = data.list
}
loadLinkList()

</script>

<style lang='scss' scoped>
.main {
  max-width: 800px;
  margin: auto;

  .guide {
    a {
      text-decoration: none;
      color: orange;
      font-weight: 600;
    }
  }

  .title {
    position: relative;
  }

  .title::before {
    content: '';
    display: block;
    background-color: rgb(244, 192, 94);
    left: -10px;
    height: 1.5em;
    width: 5px;
    position: absolute;
  }

  .links {
    list-style: none;
    margin: 0;
    padding: 0;
    display: flex;
    flex-wrap: wrap;

    .link_content {
      width: 30%;
      height: 80px;
      margin: 10px;
      background-color: aliceblue;
      display: flex;
      align-items: center;
      border-radius: 10px;

      .avatar {
        width: 70px;
        height: 70px;
        border-radius: 50%;
        border: solid bisque 2px;
        margin-left: 10px;
      }

      .detail {
        width: 70%;

        p {
          margin: 5px 10px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
      }

      &:hover {
        cursor: pointer;
      }
    }
  }
}

</style>
