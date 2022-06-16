<template>
  <div class="container">
    <div
      class="cover"
      :style="`background-image: url(${article?.cover})`"
    >
      <div class="mask" />
      <div class="blog_title">
        <h1>{{ article?.title }}</h1>
        <div class="content">
          <div class="avatar">
            <el-avatar
              size="large"
              :src="article?.author.avatar"
            />
          </div>
          <p
            class="entry-census"
            style="margin-top: 5px;margin-bottom: 10px;"
          >
            @{{ article?.author.nickname }}

            <span class="bull">·</span>

            {{ formatNanoDate(article?.createDate ? article?.createDate : '') }}

            <span class="bull">·</span>
            {{ article?.viewCounts }} 次阅读
            <span class="bull">·</span>
            {{ article?.commentCounts }} 评论
          </p>
          <p style="margin-top: 5px; display: flex;">
            <svg-icon
              name="folder"
              class="icon"
              style="margin-right: 5px;"
            />
            <span>
              {{ article?.category.categoryName }}
            </span>
            <svg-icon
              name="tags"
              class="icon"
              style="margin-right: 5px; margin-left: 20px;"
            />
            <span
              v-for="(item, index) in article?.tags"
              :key="item.id"
            >
              {{ item.tagName }}
              <span v-if="index < tagLen">、</span>
            </span>
          </p>
        </div>
      </div>
    </div>
    <div class="main">
      <div class="content_body">
        <div style="width: 73%;">
          <div
            class="blog_body default-theme md-scrn .md-content .md-preview"
            v-highlight
            v-html="article?.body.contentHtml"
          />
          <app-comment :article-id="$route.params.id" />
        </div>
        <div class="catalog">
          <catalog-item
            :catalog="catalog"
            :cata-high-light="cataHighLight"
            @to-title="toTitle"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import * as articleApi from '@/api/article'
import { IArticleDetail } from '@/api/types/article/article'
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import { formatNanoDate } from '@/utils/time'
import 'md-editor-v3/lib/style.css'
import { ICatalog } from './catalogItem/catalog'
import catalogItem from './catalogItem/CatalogItem.vue'

const router = useRouter()
const tagLen = ref(0)
const article = ref<IArticleDetail | null>(null)
const catalog = ref<ICatalog[]>([])
const getArticleDetail = async () => {
  articleApi.getArticleDetail(router.currentRoute.value.params.id.toString()).then((data) => {
    article.value = data.data
    if (!article.value.body.contentHtml) article.value.body.contentHtml = '<h1>哎呀！文章不见了<div>'
    tagLen.value = article.value.tags.length - 1
    const list = JSON.parse(article.value?.body.catalog)
    const stack: ICatalog[] = []
    for (const item of list) {
      if (stack.length === 0) {
        stack.push(item)
        catalog.value.push(item)
      } else {
        while (true) {
          if (stack.length === 0) {
            stack.push(item)
            catalog.value.push(item)
            break
          } else {
            const tmp = stack.pop()
            if (tmp && item.level > tmp.level) {
              if (tmp) {
                if (tmp?.children) {
                  tmp.children.push(item)
                } else {
                  tmp.children = []
                  tmp.children.push(item)
                }
                stack.push(tmp)
              }
              stack.push(item)
              break
            }
          }
        }
      }
    }
  }).catch((_err) => {
    router.go(-1)
  })
}
// eslint-disable-next-line func-call-spacing
getArticleDetail()

onMounted(() => {
  addLineNumber()
  listener()
})

const addLineNumber = () => {
  // v-html 渲染完成后，生成代码行号
  const timer = setInterval(() => {
    if ((document.querySelector('.blog_body') as HTMLElement)?.innerText) {
      clearInterval(timer)
      // console.log(document.querySelectorAll('pre code'))
      document.querySelectorAll('pre code').forEach(e => {
        const node = (e as HTMLElement)
        const lines = node.innerText.split('\n').length - 1
        const ul = document.createElement('span')
        ul.setAttribute('rn-wrapper', '')
        // ul.classList.add('pre-numbering')
        // node.classList.add('has-numbering')
        const parent = node.parentElement
        // console.log(node)
        // console.log(node.parentElement)
        parent?.append(ul)

        for (let i = 1; i <= lines; i++) {
          const li = document.createElement('span')
          ul.appendChild(li)
        }
      })
    }
  }, 100)
}

const toTitle = (id: string) => {
  const node = (document.getElementById(id) as HTMLElement)
  console.log(id)
  const main = document.querySelector('html')
  if (main) main.scrollTop += node.getBoundingClientRect().top - 60
}

const cataHighLight = ref('')
const listener = () => {
  const timer = setInterval(() => {
    if ((document.querySelector('.blog_body') as HTMLElement)?.innerText) {
      clearInterval(timer)
      window.addEventListener('scroll', () => {
        const titles = document.querySelectorAll('h1[id], h2[id], h3[id], h4[id], h5[id]')
        for (const title of titles) {
          if (title.getBoundingClientRect().top <= 100) {
            console.log(title.getAttribute('id'))
            cataHighLight.value = title.getAttribute('id')
          }
        }
      }, true)
    }
  }, 100)
}

</script>

<style lang="scss">
// v-html 渲染内容，获取不到scope的css
.default-theme pre code.hljs {
  line-height: 1.6;
}

.default-theme pre span[rn-wrapper] {
  top: 45px;
  line-height: 1.6;
}
</style>

<style lang='scss' scoped>
.container {
  font-family: 'Noto Serif SC','Source Han Serif SC','Source Han Serif','source-han-serif-sc','PT Serif','SongTi SC','MicroSoft Yahei',Georgia,serif;
  margin: auto;
}

  .cover {
    height: 400px;
    width: 100%;
    margin-bottom: 10px;
    position: relative;
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center center;
    background-origin: border-box;
    top: -60px;

    .mask {
      background-color: rgba(0, 0, 0, 0.3);
      position: absolute;
      height: 100%;
      width: 100%;
    }

    .blog_title {
      // background-color: #fff;
      padding: 10px 0;
      margin-bottom: 10px;
      position: absolute;
      bottom: 0;
      color: #fff;
      height: 40%;
      max-width: 1220px;
      margin: auto;
      left: 0;
      right: 0;
      // box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);

      h1 {
        // padding-left: 40px;
        padding-bottom: 20px;
        margin-bottom: 10px;
        border-bottom: solid 2px rgb(193, 191, 191);
        font-size: 2em;
      }

      .avatar {
        position: absolute;
        // top: 50%;
        // transform: translateY(-50%);
        left: 0;
        .el-avatar {
          width: 50px;
          height: 50px;
        }
      }

      .content {
        margin-left: 70px;
        .meta {
          display: flex;
          justify-content: space-between;
          width: 270px;
          font-style: italic;
          font-size: 0.9em;
          color: #fff;
        }
      }
    }
  }

.main {
  max-width: 1220px;
  margin: auto;
  position: relative;
  top: -60px;

  .content_body {
    display: flex;
    justify-content: space-between;
    align-items: flex-start;
    margin-bottom: 20px;

    .blog_body {
      // background-color: #fff;
      max-width: 876px;
      overflow: hidden;
      padding: 0 20px;
      margin-bottom: 20px;
      // box-shadow: 0 2px 12px 0 rgb(0 0 0 / 10%);
    }
  }
}

.catalog {
  position: sticky;
  box-sizing: border-box;
  overflow-y: auto;
  top: 60px;
  left: 0;
}
</style>
