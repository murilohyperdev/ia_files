import api from '@/lib/axios'
import type { Rule } from '@/types/admin'

export const ruleService = {
  getAll: async (): Promise<Rule[]> => {
    const response = await api.get<Rule[]>('/rules')
    return response.data
  },

  getModules: async (): Promise<string[]> => {
    const response = await api.get<string[]>('/rules/modules')
    return response.data
  },

  getByModule: async (module: string): Promise<Rule[]> => {
    const response = await api.get<Rule[]>(`/rules/by-module/${module}`)
    return response.data
  },
}
