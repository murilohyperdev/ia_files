export interface UserListItem {
  id: string
  name: string
  email: string
  avatarUrl?: string
  phone?: string
  isActive: boolean
  lastLoginAt?: string
  createdAt: string
  groups: GroupSimple[]
}

export interface UserDetail {
  id: string
  merchantId: string
  name: string
  email: string
  avatarUrl?: string
  phone?: string
  isActive: boolean
  emailVerifiedAt?: string
  lastLoginAt?: string
  createdAt: string
  groups: GroupSimple[]
  rules: string[]
}

export interface GroupSimple {
  id: string
  name: string
}

export interface GroupListItem {
  id: string
  name: string
  description?: string
  isSystem: boolean
  isActive: boolean
  userCount: number
  createdAt: string
}

export interface GroupDetail {
  id: string
  name: string
  description?: string
  isSystem: boolean
  isActive: boolean
  userCount: number
  createdAt: string
  rules: Rule[]
}

export interface Rule {
  id: string
  code: string
  name: string
  description?: string
  module: string
}

export interface PageResponse<T> {
  content: T[]
  page: number
  size: number
  totalElements: number
  totalPages: number
  first: boolean
  last: boolean
}

export interface UserCreateRequest {
  name: string
  email: string
  password: string
  phone?: string
  groupIds?: string[]
}

export interface UserUpdateRequest {
  name: string
  email: string
  phone?: string
  isActive: boolean
  groupIds?: string[]
}

export interface GroupCreateRequest {
  name: string
  description?: string
  ruleIds?: string[]
}

export interface GroupUpdateRequest {
  name: string
  description?: string
  isActive: boolean
  ruleIds?: string[]
}

export interface UserFilters {
  search?: string
  isActive?: boolean
  page?: number
  size?: number
  sortBy?: string
  sortDir?: 'asc' | 'desc'
}

export interface GroupFilters {
  page?: number
  size?: number
  sortBy?: string
  sortDir?: 'asc' | 'desc'
}
