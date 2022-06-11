import request from '@/utils/request'
import { ICommonParam, IPage, IResp } from './types/common'
import { IUser, IUserVo } from './types/user'

export const getUserlist = (params: ICommonParam) => {
  return request<IResp<IPage<IUserVo>>>({
    method: 'GET',
    url: 'user/list',
    params
  })
}

export const addUser = (params: IUser) => {
  return request({
    method: 'POST',
    url: 'user/save',
    params
  })
}

export const editUser = (params: IUser) => {
  return request({
    method: 'PUT',
    url: 'user/save',
    params
  })
}
