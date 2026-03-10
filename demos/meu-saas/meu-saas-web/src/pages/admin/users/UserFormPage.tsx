import { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { useForm } from 'react-hook-form'
import { zodResolver } from '@hookform/resolvers/zod'
import { z } from 'zod'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { ArrowLeft, Loader2, Eye, EyeOff } from 'lucide-react'
import { toast } from 'sonner'

import { Button } from '@/components/ui/button'
import { Input } from '@/components/ui/input'
import { Label } from '@/components/ui/label'
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Switch } from '@/components/ui/switch'
import { Checkbox } from '@/components/ui/checkbox'
import { userService } from '@/services/user.service'
import { groupService } from '@/services/group.service'

const createSchema = z.object({
  name: z.string().min(3, 'Nome deve ter pelo menos 3 caracteres'),
  email: z.string().email('Email inválido'),
  password: z.string().min(8, 'Senha deve ter pelo menos 8 caracteres'),
  phone: z.string().optional(),
  groupIds: z.array(z.string()).optional(),
})

const updateSchema = z.object({
  name: z.string().min(3, 'Nome deve ter pelo menos 3 caracteres'),
  email: z.string().email('Email inválido'),
  phone: z.string().optional(),
  isActive: z.boolean(),
  groupIds: z.array(z.string()).optional(),
})

type CreateFormData = z.infer<typeof createSchema>
type UpdateFormData = z.infer<typeof updateSchema>

