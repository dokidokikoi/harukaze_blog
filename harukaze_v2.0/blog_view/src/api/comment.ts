import request from '@/utils/request'
import { IComment, ICommentParam, ICommentVo } from './types/comment'
import { IPage } from './types/common'

export const getCommentList = (id: string, params: any) => {
  return request<IPage<ICommentVo>>({
    method: 'GET',
    url: 'comment/list/' + id,
    params
  })
}

export const postComment = (data: ICommentParam) => {
  return request({
    method: 'POST',
    url: 'comment/save',
    data
  })
}

export const editComment = (data: IComment) => {
  return request({
    method: 'PUT',
    url: 'comment/update',
    data
  })
}

export const setCommentState = (params: {id: string, flag: boolean}) => {
  return request({
    method: 'PUT',
    url: 'comment/set_state',
    params
  })
}
