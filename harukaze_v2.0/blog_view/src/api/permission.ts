import request from '@/utils/request'
import { ICommonParam, IPage, IResp } from './types/common'
import { IPermission } from './types/permission/permission'

export const getPermissionList = (params: ICommonParam) => {
  return request<IResp<IPage<IPermission>>>({
    method: 'GET',
    url: 'permission/list',
    params
  })
}

export const addPermission = (params: IPermission) => {
  return request({
    method: 'POST',
    url: 'permission/save',
    params
  })
}

export const editPermission = (params: IPermission) => {
  return request({
    method: 'PUT',
    url: 'permission/update',
    params
  })
}
