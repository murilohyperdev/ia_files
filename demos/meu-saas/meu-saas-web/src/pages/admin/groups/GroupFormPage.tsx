import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { ArrowLeft, Loader2 } from 'lucide-react'
import { toast } from 'sonner'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Switch } from '@/components/ui/switch'
import { Checkbox } from '@/components/ui/checkbox'
import { Badge } from '@/components/ui/badge'
import { groupService } from '@/services/group.service'
import { ruleService } from '@/services/rule.service'
import type { Rule } from '@/types/admin'

const createSchema = z.object({
  name: z.string().min(2, 'Nome deve ter pelo menos 2 caracteres'),
  description: z.string().optional(),
  ruleIds: z.array(z.string()).optional(),
})

const updateSchema = z.object({
  name: z.string().min(2, 'Nome deve ter pelo menos 2 caracteres'),
  description: z.string().optional(),
  isActive: z.boolean(),
  ruleIds: z.array(z.string()).optional(),
})

type CreateFormData = z.infer<typeof createSchema>
type UpdateFormData = z.infer<typeof updateSchema>

export function GroupFormPage() {
  const { id } = useParams()
  const navigate = useNavigate()
  const queryClient = useQueryClient()
  const isEditing = !!id
  const [selectedRules, setSelectedRules] = useState<string[]>([])

  const { data: group, isLoading: isLoadingGroup } = useQuery({
    queryKey: ['group', id],
    queryFn: () => groupService.getById(id!),
    enabled: isEditing,
  })

  const { data: rules } = useQuery({
    queryKey: ['rules'],
    queryFn: () => ruleService.getAll(),
  })

  const createForm = useForm<CreateFormData>({
    resolver: zodResolver(createSchema),
    defaultValues: {
      name: '',
      description: '',
      ruleIds: [],
    },
  })

  const updateForm = useForm<UpdateFormData>({
    resolver: zodResolver(updateSchema),
    defaultValues: {
      name: '',
      description: '',
      isActive: true,
      ruleIds: [],
    },
  })

  useEffect(() => {
    if (group) {
      updateForm.reset({
        name: group.name,
        description: group.description || '',
        isActive: group.isActive,
        ruleIds: group.rules.map((r) => r.id),
      })
      setSelectedRules(group.rules.map((r) => r.id))
    }
  }, [group, updateForm])

  const createMutation = useMutation({
    mutationFn: groupService.create,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['groups'] })
      toast.success('Grupo criado com sucesso!')
      navigate('/admin/groups')
    },
    onError: () => {
      toast.error('Erro ao criar grupo')
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: UpdateFormData }) =>
      groupService.update(id, {
        name: data.name,
        description: data.description,
        isActive: data.isActive,
        ruleIds: data.ruleIds,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['groups'] })
      queryClient.invalidateQueries({ queryKey: ['group', id] })
      toast.success('Grupo atualizado com sucesso!')
      navigate('/admin/groups')
    },
    onError: () => {
      toast.error('Erro ao atualizar grupo')
    },
  })

  const onCreateSubmit = (data: CreateFormData) => {
    createMutation.mutate({
      ...data,
      ruleIds: selectedRules,
    })
  }

  const onUpdateSubmit = (data: UpdateFormData) => {
    updateMutation.mutate({
      id: id!,
      data: {
        ...data,
        ruleIds: selectedRules,
      },
    })
  }

  const toggleRule = (ruleId: string) => {
    setSelectedRules((prev) =>
      prev.includes(ruleId)
        ? prev.filter((id) => id !== ruleId)
        : [...prev, ruleId]
    )
  }

  const toggleModule = (module: string, moduleRules: Rule[]) => {
    const moduleRuleIds = moduleRules.map((r) => r.id)
    const allSelected = moduleRuleIds.every((id) => selectedRules.includes(id))

    if (allSelected) {
      setSelectedRules((prev) => prev.filter((id) => !moduleRuleIds.includes(id)))
    } else {
      setSelectedRules((prev) => [...new Set([...prev, ...moduleRuleIds])])
    }
  }

  const rulesByModule = rules?.reduce((acc, rule) => {
    if (!acc[rule.module]) {
      acc[rule.module] = []
    }
    acc[rule.module].push(rule)
    return acc
  }, {} as Record<string, Rule[]>) || {}

  if (isEditing && isLoadingGroup) {
    return (
      <div className="flex items-center justify-center py-12">
        <Loader2 className="h-8 w-8 animate-spin" />
      </div>
    )
  }

  const isSystemGroup = isEditing && group?.isSystem

  return (
    <div className="space-y-6">
      <div className="flex items-center gap-4">
        <Button variant="ghost" size="icon" onClick={() => navigate('/admin/groups')}>
          <ArrowLeft className="h-4 w-4" />
        </Button>
        <div>
          <h1 className="text-3xl font-bold">
            {isEditing ? 'Editar Grupo' : 'Novo Grupo'}
          </h1>
          <p className="text-muted-foreground">
            {isEditing
              ? 'Atualize as informações do grupo'
              : 'Preencha os dados do novo grupo'}
          </p>
        </div>
      </div>

      {isSystemGroup && (
        <div className="rounded-lg border border-yellow-500 bg-yellow-50 p-4 dark:bg-yellow-950">
          <p className="text-sm text-yellow-700 dark:text-yellow-300">
            Este é um grupo do sistema e não pode ser editado ou excluído.
          </p>
        </div>
      )}

      <div className="grid gap-6 md:grid-cols-2">
        <Card>
          <CardHeader>
            <CardTitle>Informações do Grupo</CardTitle>
            <CardDescription>Dados principais do grupo</CardDescription>
          </CardHeader>
          <CardContent>
            <form
              onSubmit={
                isEditing
                  ? updateForm.handleSubmit(onUpdateSubmit)
                  : createForm.handleSubmit(onCreateSubmit)
              }
              className="space-y-4"
            >
              <div className="space-y-2">
                <Label htmlFor="name">Nome</Label>
                <Input
                  id="name"
                  disabled={isSystemGroup}
                  {...(isEditing
                    ? updateForm.register('name')
                    : createForm.register('name'))}
                />
                {(isEditing
                  ? updateForm.formState.errors.name
                  : createForm.formState.errors.name) && (
                  <p className="text-sm text-red-600">
                    {isEditing
                      ? updateForm.formState.errors.name?.message
                      : createForm.formState.errors.name?.message}
                  </p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="description">Descrição</Label>
                <Input
                  id="description"
                  disabled={isSystemGroup}
                  placeholder="Descrição opcional do grupo"
                  {...(isEditing
                    ? updateForm.register('description')
                    : createForm.register('description'))}
                />
              </div>

              {isEditing && (
                <div className="flex items-center justify-between">
                  <Label htmlFor="isActive">Grupo Ativo</Label>
                  <Switch
                    id="isActive"
                    disabled={isSystemGroup}
                    checked={updateForm.watch('isActive')}
                    onCheckedChange={(checked) =>
                      updateForm.setValue('isActive', checked)
                    }
                  />
                </div>
              )}

              <Button
                type="submit"
                className="w-full"
                disabled={isSystemGroup || createMutation.isPending || updateMutation.isPending}
              >
                {(createMutation.isPending || updateMutation.isPending) && (
                  <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                )}
                {isEditing ? 'Salvar Alterações' : 'Criar Grupo'}
              </Button>
            </form>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Permissões</CardTitle>
            <CardDescription>
              Selecione as permissões do grupo
            </CardDescription>
          </CardHeader>
          <CardContent className="max-h-96 overflow-y-auto">
            <div className="space-y-6">
              {Object.entries(rulesByModule).map(([module, moduleRules]) => {
                const allSelected = moduleRules.every((r) =>
                  selectedRules.includes(r.id)
                )
                const someSelected = moduleRules.some((r) =>
                  selectedRules.includes(r.id)
                )

                return (
                  <div key={module} className="space-y-3">
                    <div className="flex items-center justify-between">
                      <div className="flex items-center gap-2">
                        <Checkbox
                          id={`module-${module}`}
                          checked={allSelected}
                          disabled={isSystemGroup}
                          onCheckedChange={() => toggleModule(module, moduleRules)}
                          className={someSelected && !allSelected ? 'opacity-50' : ''}
                        />
                        <Label
                          htmlFor={`module-${module}`}
                          className="font-semibold cursor-pointer"
                        >
                          {module}
                        </Label>
                      </div>
                      <Badge variant="secondary">
                        {moduleRules.filter((r) => selectedRules.includes(r.id)).length}/
                        {moduleRules.length}
                      </Badge>
                    </div>
                    <div className="ml-6 space-y-2">
                      {moduleRules.map((rule) => (
                        <div
                          key={rule.id}
                          className="flex items-start space-x-3 rounded-lg border p-2"
                        >
                          <Checkbox
                            id={rule.id}
                            checked={selectedRules.includes(rule.id)}
                            disabled={isSystemGroup}
                            onCheckedChange={() => toggleRule(rule.id)}
                          />
                          <div className="flex-1">
                            <Label
                              htmlFor={rule.id}
                              className="cursor-pointer font-medium text-sm"
                            >
                              {rule.name}
                            </Label>
                            {rule.description && (
                              <p className="text-xs text-muted-foreground">
                                {rule.description}
                              </p>
                            )}
                          </div>
                        </div>
                      ))}
                    </div>
                  </div>
                )
              })}
              {(!rules || rules.length === 0) && (
                <p className="text-sm text-muted-foreground">
                  Nenhuma permissão disponível
                </p>
              )}
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
