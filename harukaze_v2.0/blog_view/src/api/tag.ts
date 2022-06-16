import request from '@/utils/request'
import { ITag, ITagData } from './types/article/tag'
import { ICommonParam, IPage } from './types/common'

export const getTagList = (params: ICommonParam) => {
  return request<IPage<ITag>>({
    method: 'GET',
    url: 'tag/list',
    params
  })
}

export const addTag = (data: ITag) => {
  return request({
    method: 'POST',
    url: 'tag/save',
    data
  })
}

export const editTag = (data: ITag) => {
  return request({
    method: 'PUT',
    url: 'tag/update',
    data
  })
}

export const delTag = (id: string) => {
  return request({
    method: 'DELETE',
    url: 'tag/delete/' + id
  })
}

export const getTagData = () => {
  return request<ITagData[]>({
    method: 'GET',
    url: 'tag/articles'
  })
}

export const getTagCount = () => {
  return request<number>({
    method: 'GET',
    url: 'tag/count'
  })
}
