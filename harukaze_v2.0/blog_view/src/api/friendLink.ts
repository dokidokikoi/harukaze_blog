import request from '@/utils/request'
import { ICommonParam, IPage, IResp } from './types/common'
import { IFriendLink } from './types/friendLink'

export const getLinkList = (params: ICommonParam) => {
  return request<IResp<IPage<IFriendLink>>>({
    method: 'GET',
    url: 'friendlink/list',
    params
  })
}

export const addLinkList = (params: IFriendLink) => {
  return request({
    method: 'POST',
    url: 'friendlink/save',
    params
  })
}

export const editLinkList = (params: IFriendLink) => {
  return request({
    method: 'PUT',
    url: 'friendlink/update',
    params
  })
}
