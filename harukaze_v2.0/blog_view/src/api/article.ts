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

export const updateBlog = (params: IArticleParam) => {
  return request({
    method: 'PUT',
    url: 'article/update',
    params
  })
}

export const setBlogState = (data: {id: string, flag: boolean}) => {
  return request({
    method: 'PUT',
    url: 'article/set_state',
    data
  })
}
