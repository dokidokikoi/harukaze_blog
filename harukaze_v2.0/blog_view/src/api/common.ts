/**
 * 公共基础接口封装
 */
import request from '@/utils/request'
import { ILoginInfo } from './types/common'
import { IUserVo } from './types/user'

interface logData {
  token: string
  user_info: IUserVo
}

export const getCaptcha = () => {
  return request<ILoginInfo>({
    method: 'GET',
    url: 'captcha'
  })
}

export const login = (params: {
  account: string
  password: string
  key: string
  code: string
}) => {
  return request<logData>({
    method: 'POST',
    url: 'login',
    params
  })
}

export const logout = () => {
  return request({
    method: 'POST',
    url: 'logout'
  })
}
