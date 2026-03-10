# meu-saas - Front-end

## Stack
React 18 + TypeScript + Vite + Tailwind CSS + shadcn/ui

## Estrutura de Pastas
```
src/
├── components/ui/          # shadcn/ui components (não editar manualmente)
├── components/             # Componentes reutilizáveis do projeto
├── pages/                  # Páginas da aplicação
├── hooks/                  # Custom hooks
├── services/               # Axios instance + chamadas API
├── contexts/               # React Contexts (Auth, Theme, Tenant)
├── types/                  # TypeScript types/interfaces
├── lib/                    # Utilitários (cn, formatters)
└── routes/                 # Configuração de rotas
```

## Comandos
```bash
npm run dev          # Dev server (porta 3000)
npm run build        # Build de produção
npm run lint         # ESLint
npm run preview      # Preview do build
```

## Convenções
- **Componentes**: PascalCase, um componente por arquivo
- **Hooks**: prefixo `use` (ex: `useAuth`, `useMerchant`)
- **API calls**: SEMPRE via TanStack Query (useQuery/useMutation), nunca fetch direto
- **HTTP client**: Axios com interceptors para JWT e refresh token automático
- **Formulários**: React Hook Form + Zod para validação
- **Estilização**: Tailwind CSS + cn() helper, NUNCA CSS puro
- **shadcn/ui**: instalar componentes via `npx shadcn-ui@latest add <component>`
- **Imports**: paths absolutos via `@/` (configurado no vite.config.ts)

## API
- Base URL: `http://localhost:8444/api/v1`
- Auth: Bearer token no header `Authorization`
- Refresh automático via interceptor Axios quando token expira

## Context7 — Bibliotecas para consultar
React, Vite, TanStack Query, React Router, shadcn/ui, Zod, Tailwind CSS, React Hook Form, Axios
