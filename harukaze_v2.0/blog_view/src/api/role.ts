import request from '@/utils/request'
import { ICommonParam, IPage, IResp } from './types/common'
import { IRole, IRoleParam, IRoleVo } from './types/permission/role'

export const getRoleList = (params: ICommonParam) => {
  return request<IResp<IPage<IRoleVo>>>({
    method: 'GET',
    url: 'role/list',
    params
  })
}

export const addRole = (params: IRole) => {
  return request({
    method: 'POST',
    url: 'role/save',
    params
  })
}

export const editRole = (params: IRoleParam) => {
  return request({
    method: 'PUT',
    url: 'role/update',
    params
  })
}
