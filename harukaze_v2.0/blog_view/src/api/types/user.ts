import { IPermission } from './permission/permission'
import { IRole } from './permission/role'

export interface IUser {
  id: string
  account: string
  avatar: string
  createDate: string
  email: string
  lastLogin: string
  nickname: string
  password: string
  state: number
  summary: string
}

export interface IUserVo {
  id: string
  account: string
  avatar: string
  createDate: string
  email: string
  lastLogin: string
  nickname: string
  state: number
  summary: string
  role: IRole[]
  permissions: IPermission[]
}
