import request from '@/utils/request'
import { ICategory, ICategoryData } from './types/article/category'
import { ICommonParam, IPage } from './types/common'

export const getCategoryList = (params: ICommonParam) => {
  return request<IPage<ICategory>>({
    method: 'GET',
    url: 'category/list',
    params
  })
}

export const addCategory = (params: ICategory) => {
  return request({
    method: 'POST',
    url: 'category/save',
    params
  })
}

export const editCategory = (params: ICategory) => {
  return request({
    method: 'PUT',
    url: 'category/update',
    params
  })
}

export const deleteCategory = (id: string) => {
  return request({
    method: 'DELETE',
    url: 'category/delete/' + id
  })
}

export const getCategoryData = () => {
  return request<ICategoryData[]>({
    method: 'GET',
    url: 'category/article'
  })
}
