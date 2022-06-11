import { createStore, useStore as baseUseStore, Store } from 'vuex'
import { InjectionKey } from 'vue'
import { IUser } from '@/api/types/user'
import { setItem, getItem } from '@/utils/storage'

interface IUserInfo {
  token: string
  user_info: IUser
}

// 为 store state 声明类型
const state = {
  isCollapse: false,
  user: getItem<{ token: string} & IUser>('user') as unknown as IUserInfo
}

export type State = typeof state

// 定义 injection key
export const key: InjectionKey<Store<State>> = Symbol('store')

export const store = createStore<State>({
  state,
  mutations: {
    setIsCollapse (state, flag) {
      state.isCollapse = flag
    },
    setUser (state, playload) {
      state.user = playload
      setItem('user', state.user)
    }
  }
})

// 定义自己的 `useStore` 组合式函数
export function useStore () {
  return baseUseStore(key)
}
