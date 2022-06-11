export interface ITag {
  id: string
  tagName: string
}

export interface ITagData {
  tag: ITag
  articleCount: number
  viewCount: number
  commentCount: number
}
