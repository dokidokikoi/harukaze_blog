<template>
  <ul>
    <li
      v-for="item in navTitles"
      :key="item.id"
      :class="$route.path === '/' + item.path ? 'active' : ''"
      @click="toPage(item.path)"
    >
      <router-link
        :to="'/' + item.path"
        style="position: relative;"
      >
        <div class="center">
          <svg-icon
            class="icon menuIcon"
            :name="item.icon"
          />
          <span style="margin: 0 10px;">{{ item.authName }}</span>
        </div>
        <div class="bottomBar" />
      </router-link>
    </li>
  </ul>
</template>

<script lang="ts" setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const navTitles = ref([
  {
    id: 1,
    authName: '首页',
    path: '',
    icon: 'home'
  },
  {
    id: 2,
    authName: '项目',
    path: 'project',
    icon: 'project'
  },
  {
    id: 3,
    authName: '归档',
    path: 'archives',
    icon: 'archive'
  },
  {
    id: 4,
    authName: '随笔',
    path: 'note',
    icon: 'note'
  },
  {
    id: 5,
    authName: '留言板',
    path: 'message',
    icon: 'message'
  },
  {
    id: 6,
    authName: '友链',
    path: 'link',
    icon: 'link'
  },
  {
    id: 7,
    authName: '关于我',
    path: 'about',
    icon: 'user'
  }
])

const router = useRouter()
const toPage = (path: string) => {
  router.push('/' + path)
}

</script>

<style lang='scss' scoped>
ul {
  width: 50%;
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  color: aliceblue;
  justify-content: space-around;
  height: 100%;

  li {
    margin: 0;
    padding: 0;
    height: 100%;
    display: flex;
    align-items: center;
    min-width: 110px;
    overflow: hidden;

    a {
      text-decoration: none;
      color: #fff;
    }

    .menuIcon {
      transform: rotate(20deg);
      transition: all 0.2s ease-in-out;
    }

    .bottomBar {
      bottom: -27px;
      height: 7px;
      position: absolute;
      width: 100%;
      background-color: #fff;
      transition: all 0.2s ease-in-out;
      z-index: 1000;
    }

    &:hover {
      cursor: pointer;
      // background-color: #fff;

      .menuIcon {
        transform: rotate(0);
      }

      .bottomBar {
        bottom: -22px;
      }
    }
  }
}

.active {
  a {
    color: rgb(129, 245, 119);
  }

  .menuIcon {
    transform: rotate(0);
  }

  .bottomBar {
    bottom: -24px;
    background-color: rgb(129, 245, 119);
  }
}

.flex-grow {
  flex-grow: 1;
}
</style>
