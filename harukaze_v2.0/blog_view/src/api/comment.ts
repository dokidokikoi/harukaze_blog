import request from '@/utils/request'
import { IComment, ICommentParam, ICommentVo } from './types/comment'
import { IPage, IResp } from './types/common'

export const getCommentList = (id: string, params: any) => {
  return request<IResp<IPage<ICommentVo>>>({
    method: 'GET',
    url: 'comment/list/' + id,
    params
  })
}

export const postComment = (params: ICommentParam) => {
  return request({
    method: 'POST',
    url: 'comment/save',
    params
  })
}

export const editComment = (params: IComment) => {
  return request({
    method: 'PUT',
    url: 'comment/update',
    params
  })
}