export function UserFormPage() {
  const { id } = useParams()
  const navigate = useNavigate()
  const queryClient = useQueryClient()
  const isEditing = !!id
  const [showPassword, setShowPassword] = useState(false)
  const [selectedGroups, setSelectedGroups] = useState<string[]>([])

  const { data: user, isLoading: isLoadingUser } = useQuery({
    queryKey: ['user', id],
    queryFn: () => userService.getById(id!),
    enabled: isEditing,
  })

  const { data: groups } = useQuery({
    queryKey: ['groups-simple'],
    queryFn: () => groupService.getAllSimple(),
  })

  const createForm = useForm<CreateFormData>({
    resolver: zodResolver(createSchema),
    defaultValues: {
      name: '',
      email: '',
      password: '',
      phone: '',
      groupIds: [],
    },
  })

  const updateForm = useForm<UpdateFormData>({
    resolver: zodResolver(updateSchema),
    defaultValues: {
      name: '',
      email: '',
      phone: '',
      isActive: true,
      groupIds: [],
    },
  })

  useEffect(() => {
    if (user) {
      updateForm.reset({
        name: user.name,
        email: user.email,
        phone: user.phone || '',
        isActive: user.isActive,
        groupIds: user.groups.map((g) => g.id),
      })
      setSelectedGroups(user.groups.map((g) => g.id))
    }
  }, [user, updateForm])

  const createMutation = useMutation({
    mutationFn: userService.create,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['users'] })
      toast.success('Usuário criado com sucesso!')
      navigate('/admin/users')
    },
    onError: () => {
      toast.error('Erro ao criar usuário')
    },
  })

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: UpdateFormData }) =>
      userService.update(id, {
        name: data.name,
        email: data.email,
        phone: data.phone,
        isActive: data.isActive,
        groupIds: data.groupIds,
      }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['users'] })
      queryClient.invalidateQueries({ queryKey: ['user', id] })
      toast.success('Usuário atualizado com sucesso!')
      navigate('/admin/users')
    },
    onError: () => {
      toast.error('Erro ao atualizar usuário')
    },
  })

  const onCreateSubmit = (data: CreateFormData) => {
    createMutation.mutate({
      ...data,
      groupIds: selectedGroups,
    })
  }

  const onUpdateSubmit = (data: UpdateFormData) => {
    updateMutation.mutate({
      id: id!,
      data: {
        ...data,
        groupIds: selectedGroups,
      },
    })
  }

  const toggleGroup = (groupId: string) => {
    setSelectedGroups((prev) =>
      prev.includes(groupId)
        ? prev.filter((id) => id !== groupId)
        : [...prev, groupId]
    )
  }

  if (isEditing && isLoadingUser) {
    return (
      <div className="flex items-center justify-center py-12">
        <Loader2 className="h-8 w-8 animate-spin" />
      </div>
    )
  }

  return (
    <div className="space-y-6">
      <div className="flex items-center gap-4">
        <Button variant="ghost" size="icon" onClick={() => navigate('/admin/users')}>
          <ArrowLeft className="h-4 w-4" />
        </Button>
        <div>
          <h1 className="text-3xl font-bold">
            {isEditing ? 'Editar Usuário' : 'Novo Usuário'}
          </h1>
          <p className="text-muted-foreground">
            {isEditing
              ? 'Atualize as informações do usuário'
              : 'Preencha os dados do novo usuário'}
          </p>
        </div>
      </div>

      <div className="grid gap-6 md:grid-cols-2">
        <Card>
          <CardHeader>
            <CardTitle>Informações Básicas</CardTitle>
            <CardDescription>Dados principais do usuário</CardDescription>
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
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  {...(isEditing
                    ? updateForm.register('email')
                    : createForm.register('email'))}
                />
                {(isEditing
                  ? updateForm.formState.errors.email
                  : createForm.formState.errors.email) && (
                  <p className="text-sm text-red-600">
                    {isEditing
                      ? updateForm.formState.errors.email?.message
                      : createForm.formState.errors.email?.message}
                  </p>
                )}
              </div>

              {!isEditing && (
                <div className="space-y-2">
                  <Label htmlFor="password">Senha</Label>
                  <div className="relative">
                    <Input
                      id="password"
                      type={showPassword ? 'text' : 'password'}
                      {...createForm.register('password')}
                    />
                    <Button
                      type="button"
                      variant="ghost"
                      size="icon"
                      className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
                      onClick={() => setShowPassword(!showPassword)}
                    >
                      {showPassword ? (
                        <EyeOff className="h-4 w-4" />
                      ) : (
                        <Eye className="h-4 w-4" />
                      )}
                    </Button>
                  </div>
                  {createForm.formState.errors.password && (
                    <p className="text-sm text-red-600">
                      {createForm.formState.errors.password.message}
                    </p>
                  )}
                </div>
              )}

              <div className="space-y-2">
                <Label htmlFor="phone">Telefone</Label>
                <Input
                  id="phone"
                  type="tel"
                  placeholder="(00) 00000-0000"
                  {...(isEditing
                    ? updateForm.register('phone')
                    : createForm.register('phone'))}
                />
              </div>

              {isEditing && (
                <div className="flex items-center justify-between">
                  <Label htmlFor="isActive">Usuário Ativo</Label>
                  <Switch
                    id="isActive"
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
                disabled={createMutation.isPending || updateMutation.isPending}
              >
                {(createMutation.isPending || updateMutation.isPending) && (
                  <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                )}
                {isEditing ? 'Salvar Alterações' : 'Criar Usuário'}
              </Button>
            </form>
          </CardContent>
        </Card>

        <Card>
          <CardHeader>
            <CardTitle>Grupos</CardTitle>
            <CardDescription>
              Selecione os grupos de acesso do usuário
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="space-y-3">
              {groups?.map((group) => (
                <div
                  key={group.id}
                  className="flex items-center space-x-3 rounded-lg border p-3"
                >
                  <Checkbox
                    id={group.id}
                    checked={selectedGroups.includes(group.id)}
                    onCheckedChange={() => toggleGroup(group.id)}
                  />
                  <Label htmlFor={group.id} className="flex-1 cursor-pointer">
                    {group.name}
                  </Label>
                </div>
              ))}
              {(!groups || groups.length === 0) && (
                <p className="text-sm text-muted-foreground">
                  Nenhum grupo disponível
                </p>
              )}
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  )
}
