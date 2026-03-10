import api from '@/lib/axios'
import type {
  UserListItem,
  UserDetail,
  UserCreateRequest,
  UserUpdateRequest,
  UserFilters,
  PageResponse,
} from '@/types/admin'

export const userService = {
  getAll: async (filters: UserFilters = {}): Promise<PageResponse<UserListItem>> => {
    const params = new URLSearchParams()
    if (filters.search) params.append('search', filters.search)
    if (filters.isActive !== undefined) params.append('isActive', String(filters.isActive))
    if (filters.page !== undefined) params.append('page', String(filters.page))
    if (filters.size !== undefined) params.append('size', String(filters.size))
    if (filters.sortBy) params.append('sortBy', filters.sortBy)
    if (filters.sortDir) params.append('sortDir', filters.sortDir)

    const response = await api.get<PageResponse<UserListItem>>(`/users?${params.toString()}`)
    return response.data
  },

  getById: async (id: string): Promise<UserDetail> => {
    const response = await api.get<UserDetail>(`/users/${id}`)
    return response.data
  },

  create: async (data: UserCreateRequest): Promise<UserDetail> => {
    const response = await api.post<UserDetail>('/users', data)
    return response.data
  },

  update: async (id: string, data: UserUpdateRequest): Promise<UserDetail> => {
    const response = await api.put<UserDetail>(`/users/${id}`, data)
    return response.data
  },

  delete: async (id: string): Promise<void> => {
    await api.delete(`/users/${id}`)
  },

  updateGroups: async (id: string, groupIds: string[]): Promise<void> => {
    await api.put(`/users/${id}/groups`, groupIds)
  },
}
