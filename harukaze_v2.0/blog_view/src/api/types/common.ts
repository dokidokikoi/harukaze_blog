export interface ILoginInfo {
  logo_square: string
  logo_rectangle: string
  login_logo: string
  slide: string[]
}

export interface IData {
  articles: number
  comments: number
  views: number
}

export interface ICommonParam {
  key: string
  page: number
  limit: number
}

export interface IPage<T> {
  currPage: number
  list: T[]
  pageSize: number
  totalCount: number
  totalPage: number
}

export interface IResp<T> {
  code: number
  msg: string
  data: T
}
