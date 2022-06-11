import { IUser } from './user'

export interface IComment {
  id: string
  content: string
  level: number
  createDate: string
  state: number
  articleId: string
  authorId: string
  parentId: string
  toUserId: string
  weight: number
  authorName: string
  toUserName: string
}

export interface ICommentVo {
  id: string
  content: string
  level: number
  createDate: string
  state: number
  author: IUser
  toUserNickname: string
  weight: number
  children: ICommentVo[]
}

export interface ICommentParam {
  content: string
  articleId: string
  author: {
    id: string
    name: string
  }
  parentId: string
  toUser: {
    id: string
    name: string
  }
}
