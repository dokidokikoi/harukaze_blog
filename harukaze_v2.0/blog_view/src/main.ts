import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { store, key } from './store'
import './styles/index.scss'
import elementPlus from './plugins/element-plus'
import hljs from 'highlight.js'
import 'highlight.js/styles/monokai-sublime.css'

const app = createApp(App)
app.use(store, key)
  .use(router)
  .use(elementPlus)
// 自定义一个代码高亮指令
app.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach((block: any) => {
    hljs.highlightBlock(block)
  })
})

// 自动注册全局组件
const modules = import.meta.globEager('./components/**/index.ts')
for (const path in modules) {
  app.use(modules[path].default)
}

app.mount('#app')
