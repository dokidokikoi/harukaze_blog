import request from '@/utils/request'
import { ICommonParam, IPage, IResp } from './types/common'
import { ILog } from './types/log'

export const getlogList = (params: ICommonParam) => {
  return request<IResp<IPage<ILog>>>({
    method: 'GET',
    url: 'log/list',
    params
  })
}

export const delLog = (id: string) => {
  return request({
    method: 'DELETE',
    url: 'log/delete/' + id
  })
}
