import request from '@/utils/request'
import { ICommonParam, IPage } from './types/common'
import { IFriendLink } from './types/friendLink'

export const getLinkList = (params: ICommonParam) => {
  return request<IPage<IFriendLink>>({
    method: 'GET',
    url: 'friendlink/list',
    params
  })
}

export const addLink = (data: IFriendLink) => {
  return request({
    method: 'POST',
    url: 'friendlink/save',
    data
  })
}

export const editLink = (data: IFriendLink) => {
  return request({
    method: 'PUT',
    url: 'friendlink/update',
    data
  })
}

export const setLinkState = (params: {id: string, flag: boolean}) => {
  return request({
    method: 'PUT',
    url: 'friendlink/set_state',
    params
  })
}
