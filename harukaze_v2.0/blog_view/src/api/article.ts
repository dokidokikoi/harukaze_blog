import request from '@/utils/request'
import { IArticleDetail, IArticleParam, ISearchParam } from './types/article/article'
import { IPage } from './types/common'

export const getArticleList = (data: ISearchParam) => {
  return request<IPage<IArticleDetail>>({
    method: 'POST',
    url: 'article/list',
    data
  })
}

export const getArticleDetail = (id: string) => {
  return request<IArticleDetail>({
    method: 'GET',
    url: 'article/info/' + id
  })
}

export const writeBlog = (data: IArticleParam) => {
  return request({
    method: 'POST',
    url: 'article/save',
    data
  })
}

export const updateBlog = (data: IArticleParam) => {
  return request({
    method: 'PUT',
    url: 'article/update',
    data
  })
}

export const setBlogState = (params: {id: string, flag: boolean}) => {
  return request({
    method: 'PUT',
    url: 'article/set_state',
    params
  })
}

export const fixedTop = (params: {id: string, weight: number}) => {
  return request({
    method: 'PUT',
    url: 'article/set_weight',
    params
  })
}

export const getArticleCount = () => {
  return request<number>({
    method: 'GET',
    url: 'article/count'
  })
}

export const getAllArticle = () => {
  return request<IArticleDetail[]>({
    method: 'GET',
    url: 'article/all'
  })
}
