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

export const addCategory = (data: ICategory) => {
  return request({
    method: 'POST',
    url: 'category/save',
    data
  })
}

export const editCategory = (data: ICategory) => {
  return request({
    method: 'PUT',
    url: 'category/update',
    data
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

export const getCategoryCount = () => {
  return request<number>({
    method: 'GET',
    url: 'category/count'
  })
}
