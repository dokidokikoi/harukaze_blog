import request from '@/utils/request'
import { ICommonParam, IPage } from './types/common'
import { IPermission, IPermissionTree } from './types/permission/permission'

export const getPermissionList = (params: ICommonParam) => {
  return request<IPage<IPermission>>({
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

export const getPermissionTree = () => {
  return request<IPermissionTree[]>({
    method: 'GET',
    url: 'permission/permission_tree'
  })
}
