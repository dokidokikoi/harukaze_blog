import { ICategory } from './category'
import { ITag } from './tag'
import { IUser } from '../user'

export interface IArticleBody {
  id: string
  content: string
  contentHtml: string
  articleId: string
}

export interface IArticleDetail {
  id: string
  title: string
  summary: string
  cover: string
  viewCounts: number
  commentCounts: number
  weight: number
  state: number
  createDate: string
  updateDate: string
  body: IArticleBody
  author: IUser
  category: ICategory
  tags: ITag[]
}

export interface IArticleParam {
  id: string
  title: string
  summary: string
  cover: string
  weight: number
  state: number
  body: IArticleBody
  category: ICategory
  tags: ITag[]
}

export interface ISearchParam {
  key: string
  categoryId: string
  tags: string[]
  limit: number
  page: number
  sort: {
    time: string
    view: string
    comment: string
  }
}
