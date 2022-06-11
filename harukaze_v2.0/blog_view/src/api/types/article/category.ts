export interface ICategory {
  id: string
  avatar: string
  categoryName: string
  categoryDesc: string
}

export interface ICategoryData {
  category: ICategory
  articleCount: number
  viewCount: number
  commentCount: number
}
