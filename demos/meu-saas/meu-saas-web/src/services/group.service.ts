import api from '@/lib/axios'
import type {
  GroupListItem,
  GroupDetail,
  GroupSimple,
  GroupCreateRequest,
  GroupUpdateRequest,
  GroupFilters,
  PageResponse,
} from '@/types/admin'

export const groupService = {
  getAll: async (filters: GroupFilters = {}): Promise<PageResponse<GroupListItem>> => {
    const params = new URLSearchParams()
    if (filters.page !== undefined) params.append('page', String(filters.page))
    if (filters.size !== undefined) params.append('size', String(filters.size))
    if (filters.sortBy) params.append('sortBy', filters.sortBy)
    if (filters.sortDir) params.append('sortDir', filters.sortDir)

    const response = await api.get<PageResponse<GroupListItem>>(`/groups?${params.toString()}`)
    return response.data
  },

  getAllSimple: async (): Promise<GroupSimple[]> => {
    const response = await api.get<GroupSimple[]>('/groups/simple')
    return response.data
  },

  getById: async (id: string): Promise<GroupDetail> => {
    const response = await api.get<GroupDetail>(`/groups/${id}`)
    return response.data
  },

  create: async (data: GroupCreateRequest): Promise<GroupDetail> => {
    const response = await api.post<GroupDetail>('/groups', data)
    return response.data
  },

  update: async (id: string, data: GroupUpdateRequest): Promise<GroupDetail> => {
    const response = await api.put<GroupDetail>(`/groups/${id}`, data)
    return response.data
  },

  delete: async (id: string): Promise<void> => {
    await api.delete(`/groups/${id}`)
  },
}
