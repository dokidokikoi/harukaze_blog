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

export const addTag = (params: ITag) => {
  return request({
    method: 'POST',
    url: 'tag/save',
    params
  })
}

export const editTag = (params: ITag) => {
  return request({
    method: 'PUT',
    url: 'tag/update',
    params
  })
}

export const delTag = (id: string) => {
  return request({
    method: 'PUT',
    url: 'tag/delete/' + id
  })
}

export const getTagData = () => {
  return request<ITagData[]>({
    method: 'GET',
    url: 'tag/articles'
  })
}
