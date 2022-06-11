import request from '@/utils/request'
import { IPage, IResp } from './types/common'
import { ITodo, ITodoParam } from './types/todo'

export const getTodoList = (params: {
  key: string,
  days: number,
  page: number,
  limit: number}) => {
  return request<IResp<IPage<ITodo>>>({
    method: 'GET',
    url: 'todo/list',
    params
  })
}

export const addTodoList = (params: ITodo) => {
  return request({
    method: 'POST',
    url: 'todo/save',
    params
  })
}

export const editTodoList = (params: ITodoParam) => {
  return request({
    method: 'PUT',
    url: 'todo/update',
    params
  })
}

export const delTodo = (id: string) => {
  return request({
    method: 'DELETE',
    url: 'todo/delete/' + id
  })
}
