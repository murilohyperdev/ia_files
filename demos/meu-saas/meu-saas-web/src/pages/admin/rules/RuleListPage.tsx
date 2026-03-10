import { useQuery } from '@tanstack/react-query'
import { Loader2, Shield, Key } from 'lucide-react'

import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card'
import { Badge } from '@/components/ui/badge'
import { ruleService } from '@/services/rule.service'
import type { Rule } from '@/types/admin'

export function RuleListPage() {
  const { data: rules, isLoading } = useQuery({
    queryKey: ['rules'],
    queryFn: () => ruleService.getAll(),
  })

  const rulesByModule = rules?.reduce((acc, rule) => {
    if (!acc[rule.module]) {
      acc[rule.module] = []
    }
    acc[rule.module].push(rule)
    return acc
  }, {} as Record<string, Rule[]>) || {}

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-12">
        <Loader2 className="h-8 w-8 animate-spin" />
      </div>
    )
  }

  return (
    <div className="space-y-6">
      <div>
        <h1 className="text-3xl font-bold">Permissões</h1>
        <p className="text-muted-foreground">
          Visualize todas as permissões disponíveis no sistema
        </p>
      </div>

      <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
        {Object.entries(rulesByModule).map(([module, moduleRules]) => (
          <Card key={module}>
            <CardHeader>
              <div className="flex items-center justify-between">
                <CardTitle className="flex items-center gap-2">
                  <Shield className="h-5 w-5" />
                  {module}
                </CardTitle>
                <Badge variant="secondary">{moduleRules.length}</Badge>
              </div>
              <CardDescription>
                Permissões do módulo {module}
              </CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-3">
                {moduleRules.map((rule) => (
                  <div
                    key={rule.id}
                    className="flex items-start gap-3 rounded-lg border p-3"
                  >
                    <Key className="h-4 w-4 mt-0.5 text-muted-foreground" />
                    <div className="flex-1 min-w-0">
                      <p className="font-medium text-sm">{rule.name}</p>
                      <p className="text-xs text-muted-foreground truncate">
                        {rule.code}
                      </p>
                      {rule.description && (
                        <p className="text-xs text-muted-foreground mt-1">
                          {rule.description}
                        </p>
                      )}
                    </div>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      {(!rules || rules.length === 0) && (
        <div className="text-center py-12 text-muted-foreground">
          Nenhuma permissão cadastrada no sistema
        </div>
      )}
    </div>
  )
}
