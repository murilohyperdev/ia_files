# 🚀 Prompt Template - Projeto SaaS Full Stack

> **Uso**: Copie o prompt abaixo e cole no Claude Code. Ele irá perguntar todas as informações necessárias antes de iniciar.

---

## 📋 PROMPT PARA CLAUDE CODE

```
Crie um projeto SaaS full stack completo com a seguinte estrutura e especificações.

---

## 🎯 INSTRUÇÕES INICIAIS PARA O CLAUDE CODE

**IMPORTANTE: Antes de criar qualquer arquivo ou estrutura, você DEVE coletar as seguintes informações do usuário de forma interativa.**

### Perguntas Obrigatórias (faça TODAS antes de iniciar):

Apresente as perguntas de forma amigável e organizada. Exemplo:

---
🚀 **Vamos configurar seu novo projeto SaaS!**

Por favor, responda as seguintes perguntas para eu personalizar o projeto:

1. **Nome do Projeto** (será usado nas pastas e configurações)
   - Exemplo: `meu-saas`, `gestao-vendas`, `plataforma-cursos`
   - _Digite o nome:_

2. **Nome da Empresa/Organização** (para o pacote Java: com.empresa.projeto)
   - Exemplo: `minhaempresa`, `techcorp`, `startup`
   - _Digite o nome:_

3. **Descrição do Projeto** (breve descrição do que o sistema faz)
   - Exemplo: "Sistema de gestão de vendas para pequenas empresas"
   - _Digite a descrição:_

4. **Arquitetura de Tenancy** (DECISÃO ARQUITETURAL IMPORTANTE)

   Escolha o modelo de arquitetura para seu SaaS:

   **Opção A) Multi-Tenant (Recomendado para SaaS)**
   - Um único banco de dados compartilhado
   - Isolamento por `merchant_id` em todas as tabelas principais
   - Mais econômico em infraestrutura
   - Facilita manutenção e atualizações centralizadas
   - Ideal para: SaaS público, plataformas com muitos clientes pequenos/médios

   **Opção B) On-Premise / Single-Tenant**
   - Banco de dados dedicado por cliente
   - Isolamento total dos dados
   - Maior custo de infraestrutura
   - Deploy e manutenção individualizados
   - Ideal para: Clientes enterprise, requisitos de compliance rígidos, dados sensíveis

   **Opção C) Híbrido**
   - Combina ambas as abordagens
   - Multi-tenant para clientes menores
   - Single-tenant para clientes enterprise
   - Requer arquitetura mais complexa

   _Digite A, B ou C:_

5. **Email do Administrador Padrão**
   - Exemplo: `admin@minhaempresa.com`
   - _Digite o email:_

6. **Porta do Front-end** (padrão: 5173)
   - Alternativas: `3000`, `3011`, `5173`
   - _Pressione Enter para usar o padrão ou digite outra porta:_

7. **Porta da API** (padrão: 8080)
   - Alternativas: `8080`, `8444`, `9090`
   - _Pressione Enter para usar o padrão ou digite outra porta:_

8. **Porta do PostgreSQL** (padrão: 5432)
   - Alternativas: `5432`, `5331`, `5433`
   - _Pressione Enter para usar o padrão ou digite outra porta:_

9. **Porta do Redis** (padrão: 6379)
   - Alternativas: `6379`, `6501`, `6380`
   - _Pressione Enter para usar o padrão ou digite outra porta:_

10. **Criar arquivo de Workspace do VSCode?** (Recomendado: Sim)
    - O arquivo `.code-workspace` organiza seu projeto no VSCode, exibindo cada módulo (Web, API, Root) como pastas separadas na barra lateral
    - Inclui configurações de editor, exclusões de busca e extensões recomendadas
    - **Recomendamos fortemente que crie**, pois facilita muito a navegação em projetos multi-módulo
    - _Digite sim ou não (padrão: sim):_

---

### Após coletar as respostas:

1. **Confirme** as informações com o usuário mostrando um resumo:
   ```
   📋 **Resumo da Configuração:**

   - Nome do Projeto: meu-saas
   - Pacote Java: com.minhaempresa.meusaas
   - Descrição: Sistema de gestão...
   - 🏗️ Arquitetura: Multi-Tenant (isolamento por merchant_id)
   - Admin: admin@minhaempresa.com
   - Front-end: http://localhost:5173
   - API: http://localhost:8080
   - PostgreSQL: localhost:5432
   - Redis: localhost:6379
   - Workspace VSCode: ✅ Sim (recomendado)

   ✅ Confirma essas configurações? (sim/não)
   ```

2. **Só após a confirmação**, inicie a criação do projeto substituindo os placeholders:
   - `{{NOME_DO_PROJETO}}` → Nome informado (com hífens, ex: `meu-saas`)
   - `{{projeto}}` → Nome sem hífens/underscores (ex: `meusaas`)
   - `{{empresa}}` → Nome da empresa (ex: `minhaempresa`)
   - `{{DESCRICAO}}` → Descrição informada
   - `{{ARQUITETURA}}` → Tipo de arquitetura escolhida (MULTI_TENANT, SINGLE_TENANT ou HYBRID)
   - `{{EMAIL_ADMIN}}` → Email do admin
   - `{{PORTA_FRONT}}` → Porta do front-end
   - `{{PORTA_API}}` → Porta da API
   - `{{PORTA_POSTGRES}}` → Porta do PostgreSQL
   - `{{PORTA_REDIS}}` → Porta do Redis
   - `{{CRIAR_WORKSPACE}}` → Se o usuário quer criar o arquivo .code-workspace (sim/não)

3. **Durante a criação**, informe o progresso ao usuário:
   ```
   ⏳ Criando estrutura de pastas...
   ⏳ Configurando front-end React + Vite...
   ⏳ Configurando back-end Spring Boot...
   ⏳ Criando migrations do banco de dados...
   ✅ Projeto criado com sucesso!
   ```

---

## ⚠️ INSTRUÇÃO CRÍTICA: USO DO CONTEXT7 MCP

**IMPORTANTE: LEIA ANTES DE IMPLEMENTAR QUALQUER CÓDIGO**

O **Context7 MCP** (https://context7.com/) é uma ferramenta que fornece documentação ATUALIZADA das bibliotecas. **SEMPRE** consulte o Context7 antes de escrever código para garantir que está usando a sintaxe e APIs mais recentes.

### Quando usar o Context7:

**🎨 FRONT-END - Consultar ANTES de implementar:**
- React 18+ (hooks, APIs, patterns atuais)
- Vite (configuração, plugins)
- TanStack Query / React Query (queries, mutations, cache)
- React Router DOM v6+ (rotas, loaders, actions)
- React Hook Form (register, handleSubmit, validação)
- Zod (schemas, validação)
- Tailwind CSS (classes, configuração)
- shadcn/ui (componentes, instalação, customização)
- Axios (interceptors, configuração)
- Zustand (stores, middleware)

**⚙️ BACK-END - Consultar ANTES de implementar:**
- Spring Boot 3.x (configurações, annotations)
- Spring Security 6.x (SecurityFilterChain, OAuth2, JWT)
- Spring Data JPA (repositories, queries, specifications)
- Spring Validation (annotations, custom validators)
- Spring Cache + Redis (configuração, annotations)
- Spring Boot Actuator (endpoints, configuração)
- Flyway (migrations, naming conventions)
- MapStruct (mappers, configuração)
- SpringDoc OpenAPI (Swagger, annotations)
- JJWT (criação e validação de tokens)

### Como usar:

```
Antes de implementar [funcionalidade], consulte o Context7 para obter 
a documentação atualizada de [biblioteca] e use a sintaxe mais recente.
```

### Exemplo de fluxo correto:

```
1. ❌ ERRADO: Implementar Spring Security baseado em conhecimento antigo
   
2. ✅ CORRETO: 
   a) Consultar Context7 para "Spring Security 6 JWT configuration"
   b) Verificar a sintaxe atual do SecurityFilterChain
   c) Implementar usando a documentação atualizada
```

### 🏆 GOLDEN RULE: Validação de Versão

**REGRA DE OURO: SEMPRE confira a documentação retornada pelo Context7 com a versão REAL utilizada no projeto.**

Antes de aplicar qualquer código baseado na documentação do Context7:

1. **Verifique o `package.json`** (front-end) ou **`pom.xml` / `build.gradle`** (back-end) para identificar a versão exata da biblioteca instalada no projeto
2. **Compare** com a versão da documentação retornada pelo Context7
3. **Se as versões divergirem**, solicite ao Context7 a documentação específica da versão utilizada no projeto

**Por quê?** O Context7 pode retornar a documentação da versão mais recente de uma biblioteca, que pode ser diferente da versão instalada no projeto. Usar docs de uma versão superior pode introduzir chamadas a APIs inexistentes, configurações incompatíveis ou comportamentos inesperados.

```
Exemplo:
- pom.xml tem Spring Boot 3.2.5
- Context7 retorna docs do Spring Boot 3.4.x
- ❌ ERRADO: Usar a documentação 3.4 diretamente
- ✅ CORRETO: Solicitar ao Context7 a documentação do Spring Boot 3.2.x
```

### Nota importante:

As bibliotecas mudam frequentemente. O que funcionava em versões anteriores pode estar:
- **Deprecated** (marcado para remoção)
- **Removido** (não existe mais)
- **Alterado** (sintaxe diferente)

**SEMPRE use o Context7 para evitar implementar código desatualizado!**

---

## 📁 ESTRUTURA DO PROJETO (usar após coleta de informações)

## 📁 ESTRUTURA DO PROJETO

/{{NOME_DO_PROJETO}}
├── /{{NOME_DO_PROJETO}}-web/             # Front-end React
│   └── CLAUDE.md                         #   Convenções específicas do frontend
├── /{{NOME_DO_PROJETO}}-api/             # Back-end Java Spring Boot (multi-module Maven)
│   ├── {{projeto}}-core/                 #   Domain: entities, service interfaces, exceptions
│   ├── {{projeto}}-infrastructure/       #   JPA repos, Redis, multi-tenant
│   ├── {{projeto}}-security/             #   JWT, filters, RBAC, SecurityConfig
│   ├── {{projeto}}-web/                  #   REST controllers, DTOs, Swagger, mappers
│   ├── {{projeto}}-app/                  #   Main class, configs, application.yml, Flyway (bootável)
│   ├── pom.xml                           #   Parent POM
│   └── CLAUDE.md                         #   Convenções específicas do backend
├── CLAUDE.md                              # Instruções gerais para o Claude Code
├── README.md                              # Documentação do projeto
└── {{NOME_DO_PROJETO}}.code-workspace     # Workspace do VSCode (se criado)

---

## 🎨 FRONT-END ({{NOME_DO_PROJETO}}-web)

### Stack Tecnológica

1. **React 18+ com Vite** - Framework JavaScript moderno com build tool ultrarrápido
   - Utilize Vite como bundler por sua velocidade de HMR (Hot Module Replacement)
   - Configure com TypeScript para tipagem estática
   - Referência: https://react.dev/learn/build-a-react-app-from-scratch#vite

2. **shadcn/ui** - Biblioteca de componentes UI reutilizáveis e customizáveis
   - Componentes construídos sobre Radix UI primitives
   - Estilização com Tailwind CSS
   - Copie apenas os componentes necessários (não é dependência)
   - Referência: https://ui.shadcn.com/docs
   - MCP Server: https://ui.shadcn.com/docs/mcp

3. **TanStack Query (React Query)** - Gerenciamento de estado assíncrono e cache
   - Utilize para todas as chamadas à API REST
   - Configure cache, refetch, retry e invalidação automática
   - Implemente mutations para operações POST/PUT/DELETE
   - Referência: https://tanstack.com/query/latest

4. **React Router DOM v6+** - Roteamento declarativo
   - Configure rotas protegidas (PrivateRoute)
   - Implemente lazy loading para code splitting

5. **Axios** - Cliente HTTP para consumo da API REST
   - Configure interceptors para token JWT
   - Implemente refresh token automático
   - Trate erros globalmente

6. **Context7 MCP** - Documentação atualizada das bibliotecas
   - **⚠️ CRÍTICO: Consulte SEMPRE antes de implementar qualquer código**
   - Veja a seção "INSTRUÇÃO CRÍTICA: USO DO CONTEXT7 MCP" acima
   - Referência: https://context7.com/

7. **Zustand ou Context API** - Gerenciamento de estado global leve
   - Armazene dados do usuário autenticado
   - Gerencie tema (dark/light mode)

8. **React Hook Form + Zod** - Formulários com validação
   - Validação de schemas com Zod
   - Integração nativa com shadcn/ui

9. **Design Responsivo Mobile-First** - Layout adaptativo obrigatório
   - Abordagem Mobile-First: estilize primeiro para mobile, depois para telas maiores
   - Breakpoints Tailwind: sm (640px), md (768px), lg (1024px), xl (1280px), 2xl (1536px)
   - Touch targets mínimos de 44x44px para elementos interativos
   - Sidebar responsiva: drawer (mobile) → colapsada (tablet) → fixa (desktop)
   - Testes obrigatórios em múltiplos viewports antes de deploy
   - Referência: Seção "📱 Design Responsivo (Mobile-First)" neste documento

### Estrutura de Pastas do Front-end

```
{{NOME_DO_PROJETO}}-web/
├── public/
├── src/
│   ├── assets/                    # Imagens, fontes, ícones
│   ├── components/
│   │   ├── ui/                    # Componentes shadcn/ui
│   │   ├── layout/                # Header, Sidebar, Footer
│   │   └── common/                # Componentes reutilizáveis
│   ├── contexts/                  # React Contexts
│   ├── hooks/                     # Custom hooks
│   ├── lib/                       # Utilitários (axios, utils)
│   ├── pages/                     # Páginas/Views
│   │   ├── auth/
│   │   │   ├── LoginPage.tsx
│   │   │   └── SignupPage.tsx
│   │   ├── profile/
│   │   │   └── ProfilePage.tsx
│   │   └── home/
│   │       └── HomePage.tsx
│   ├── services/                  # Chamadas API (queries/mutations)
│   ├── stores/                    # Zustand stores (se usar)
│   ├── types/                     # TypeScript types/interfaces
│   ├── routes/                    # Configuração de rotas
│   ├── App.tsx
│   ├── main.tsx
│   └── index.css
├── .env.example
├── .env.local
├── tsconfig.json
├── vite.config.ts
└── package.json
```

### Configuração Tailwind CSS v4

O Tailwind CSS v4 usa `@theme` directive ao invés de `tailwind.config.js`:

```css
/* src/index.css */
@import "tailwindcss";

@theme {
  --color-background: hsl(0 0% 100%);
  --color-foreground: hsl(222.2 84% 4.9%);
  --color-primary: hsl(222.2 47.4% 11.2%);
  --color-primary-foreground: hsl(210 40% 98%);
  --color-secondary: hsl(210 40% 96.1%);
  --color-muted: hsl(210 40% 96.1%);
  --color-muted-foreground: hsl(215.4 16.3% 46.9%);
  --color-accent: hsl(210 40% 96.1%);
  --color-destructive: hsl(0 84.2% 60.2%);
  --color-border: hsl(214.3 31.8% 91.4%);
  --color-input: hsl(214.3 31.8% 91.4%);
  --color-ring: hsl(222.2 84% 4.9%);
  --radius: 0.5rem;
}

@layer base {
  * {
    border-color: var(--color-border);
  }
  body {
    background-color: var(--color-background);
    color: var(--color-foreground);
  }
}

.dark {
  --color-background: hsl(222.2 84% 4.9%);
  --color-foreground: hsl(210 40% 98%);
  /* ... demais cores para dark mode */
}
```

> **ATENÇÃO**: Tailwind v4 NÃO suporta `@apply` com classes customizadas no `@layer base`. Use CSS direto com variáveis CSS. A sintaxe `@apply border-border` causará erro.

### Páginas a Implementar

#### 1. Login Page (`/login`)
- Formulário com email e senha
- Validação com React Hook Form + Zod
- Link para cadastro
- Link "Esqueci minha senha"
- Integração com API de autenticação
- Redirecionamento após login bem-sucedido

#### 2. Signup Page (`/signup`)
- Formulário com: nome, email, senha, confirmação de senha
- Validação completa com feedback visual
- Aceite de termos de uso (checkbox)
- Link para login
- Criação automática de merchant para novos usuários (se aplicável)

#### 3. Profile Page (`/profile`)
- Exibição dos dados do usuário
- Formulário de edição de perfil
- Upload de avatar (opcional)
- Alteração de senha
- Rota protegida (requer autenticação)

#### 4. Home Page (`/` ou `/dashboard`)
- **Menu Lateral (Sidebar) tipo Hamburger**:
  - Colapsável/expansível
  - Item de menu: HOME
  - Ícones com Lucide React
  - Responsivo (drawer no mobile)
  
- **Header**:
  - Logo à esquerda
  - Botão toggle do tema (Dark/Light) com ícone sol/lua
  - Avatar do usuário à direita com Dropdown contendo:
    - Nome/email do usuário
    - Separador
    - "Perfil" (link para /profile)
    - "Sair" (logout com limpeza de tokens)

- **Área Principal**:
  - Cards de boas-vindas ou dashboard inicial
  - Conteúdo placeholder para futuras funcionalidades

### Configurações Essenciais do Front-end

- **Tema Dark/Light**: Persistir preferência no localStorage
- **Interceptor Axios**: Adicionar Bearer token em todas as requisições
- **Refresh Token**: Renovar automaticamente quando expirar
- **Loading States**: Skeleton loaders durante carregamento
- **Error Boundaries**: Tratamento de erros global
- **Toast Notifications**: Feedback de ações (sonner ou react-hot-toast)

### 📱 Design Responsivo (Mobile-First)

> **OBRIGATÓRIO**: Todo o layout DEVE ser responsivo, funcionando perfeitamente em dispositivos móveis (smartphones), tablets e desktops. Adote a abordagem **Mobile-First**.

#### Filosofia Mobile-First

O design Mobile-First significa construir a interface começando pela menor tela e progressivamente adicionando complexidade para telas maiores. Esta abordagem garante:

- **Performance otimizada**: Dispositivos móveis carregam apenas o essencial
- **Experiência de usuário consistente**: A interface funciona em qualquer dispositivo
- **Manutenção simplificada**: Código CSS mais limpo e organizado
- **SEO melhorado**: Google prioriza sites mobile-friendly

```css
/* ❌ ERRADO: Desktop-First (começar do grande para o pequeno) */
.container { width: 1200px; }
@media (max-width: 768px) { .container { width: 100%; } }

/* ✅ CORRETO: Mobile-First (começar do pequeno para o grande) */
.container { width: 100%; }
@media (min-width: 768px) { .container { width: 1200px; } }
```

#### Breakpoints do Tailwind CSS

Utilize os breakpoints padrão do Tailwind CSS de forma consistente em todo o projeto:

| Breakpoint | Prefixo | Min-Width | Dispositivos Típicos |
|------------|---------|-----------|----------------------|
| Default | (nenhum) | 0px | Smartphones pequenos (iPhone SE, Galaxy S) |
| `sm` | `sm:` | 640px | Smartphones grandes (iPhone Pro Max, landscape) |
| `md` | `md:` | 768px | Tablets em portrait (iPad Mini, iPad) |
| `lg` | `lg:` | 1024px | Tablets landscape, laptops pequenos |
| `xl` | `xl:` | 1280px | Laptops, desktops |
| `2xl` | `2xl:` | 1536px | Monitores grandes, ultrawide |

**Exemplo de uso Mobile-First com Tailwind:**

```tsx
{/* Grid responsivo: 1 coluna no mobile, 2 no tablet, 3 no desktop, 4 em telas grandes */}
<div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4 gap-4">
  {items.map(item => <Card key={item.id} {...item} />)}
</div>

{/* Tipografia responsiva */}
<h1 className="text-xl sm:text-2xl md:text-3xl lg:text-4xl font-bold">
  Título Responsivo
</h1>

{/* Padding/Margin responsivo */}
<section className="px-4 sm:px-6 md:px-8 lg:px-12 py-6 md:py-10">
  Conteúdo com espaçamento adaptativo
</section>
```

#### Componentes de Layout Responsivo

**1. Sidebar Responsiva (Padrão Drawer/Hamburger)**

```tsx
// Mobile: Drawer que desliza da esquerda (overlay)
// Tablet: Sidebar colapsável (ícones apenas)
// Desktop: Sidebar expandida permanente

interface SidebarProps {
  isOpen: boolean;
  onClose: () => void;
}

export function Sidebar({ isOpen, onClose }: SidebarProps) {
  return (
    <>
      {/* Overlay para mobile */}
      <div
        className={cn(
          "fixed inset-0 bg-black/50 z-40 lg:hidden transition-opacity",
          isOpen ? "opacity-100" : "opacity-0 pointer-events-none"
        )}
        onClick={onClose}
      />

      {/* Sidebar */}
      <aside
        className={cn(
          "fixed left-0 top-0 h-full bg-background border-r z-50",
          "w-64 transform transition-transform duration-300 ease-in-out",
          // Mobile: slide in/out
          "lg:transform-none lg:translate-x-0",
          isOpen ? "translate-x-0" : "-translate-x-full"
        )}
      >
        {/* Conteúdo da sidebar */}
      </aside>
    </>
  );
}
```

**2. Header Responsivo**

```tsx
export function Header() {
  return (
    <header className="sticky top-0 z-30 border-b bg-background/95 backdrop-blur supports-[backdrop-filter]:bg-background/60">
      <div className="flex h-14 sm:h-16 items-center px-4 sm:px-6">
        {/* Botão hamburger - apenas mobile/tablet */}
        <Button
          variant="ghost"
          size="icon"
          className="lg:hidden mr-2"
          onClick={toggleSidebar}
        >
          <Menu className="h-5 w-5" />
        </Button>

        {/* Logo - oculta texto em telas pequenas */}
        <div className="flex items-center gap-2">
          <Logo className="h-6 w-6 sm:h-8 sm:w-8" />
          <span className="hidden sm:inline font-semibold">{{NOME_DO_PROJETO}}</span>
        </div>

        {/* Spacer */}
        <div className="flex-1" />

        {/* Ações - adapta para mobile */}
        <div className="flex items-center gap-2 sm:gap-4">
          <ThemeToggle />
          {/* Avatar com dropdown */}
          <UserNav />
        </div>
      </div>
    </header>
  );
}
```

**3. Layout Principal Responsivo**

```tsx
export function MainLayout({ children }: { children: React.ReactNode }) {
  const [sidebarOpen, setSidebarOpen] = useState(false);

  return (
    <div className="min-h-screen bg-background">
      <Sidebar isOpen={sidebarOpen} onClose={() => setSidebarOpen(false)} />

      {/* Conteúdo principal com margem para sidebar em desktop */}
      <div className="lg:pl-64">
        <Header onMenuClick={() => setSidebarOpen(true)} />

        <main className="p-4 sm:p-6 lg:p-8">
          {/* Container com max-width para legibilidade */}
          <div className="mx-auto max-w-7xl">
            {children}
          </div>
        </main>
      </div>
    </div>
  );
}
```

#### Padrões de UI Responsiva

**Tabelas Responsivas:**

```tsx
{/* Opção 1: Scroll horizontal em mobile */}
<div className="overflow-x-auto -mx-4 sm:mx-0">
  <table className="min-w-full">...</table>
</div>

{/* Opção 2: Cards em mobile, tabela em desktop */}
<div className="hidden md:block">
  <Table>...</Table>
</div>
<div className="md:hidden space-y-4">
  {data.map(item => <MobileCard key={item.id} {...item} />)}
</div>
```

**Formulários Responsivos:**

```tsx
<form className="space-y-4 sm:space-y-6">
  {/* Campos em linha no desktop, empilhados no mobile */}
  <div className="grid grid-cols-1 sm:grid-cols-2 gap-4">
    <FormField name="firstName" label="Nome" />
    <FormField name="lastName" label="Sobrenome" />
  </div>

  {/* Campo largo sempre */}
  <FormField name="email" label="Email" />

  {/* Botões: full-width no mobile, auto no desktop */}
  <div className="flex flex-col sm:flex-row gap-2 sm:justify-end">
    <Button variant="outline" className="w-full sm:w-auto">Cancelar</Button>
    <Button type="submit" className="w-full sm:w-auto">Salvar</Button>
  </div>
</form>
```

**Modais/Dialogs Responsivos:**

```tsx
<Dialog>
  <DialogContent className={cn(
    "sm:max-w-md",
    // Mobile: full-screen ou quase full-screen
    "max-h-[90vh] overflow-y-auto",
    "w-[calc(100%-2rem)] sm:w-full rounded-lg"
  )}>
    <DialogHeader>
      <DialogTitle>Título</DialogTitle>
    </DialogHeader>
    {/* Conteúdo */}
  </DialogContent>
</Dialog>
```

#### Imagens e Mídia Responsiva

```tsx
{/* Imagens responsivas com aspect-ratio */}
<div className="relative aspect-video w-full overflow-hidden rounded-lg">
  <img
    src={imageUrl}
    alt={description}
    className="object-cover w-full h-full"
    loading="lazy"
  />
</div>

{/* Avatar com tamanhos responsivos */}
<Avatar className="h-8 w-8 sm:h-10 sm:w-10">
  <AvatarImage src={user.avatar} />
  <AvatarFallback>{user.initials}</AvatarFallback>
</Avatar>
```

#### Touch-Friendly Design (Mobile UX)

Diretrizes essenciais para interfaces touch:

| Elemento | Tamanho Mínimo | Espaçamento | Justificativa |
|----------|----------------|-------------|---------------|
| Botões | 44x44px | 8px entre elementos | Área de toque confortável (Apple HIG) |
| Links/Ícones | 44x44px | 8px | Evita toques acidentais |
| Inputs | altura 44px | 12px vertical | Facilita digitação |
| Checkboxes | 24x24px | 16px | Visibilidade e precisão |

```tsx
{/* Botão touch-friendly */}
<Button className="min-h-[44px] min-w-[44px] px-4 py-2">
  Ação
</Button>

{/* Lista touch-friendly */}
<ul className="divide-y">
  {items.map(item => (
    <li key={item.id} className="py-3 px-4 min-h-[44px] flex items-center">
      {item.label}
    </li>
  ))}
</ul>
```

#### Hooks Úteis para Responsividade

```tsx
// hooks/useMediaQuery.ts
import { useState, useEffect } from 'react';

export function useMediaQuery(query: string): boolean {
  const [matches, setMatches] = useState(false);

  useEffect(() => {
    const media = window.matchMedia(query);
    setMatches(media.matches);

    const listener = (e: MediaQueryListEvent) => setMatches(e.matches);
    media.addEventListener('change', listener);
    return () => media.removeEventListener('change', listener);
  }, [query]);

  return matches;
}

// hooks/useBreakpoint.ts
export function useBreakpoint() {
  const isMobile = useMediaQuery('(max-width: 639px)');
  const isTablet = useMediaQuery('(min-width: 640px) and (max-width: 1023px)');
  const isDesktop = useMediaQuery('(min-width: 1024px)');

  return { isMobile, isTablet, isDesktop };
}

// Uso:
function MyComponent() {
  const { isMobile } = useBreakpoint();

  return isMobile ? <MobileView /> : <DesktopView />;
}
```

#### Testes de Responsividade

**Checklist obrigatório antes de considerar uma página pronta:**

- [ ] **iPhone SE (375px)** - Menor smartphone comum
- [ ] **iPhone 14 Pro (393px)** - Smartphone médio
- [ ] **iPhone 14 Pro Max (430px)** - Smartphone grande
- [ ] **iPad Mini (768px)** - Tablet pequeno
- [ ] **iPad Pro (1024px)** - Tablet grande
- [ ] **Laptop (1280px)** - Desktop comum
- [ ] **Desktop (1536px+)** - Monitor grande

**Ferramentas para teste:**

1. **Chrome DevTools**: Device Mode (Ctrl+Shift+M)
2. **Responsively App**: Visualiza múltiplos dispositivos simultaneamente
3. **BrowserStack**: Testes em dispositivos reais
4. **Lighthouse**: Audita acessibilidade e mobile-friendliness

**Testes automatizados com Playwright:**

```typescript
// e2e/responsive.spec.ts
import { test, expect } from '@playwright/test';

const viewports = [
  { name: 'mobile', width: 375, height: 667 },
  { name: 'tablet', width: 768, height: 1024 },
  { name: 'desktop', width: 1280, height: 720 },
];

for (const viewport of viewports) {
  test(`homepage renders correctly on ${viewport.name}`, async ({ page }) => {
    await page.setViewportSize({ width: viewport.width, height: viewport.height });
    await page.goto('/');

    // Verifica que elementos críticos estão visíveis
    await expect(page.locator('header')).toBeVisible();
    await expect(page.locator('main')).toBeVisible();

    // Screenshot para comparação visual
    await expect(page).toHaveScreenshot(`home-${viewport.name}.png`);
  });
}
```

#### Boas Práticas de Performance Mobile

```tsx
// 1. Lazy loading de imagens
<img loading="lazy" src={url} alt={alt} />

// 2. Lazy loading de componentes pesados
const HeavyChart = lazy(() => import('./HeavyChart'));

// 3. Evite layouts que causam reflow
// ❌ ERRADO: Altura calculada em JS
// ✅ CORRETO: Use CSS (aspect-ratio, min-height, etc.)

// 4. Prefira CSS transforms para animações
// ❌ ERRADO: animate({ left: '100px' })
// ✅ CORRETO: animate({ transform: 'translateX(100px)' })

// 5. Use will-change com moderação para elementos animados
<div className="will-change-transform transition-transform">
  Conteúdo animado
</div>
```

#### Acessibilidade em Dispositivos Móveis

```tsx
// 1. Meta viewport correta (já configurada no index.html do Vite)
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

// 2. Zoom não deve ser desabilitado (acessibilidade)
// ❌ NUNCA faça: maximum-scale=1.0, user-scalable=no

// 3. Labels visíveis para inputs (não apenas placeholder)
<label htmlFor="email" className="block text-sm font-medium">
  Email
</label>
<input id="email" type="email" placeholder="seu@email.com" />

// 4. Focus states visíveis para navegação por teclado/switch
<Button className="focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2">
  Ação
</Button>
```

---

## ⚙️ BACK-END ({{NOME_DO_PROJETO}}-api)

### Stack Tecnológica

1. **Java 21 LTS** - Versão com suporte longo e features modernas
   - Virtual Threads (Project Loom)
   - Pattern Matching
   - Records

2. **Spring Boot 3.2+** - Framework principal
   - Spring Web (REST APIs)
   - Spring Data JPA (persistência)
   - Spring Security (autenticação/autorização)
   - Spring Validation (validação de DTOs)
   - Spring Cache (cache abstraction)

3. **PostgreSQL 15+** - Banco de dados relacional principal
   - Suporte a JSON/JSONB
   - Full-text search
   - Extensões úteis

4. **Redis** - Cache distribuído e sessões
   - Cache de dados frequentes
   - Rate limiting
   - Armazenamento de refresh tokens

5. **OAuth2 + JWT** - Autenticação e Autorização
   - Access Token (curta duração: 15-30 min)
   - Refresh Token (longa duração: 7-30 dias)
   - Stateless authentication

6. **Swagger/OpenAPI 3** - Documentação da API
   - SpringDoc OpenAPI
   - UI interativa em /swagger-ui.html

7. **Flyway** - Migrations de banco de dados
   - Versionamento de schema
   - Rollback quando necessário

8. **MapStruct** - Mapeamento de entidades/DTOs
   - Performance superior ao ModelMapper
   - Compile-time mapping

9. **Lombok** - Redução de boilerplate
   - @Data, @Builder, @Slf4j, etc.

10. **Spring Boot Actuator** - Health checks e monitoramento
    - Endpoints de liveness e readiness
    - Métricas da aplicação
    - Informações de build

11. **Context7 MCP** - Documentação atualizada das bibliotecas Java/Spring
    - **⚠️ CRÍTICO: Consulte SEMPRE antes de implementar qualquer código**
    - Veja a seção "INSTRUÇÃO CRÍTICA: USO DO CONTEXT7 MCP" no início do prompt
    - Referência: https://context7.com/

### Arquitetura Multi-Module Maven (Profissional)

Projeto modularizado com separação clara de responsabilidades. Cada módulo é um sub-projeto Maven independente com seu próprio `pom.xml`.

```
{{NOME_DO_PROJETO}}-api/                          # Parent POM (multi-module)
├── pom.xml                                        # Parent: define módulos, versões, dependências comuns
│
├── {{projeto}}-core/                              # 🏗️ DOMAIN (núcleo da aplicação)
│   ├── pom.xml
│   └── src/main/java/com/{{empresa}}/{{projeto}}/core/
│       ├── entity/                                # JPA Entities
│       │   ├── Merchant.java
│       │   ├── User.java
│       │   ├── Group.java
│       │   ├── Rule.java
│       │   └── RefreshToken.java
│       ├── repository/                            # Interfaces de Repository (Spring Data JPA)
│       │   ├── MerchantRepository.java
│       │   ├── UserRepository.java
│       │   ├── GroupRepository.java
│       │   ├── RuleRepository.java
│       │   └── RefreshTokenRepository.java
│       ├── service/                               # Interfaces e implementações de negócio
│       │   ├── AuthService.java
│       │   ├── UserService.java
│       │   ├── GroupService.java
│       │   ├── MerchantService.java
│       │   └── TokenService.java
│       ├── exception/                             # Exceções de domínio
│       │   ├── BusinessException.java
│       │   ├── ResourceNotFoundException.java
│       │   └── UnauthorizedException.java
│       └── util/                                  # Utilitários de domínio
│           ├── Constants.java
│           └── DateUtils.java
│
├── {{projeto}}-infrastructure/                    # 🔧 INFRASTRUCTURE (implementações externas)
│   ├── pom.xml                                    # Depende de: core
│   └── src/
│       ├── main/java/com/{{empresa}}/{{projeto}}/infrastructure/
│       │   ├── persistence/                       # Configurações JPA, custom queries
│       │   │   └── CustomUserRepositoryImpl.java
│       │   ├── cache/                             # Configuração Redis
│       │   │   └── RedisConfig.java
│       │   └── tenant/                            # Multi-tenant (se aplicável)
│       │       ├── TenantContext.java
│       │       ├── TenantFilter.java
│       │       └── TenantAspect.java
│
├── {{projeto}}-security/                          # 🔐 SECURITY (autenticação e autorização)
│   ├── pom.xml                                    # Depende de: core
│   └── src/main/java/com/{{empresa}}/{{projeto}}/security/
│       ├── config/
│       │   └── SecurityConfig.java                # SecurityFilterChain, CORS, CSRF
│       ├── jwt/
│       │   ├── JwtTokenProvider.java
│       │   ├── JwtAuthenticationFilter.java
│       │   └── JwtConfig.java
│       ├── userdetails/
│       │   └── UserDetailsServiceImpl.java
│       └── SecurityUtils.java
│
├── {{projeto}}-web/                               # 🌐 WEB (camada de apresentação REST)
│   ├── pom.xml                                    # Depende de: core, security
│   └── src/main/java/com/{{empresa}}/{{projeto}}/web/
│       ├── controller/                            # REST Controllers
│       │   ├── AuthController.java
│       │   ├── UserController.java
│       │   ├── GroupController.java
│       │   └── MerchantController.java
│       ├── dto/                                   # Data Transfer Objects
│       │   ├── request/
│       │   │   ├── LoginRequest.java
│       │   │   ├── UserCreateRequest.java
│       │   │   └── UserUpdateRequest.java
│       │   └── response/
│       │       ├── UserResponse.java
│       │       ├── TokenResponse.java
│       │       └── PageResponse.java
│       ├── mapper/                                # MapStruct Mappers (Entity ↔ DTO)
│       │   ├── UserMapper.java
│       │   ├── GroupMapper.java
│       │   └── MerchantMapper.java
│       ├── exception/                             # Exception handlers REST
│       │   └── GlobalExceptionHandler.java        # @ControllerAdvice
│       └── config/
│           └── SwaggerConfig.java                 # SpringDoc OpenAPI
│
├── {{projeto}}-app/                               # 🚀 APP (módulo bootável)
│   ├── pom.xml                                    # Depende de: core, infrastructure, security, web
│   └── src/
│       ├── main/java/com/{{empresa}}/{{projeto}}/
│       │   ├── {{Projeto}}Application.java        # @SpringBootApplication (main class)
│       │   └── config/                            # Configs de bootstrap
│       │       ├── CorsConfig.java
│       │       └── WebConfig.java
│       ├── main/resources/
│       │   ├── application.yml
│       │   ├── application-dev.yml
│       │   ├── application-prod.yml
│       │   └── db/migration/                      # Flyway migrations (junto do datasource config)
│       │       ├── V1__create_merchant_table.sql
│       │       ├── V2__create_user_tables.sql
│       │       └── V3__insert_default_data.sql
│       └── test/                                  # Testes de integração
│
└── Dockerfile
```

### Grafo de dependências entre módulos:

```
                    ┌─────────────┐
                    │  {{projeto}} │
                    │     -app     │  (bootável - depende de todos)
                    └──────┬──────┘
                           │
          ┌────────────────┼────────────────┐
          │                │                │
   ┌──────▼──────┐  ┌─────▼──────┐  ┌──────▼──────┐
   │  {{projeto}} │  │ {{projeto}} │  │  {{projeto}} │
   │     -web     │  │-infrastruc.│  │  -security   │
   └──────┬──────┘  └─────┬──────┘  └──────┬──────┘
          │                │                │
          └────────────────┼────────────────┘
                           │
                    ┌──────▼──────┐
                    │  {{projeto}} │
                    │    -core     │  (sem dependências internas)
                    └─────────────┘
```

### Responsabilidades de cada módulo:

| Módulo | Responsabilidade | Depende de |
|--------|------------------|------------|
| `{{projeto}}-core` | Entities, repository interfaces, services de negócio, exceções de domínio, utilitários | Nenhum módulo interno |
| `{{projeto}}-infrastructure` | Implementações JPA, configs Redis, integrações externas, multi-tenant, TenantContext | `core` |
| `{{projeto}}-security` | JWT provider/filter, SecurityConfig, UserDetailsService, RBAC | `core`, `infrastructure` |
| `{{projeto}}-web` | REST controllers, DTOs (request/response), MapStruct mappers, Swagger, @ControllerAdvice | `core`, `security` |
| `{{projeto}}-app` | Main class, application.yml, Flyway migrations, configs de bootstrap, wiring de todos os módulos | Todos |

### Regras de dependência (IMPORTANTE):

1. **`core` é independente** — não importa nenhum outro módulo do projeto. É o domínio puro.
2. **`infrastructure`** depende apenas de `core`
3. **`security`** depende de `core` e `infrastructure` (para acessar `TenantContext` e extrair tenant do JWT)
4. **`web`** depende de `core` e `security` (para annotations de segurança nos controllers)
5. **`app`** é o único módulo bootável — agrega todos os outros via dependência Maven
6. **NUNCA** crie dependências circulares entre módulos

### Convenção de Nomenclatura do Banco de Dados

- **Prefixo de tabelas**: `t_` (exemplo: `t_merchant`, `t_user`)
- **Nomenclatura**: snake_case
- **Chaves primárias**: `id` (UUID ou BIGINT auto-increment)
- **Chaves estrangeiras**: `{tabela}_id` (exemplo: `merchant_id`)
- **Timestamps**: `created_at`, `updated_at`
- **Soft delete**: `deleted_at` (nullable)

---

## 🏢 ARQUITETURA DE TENANCY

> **IMPORTANTE**: Esta seção se adapta conforme a escolha de arquitetura feita na configuração inicial.

---

### Comparativo das Estratégias de Tenancy

| Aspecto | Multi-Tenant | Single-Tenant | Híbrido |
|---------|--------------|---------------|---------|
| **Banco de Dados** | Um banco, isolamento por `merchant_id` | Um banco por cliente | Ambos |
| **Custo de Infra** | Baixo | Alto | Médio-Alto |
| **Isolamento de Dados** | Lógico (coluna) | Físico (banco separado) | Configurável |
| **Manutenção** | Centralizada | Individual por cliente | Complexa |
| **Escalabilidade** | Horizontal fácil | Vertical por cliente | Complexa |
| **Compliance** | Pode não atender alguns requisitos | Total isolamento | Flexível |
| **Customização** | Limitada | Total por cliente | Flexível |
| **Deploy** | Único | Múltiplos | Múltiplos |

---

### OPÇÃO A: Multi-Tenant (Isolamento por merchant_id)

**Estratégia**: Um único banco de dados com isolamento lógico através da coluna `merchant_id` em todas as tabelas de negócio.

```
┌─────────────────────────────────────────────────────────────────┐
│                    BANCO DE DADOS ÚNICO                         │
│  ┌───────────────────────────────────────────────────────────┐  │
│  │                     Schema: public                        │  │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐       │  │
│  │  │ t_merchant  │  │   t_user    │  │  t_order    │  ...  │  │
│  │  │             │  │ merchant_id │  │ merchant_id │       │  │
│  │  └─────────────┘  └─────────────┘  └─────────────┘       │  │
│  │                                                           │  │
│  │  Merchant A (id=1): Vê apenas seus dados                  │  │
│  │  Merchant B (id=2): Vê apenas seus dados                  │  │
│  │  Merchant C (id=3): Vê apenas seus dados                  │  │
│  └───────────────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────────────┘
```

#### Tabela t_merchant (Multi-Tenant)

```sql
CREATE TABLE t_merchant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,   -- Identificador único para URLs
    document VARCHAR(20),                 -- CNPJ/CPF
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    logo_url VARCHAR(500),
    is_active BOOLEAN DEFAULT true,
    plan_type VARCHAR(50) DEFAULT 'basic', -- Plano: basic, pro, enterprise
    settings JSONB DEFAULT '{}',          -- Configurações customizáveis
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Índices para performance
CREATE INDEX idx_merchant_slug ON t_merchant(slug);
CREATE INDEX idx_merchant_active ON t_merchant(is_active) WHERE deleted_at IS NULL;
CREATE INDEX idx_merchant_plan ON t_merchant(plan_type);
```

#### Tratamento de JSONB no Hibernate 6

Para campos JSONB funcionarem corretamente com Hibernate 6, adicione a anotação `@JdbcTypeCode`:

```java
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@JdbcTypeCode(SqlTypes.JSON)
@Column(columnDefinition = "jsonb")
private String settings = "{}";
```

> **IMPORTANTE**: Apenas `@Column(columnDefinition = "jsonb")` não é suficiente no Hibernate 6. Sem `@JdbcTypeCode`, o Hibernate tentará inserir o valor como VARCHAR, causando erro de tipo no PostgreSQL.

#### Implementação do Filtro Multi-Tenant no Spring Boot

**1. TenantContext (ThreadLocal para armazenar o tenant atual)**

```java
public class TenantContext {
    private static final ThreadLocal<UUID> currentTenant = new ThreadLocal<>();

    public static void setCurrentTenant(UUID tenantId) {
        currentTenant.set(tenantId);
    }

    public static UUID getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
```

**2. TenantFilter (Extrai tenant do JWT ou header)**

```java
@Component
public class TenantFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            // Extrair merchant_id do JWT (claim) ou header X-Tenant-ID
            UUID tenantId = extractTenantFromRequest(request);
            if (tenantId != null) {
                TenantContext.setCurrentTenant(tenantId);
            }
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }
}
```

**3. Hibernate Filter para aplicar automaticamente**

**IMPORTANTE**: Defina `@FilterDef` APENAS UMA VEZ no `package-info.java` do pacote de entidades:

```java
// src/main/java/com/{{empresa}}/{{projeto}}/core/entity/package-info.java
@FilterDef(
    name = "tenantFilter",
    parameters = @ParamDef(name = "merchantId", type = java.util.UUID.class)
)
package com.{{empresa}}.{{projeto}}.core.entity;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
```

Nas entidades, use APENAS `@Filter` (sem `@FilterDef`):

```java
@Entity
@Table(name = "t_user")
@Filter(name = "tenantFilter", condition = "merchant_id = :merchantId")
public class User {
    // ...
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;
}
```

> **ERRO COMUM**: Duplicar `@FilterDef` em múltiplas entidades causa erro de inicialização: "Multiple @FilterDef annotations for 'tenantFilter'".

**4. Ativar o filtro automaticamente via AOP**

```java
@Aspect
@Component
public class TenantAspect {

    @Autowired
    private EntityManager entityManager;

    @Before("execution(* com.{{empresa}}.{{projeto}}.core.repository.*.*(..))")
    public void enableTenantFilter() {
        UUID tenantId = TenantContext.getCurrentTenant();
        if (tenantId != null) {
            Session session = entityManager.unwrap(Session.class);
            session.enableFilter("tenantFilter")
                   .setParameter("merchantId", tenantId);
        }
    }
}
```

#### Estratégia de Identificação do Tenant

| Método | Descrição | Quando Usar |
|--------|-----------|-------------|
| **JWT Claim** | `merchant_id` incluído no token | Recomendado para APIs |
| **Header X-Tenant-ID** | Header HTTP customizado | APIs internas, microserviços |
| **Subdomínio** | `cliente.meusaas.com` | SaaS públicos, white-label |
| **Path** | `/api/v1/tenants/{id}/...` | APIs REST puras |

---

### OPÇÃO B: Single-Tenant / On-Premise

**Estratégia**: Cada cliente possui seu próprio banco de dados (ou instância completa da aplicação).

```
┌─────────────────┐  ┌─────────────────┐  ┌─────────────────┐
│   Cliente A     │  │   Cliente B     │  │   Cliente C     │
│  ┌───────────┐  │  │  ┌───────────┐  │  │  ┌───────────┐  │
│  │  DB: A    │  │  │  │  DB: B    │  │  │  │  DB: C    │  │
│  │  App: A   │  │  │  │  App: B   │  │  │  │  App: C   │  │
│  └───────────┘  │  │  └───────────┘  │  │  └───────────┘  │
└─────────────────┘  └─────────────────┘  └─────────────────┘
     Instância           Instância           Instância
     Dedicada            Dedicada            Dedicada
```

#### Configuração Single-Tenant

**Não há tabela t_merchant** - as configurações do cliente ficam em variáveis de ambiente ou arquivo de configuração.

```yaml
# application-cliente-a.yml
app:
  tenant:
    name: "Cliente A"
    document: "12.345.678/0001-90"
    settings:
      logo-url: "https://cliente-a.com/logo.png"
      primary-color: "#3B82F6"

spring:
  datasource:
    url: jdbc:postgresql://db-cliente-a:5432/cliente_a_db
    username: cliente_a_user
    password: ${DB_PASSWORD}
```

#### Estrutura de Tabelas (Sem merchant_id)

```sql
-- Tabelas NÃO possuem merchant_id
CREATE TABLE t_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    -- ... demais campos
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

### OPÇÃO C: Arquitetura Híbrida

**Estratégia**: Combina Multi-Tenant para clientes regulares com Single-Tenant para clientes enterprise.

```
┌─────────────────────────────────────────────────────────────────┐
│                    PLATAFORMA HÍBRIDA                           │
│                                                                 │
│  ┌─────────────────────────────────────────┐                   │
│  │     MULTI-TENANT (Clientes Básicos)     │                   │
│  │  ┌─────────┐ ┌─────────┐ ┌─────────┐   │                   │
│  │  │Client A │ │Client B │ │Client C │   │   Banco           │
│  │  │(id=1)   │ │(id=2)   │ │(id=3)   │   │   Compartilhado   │
│  │  └─────────┘ └─────────┘ └─────────┘   │                   │
│  └─────────────────────────────────────────┘                   │
│                                                                 │
│  ┌─────────────────┐  ┌─────────────────┐                      │
│  │   ENTERPRISE X   │  │   ENTERPRISE Y   │  Bancos            │
│  │   (Dedicado)     │  │   (Dedicado)     │  Dedicados         │
│  └─────────────────┘  └─────────────────┘                      │
└─────────────────────────────────────────────────────────────────┘
```

#### Tabela t_merchant (Híbrido)

```sql
CREATE TABLE t_merchant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    document VARCHAR(20),
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    logo_url VARCHAR(500),
    is_active BOOLEAN DEFAULT true,
    plan_type VARCHAR(50) DEFAULT 'basic',
    -- CAMPOS ESPECÍFICOS PARA HÍBRIDO
    tenancy_type VARCHAR(20) DEFAULT 'SHARED',  -- SHARED ou DEDICATED
    dedicated_db_url VARCHAR(500),               -- URL do banco dedicado (se DEDICATED)
    dedicated_db_schema VARCHAR(100),            -- Schema dedicado (se usar schema isolation)
    settings JSONB DEFAULT '{}',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

-- Índices
CREATE INDEX idx_merchant_slug ON t_merchant(slug);
CREATE INDEX idx_merchant_tenancy ON t_merchant(tenancy_type);
```

#### DataSource Routing para Híbrido

```java
public class TenantRoutingDataSource extends AbstractRoutingDataSource {

    @Autowired
    private MerchantRepository merchantRepository;

    @Override
    protected Object determineCurrentLookupKey() {
        UUID tenantId = TenantContext.getCurrentTenant();
        if (tenantId == null) {
            return "default";
        }

        Merchant merchant = merchantRepository.findById(tenantId).orElse(null);
        if (merchant != null && "DEDICATED".equals(merchant.getTenancyType())) {
            return "tenant_" + tenantId;  // Retorna chave do DataSource dedicado
        }
        return "default";  // Usa o banco compartilhado
    }
}
```

---

### Merchant Padrão (Seed) - Para Multi-Tenant e Híbrido

```sql
INSERT INTO t_merchant (id, name, slug, email, is_active, plan_type, tenancy_type)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    'Default Merchant',
    'default',
    '{{EMAIL_ADMIN}}',
    true,
    'enterprise',
    'SHARED'  -- ou 'DEDICATED' para híbrido
);
```

---

## 👥 ESTRUTURA DE USUÁRIOS E PERMISSÕES

### Modelo RBAC (Role-Based Access Control)

```
t_merchant (Locatário)
    │
    └── t_user (Usuários)
            │
            └── t_user_group (N:N)
                    │
                    └── t_group (Grupos/Perfis)
                            │
                            └── t_group_rule (N:N)
                                    │
                                    └── t_rule (Permissões)
```

### Tabelas

```sql
-- Grupos/Perfis
CREATE TABLE t_group (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    merchant_id UUID NOT NULL REFERENCES t_merchant(id),
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    is_system BOOLEAN DEFAULT false,   -- Perfis do sistema não podem ser deletados
    is_active BOOLEAN DEFAULT true,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UNIQUE(merchant_id, name)
);

-- Regras/Permissões
CREATE TABLE t_rule (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    code VARCHAR(100) NOT NULL UNIQUE, -- Ex: USER_CREATE, USER_READ
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    module VARCHAR(50) NOT NULL,       -- Ex: USER, MERCHANT, REPORT
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Associação Grupo <-> Regra
CREATE TABLE t_group_rule (
    group_id UUID NOT NULL REFERENCES t_group(id) ON DELETE CASCADE,
    rule_id UUID NOT NULL REFERENCES t_rule(id) ON DELETE CASCADE,
    PRIMARY KEY (group_id, rule_id)
);

-- Usuários
CREATE TABLE t_user (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    merchant_id UUID NOT NULL REFERENCES t_merchant(id),
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(500),
    phone VARCHAR(20),
    is_active BOOLEAN DEFAULT true,
    email_verified_at TIMESTAMP,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    UNIQUE(merchant_id, email)
);

-- Associação Usuário <-> Grupo
CREATE TABLE t_user_group (
    user_id UUID NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    group_id UUID NOT NULL REFERENCES t_group(id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, group_id)
);

-- Refresh Tokens (opcional - pode usar Redis)
CREATE TABLE t_refresh_token (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES t_user(id) ON DELETE CASCADE,
    token VARCHAR(500) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    revoked_at TIMESTAMP
);

-- IMPORTANTE: Se usar BaseEntity com updated_at, inclua a coluna em TODAS as tabelas

-- Índices
CREATE INDEX idx_user_merchant ON t_user(merchant_id);
CREATE INDEX idx_user_email ON t_user(email);
CREATE INDEX idx_group_merchant ON t_group(merchant_id);
```

### Dados Padrão (Seeds)

```sql
-- Regras padrão do sistema
INSERT INTO t_rule (id, code, name, module) VALUES
    (gen_random_uuid(), 'USER_CREATE', 'Criar usuários', 'USER'),
    (gen_random_uuid(), 'USER_READ', 'Visualizar usuários', 'USER'),
    (gen_random_uuid(), 'USER_UPDATE', 'Editar usuários', 'USER'),
    (gen_random_uuid(), 'USER_DELETE', 'Remover usuários', 'USER'),
    (gen_random_uuid(), 'GROUP_CREATE', 'Criar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_READ', 'Visualizar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_UPDATE', 'Editar grupos', 'GROUP'),
    (gen_random_uuid(), 'GROUP_DELETE', 'Remover grupos', 'GROUP'),
    (gen_random_uuid(), 'MERCHANT_READ', 'Visualizar merchant', 'MERCHANT'),
    (gen_random_uuid(), 'MERCHANT_UPDATE', 'Editar merchant', 'MERCHANT'),
    (gen_random_uuid(), 'ADMIN_ACCESS', 'Acesso administrativo', 'SYSTEM');

-- Grupo Admin para o merchant padrão
INSERT INTO t_group (id, merchant_id, name, description, is_system)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    'Acesso total ao sistema',
    true
);

-- Associar todas as regras ao grupo Admin
INSERT INTO t_group_rule (group_id, rule_id)
SELECT '00000000-0000-0000-0000-000000000001', id FROM t_rule;

-- Usuário Admin padrão (senha: Admin@123)
INSERT INTO t_user (id, merchant_id, name, email, password_hash, is_active, email_verified_at)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001',
    'Administrador',
    '{{EMAIL_ADMIN}}',
    '$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/X4.VTtYLgVJay2Kbu', -- BCrypt
    true,
    CURRENT_TIMESTAMP
);

-- Associar admin ao grupo Admin
INSERT INTO t_user_group (user_id, group_id)
VALUES (
    '00000000-0000-0000-0000-000000000001',
    '00000000-0000-0000-0000-000000000001'
);
```

---

## 🔐 ENDPOINTS DA API

### Autenticação

```
POST   /api/v1/auth/login          # Login (retorna access + refresh token)
POST   /api/v1/auth/register       # Cadastro de novo usuário
POST   /api/v1/auth/refresh        # Renovar access token
POST   /api/v1/auth/logout         # Invalidar refresh token
POST   /api/v1/auth/forgot-password
POST   /api/v1/auth/reset-password
```

### Usuário Autenticado

```
GET    /api/v1/me                  # Dados do usuário logado
PUT    /api/v1/me                  # Atualizar perfil
PUT    /api/v1/me/password         # Alterar senha
PUT    /api/v1/me/avatar           # Upload de avatar
```

### Gestão de Usuários (Admin)

```
GET    /api/v1/users               # Listar usuários (paginado)
GET    /api/v1/users/{id}          # Buscar usuário
POST   /api/v1/users               # Criar usuário
PUT    /api/v1/users/{id}          # Atualizar usuário
DELETE /api/v1/users/{id}          # Soft delete usuário
```

### Grupos

```
GET    /api/v1/groups              # Listar grupos
GET    /api/v1/groups/{id}         # Buscar grupo
POST   /api/v1/groups              # Criar grupo
PUT    /api/v1/groups/{id}         # Atualizar grupo
DELETE /api/v1/groups/{id}         # Remover grupo
```

### Regras

```
GET    /api/v1/rules               # Listar todas as regras disponíveis
```

### Health Check & Monitoramento (Actuator)

```
GET    /actuator/health            # Status geral da aplicação
GET    /actuator/health/liveness   # Probe de liveness (aplicação está rodando?)
GET    /actuator/health/readiness  # Probe de readiness (aplicação está pronta?)
GET    /actuator/info              # Informações da aplicação (versão, build, etc.)
GET    /actuator/metrics           # Métricas da aplicação (opcional, proteger em prod)
```

**Configuração recomendada no `application.yml`:**

```yaml
management:
  endpoints:
    web:
      exposure:
        include: health, info, metrics
      base-path: /actuator
  endpoint:
    health:
      show-details: when_authorized  # ou 'always' em dev
      probes:
        enabled: true                # Habilita liveness e readiness
  health:
    db:
      enabled: true                  # Verifica conexão com banco
    redis:
      enabled: true                  # Verifica conexão com Redis
  info:
    env:
      enabled: true
    build:
      enabled: true
    git:
      enabled: true
      mode: full

# Informações da aplicação
info:
  app:
    name: {{NOME_DO_PROJETO}}
    description: {{DESCRICAO}}
    version: '@project.version@'
  java:
    version: '@java.version@'
```

**Resposta exemplo do `/actuator/health`:**

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "PostgreSQL",
        "validationQuery": "isValid()"
      }
    },
    "redis": {
      "status": "UP",
      "details": {
        "version": "7.0.0"
      }
    },
    "diskSpace": {
      "status": "UP"
    }
  }
}
```

---

## 📦 DEPENDÊNCIAS (Multi-Module Maven)

### Parent POM (`{{NOME_DO_PROJETO}}-api/pom.xml`)

```xml
<groupId>com.{{empresa}}</groupId>
<artifactId>{{projeto}}-parent</artifactId>
<version>0.0.1-SNAPSHOT</version>
<packaging>pom</packaging>

<modules>
    <module>{{projeto}}-core</module>
    <module>{{projeto}}-infrastructure</module>
    <module>{{projeto}}-security</module>
    <module>{{projeto}}-web</module>
    <module>{{projeto}}-app</module>
</modules>

<properties>
    <java.version>21</java.version>
    <spring-boot.version>3.2.5</spring-boot.version>
    <flyway.version>10.10.0</flyway.version>
    <mapstruct.version>1.5.5.Final</mapstruct.version>
    <jjwt.version>0.12.5</jjwt.version>
    <springdoc.version>2.3.0</springdoc.version>
</properties>

<!-- dependencyManagement: centraliza versões para todos os módulos -->
<dependencyManagement>
    <dependencies>
        <!-- Módulos internos -->
        <dependency>
            <groupId>com.{{empresa}}</groupId>
            <artifactId>{{projeto}}-core</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>com.{{empresa}}</groupId>
            <artifactId>{{projeto}}-infrastructure</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>com.{{empresa}}</groupId>
            <artifactId>{{projeto}}-security</artifactId>
            <version>${project.version}</version>
        </dependency>
        <dependency>
            <groupId>com.{{empresa}}</groupId>
            <artifactId>{{projeto}}-web</artifactId>
            <version>${project.version}</version>
        </dependency>
        <!-- Flyway PostgreSQL (IMPORTANTE: definir versão aqui) -->
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-database-postgresql</artifactId>
            <version>${flyway.version}</version>
        </dependency>
    </dependencies>
</dependencyManagement>

<!-- Dependências comuns a TODOS os módulos -->
<dependencies>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Dependências por módulo:

| Módulo | Dependências principais |
|--------|------------------------|
| **`{{projeto}}-core`** | `spring-boot-starter-data-jpa`, `spring-boot-starter-validation`, `lombok` |
| **`{{projeto}}-infrastructure`** | `{{projeto}}-core`, `postgresql`, `spring-boot-starter-data-redis`, `spring-boot-starter-cache`, `spring-boot-starter-aop` |
| **`{{projeto}}-security`** | `{{projeto}}-core`, `{{projeto}}-infrastructure`, `spring-boot-starter-security`, `jjwt-api/impl/jackson` |
| **`{{projeto}}-web`** | `{{projeto}}-core`, `{{projeto}}-security`, `spring-boot-starter-web`, `springdoc-openapi`, `mapstruct` |
| **`{{projeto}}-app`** | Todos os módulos acima, `spring-boot-starter-actuator`, `flyway-core`, `flyway-database-postgresql` (versão definida no parent POM) |

### Exemplo: `{{projeto}}-app/pom.xml` (módulo bootável)

```xml
<parent>
    <groupId>com.{{empresa}}</groupId>
    <artifactId>{{projeto}}-parent</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</parent>

<artifactId>{{projeto}}-app</artifactId>

<dependencies>
    <dependency>
        <groupId>com.{{empresa}}</groupId>
        <artifactId>{{projeto}}-core</artifactId>
    </dependency>
    <dependency>
        <groupId>com.{{empresa}}</groupId>
        <artifactId>{{projeto}}-infrastructure</artifactId>
    </dependency>
    <dependency>
        <groupId>com.{{empresa}}</groupId>
        <artifactId>{{projeto}}-security</artifactId>
    </dependency>
    <dependency>
        <groupId>com.{{empresa}}</groupId>
        <artifactId>{{projeto}}-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

> **IMPORTANTE**: O `spring-boot-maven-plugin` só deve estar no módulo `{{projeto}}-app`, pois é o único módulo bootável.

---

## 🐳 DOCKER COMPOSE (Desenvolvimento)

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:15-alpine
    container_name: {{projeto}}-postgres
    environment:
      POSTGRES_DB: {{projeto}}_db
      POSTGRES_USER: {{projeto}}_user
      POSTGRES_PASSWORD: {{projeto}}_pass
    ports:
      - "{{PORTA_POSTGRES}}:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U {{projeto}}_user -d {{projeto}}_db"]
      interval: 10s
      timeout: 5s
      retries: 5

  redis:
    image: redis:7-alpine
    container_name: {{projeto}}-redis
    ports:
      - "{{PORTA_REDIS}}:6379"
    volumes:
      - redis_data:/data
    healthcheck:
      test: ["CMD", "redis-cli", "ping"]
      interval: 10s
      timeout: 5s
      retries: 5

volumes:
  postgres_data:
  redis_data:
```

**Docker Compose com a API (opcional):**

```yaml
  api:
    build: ./{{NOME_DO_PROJETO}}-api
    container_name: {{projeto}}-api
    ports:
      - "{{PORTA_API}}:8080"
    environment:
      SPRING_PROFILES_ACTIVE: dev
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/{{projeto}}_db
      SPRING_DATASOURCE_USERNAME: {{projeto}}_user
      SPRING_DATASOURCE_PASSWORD: {{projeto}}_pass
      SPRING_REDIS_HOST: redis
      SPRING_REDIS_PORT: 6379
    depends_on:
      postgres:
        condition: service_healthy
      redis:
        condition: service_healthy
    healthcheck:
      test: ["CMD", "curl", "-f", "http://localhost:8080/actuator/health/liveness"]
      interval: 30s
      timeout: 10s
      retries: 3
      start_period: 40s
```

> **Nota**: O `Dockerfile` deve fazer o build a partir do parent POM e copiar o JAR do módulo `{{projeto}}-app/target/`.

---

## 🌐 CORS para Desenvolvimento

Configure múltiplas origens no `application-dev.yml` para flexibilidade durante desenvolvimento:

```yaml
# application-dev.yml
app:
  cors:
    allowed-origins: http://localhost:3000,http://localhost:3001,http://localhost:5173
```

E no `CorsConfig.java`:

```java
@Value("${app.cors.allowed-origins:http://localhost:3000}")
private String allowedOrigins;

@Bean
public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.setAllowedOrigins(Arrays.asList(allowedOrigins.split(",")));
    config.setAllowedHeaders(List.of("*"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    // ...
}
```

> **Dica**: Inclua as portas alternativas comuns (3000, 3001, 5173) para evitar bloqueios de CORS quando precisar mudar a porta do frontend.

---

## 📝 ARQUIVOS DE CONFIGURAÇÃO

### CLAUDE.md (Raiz do projeto)

```markdown
# {{NOME_DO_PROJETO}}

## Descrição
{{DESCRICAO}}

## 🏗️ Arquitetura de Tenancy
**Tipo**: {{ARQUITETURA}}

<!-- Se MULTI_TENANT -->
> **Multi-Tenant**: Isolamento por `merchant_id`. Todas as tabelas de negócio possuem `merchant_id`. Filtro automático via Hibernate Filters. NUNCA esqueça de filtrar por `merchant_id` em queries customizadas!

<!-- Se SINGLE_TENANT -->
> **Single-Tenant**: Instância dedicada por cliente. Não há `merchant_id`. Configurações via variáveis de ambiente.

<!-- Se HYBRID -->
> **Híbrido**: Verificar `tenancy_type` do merchant (SHARED ou DEDICATED). DataSource routing automático para enterprise. Clientes SHARED compartilham banco principal.

## Estrutura do Projeto
- `{{NOME_DO_PROJETO}}-web/`: Front-end React 18 + TypeScript + Vite
- `{{NOME_DO_PROJETO}}-api/`: Back-end Java 21 + Spring Boot (multi-module Maven)
  - `{{projeto}}-core/`: Domain (entities, repositories, services, exceptions)
  - `{{projeto}}-infrastructure/`: JPA, Redis, multi-tenant
  - `{{projeto}}-security/`: JWT, SecurityConfig, RBAC
  - `{{projeto}}-web/`: REST controllers, DTOs, mappers, Swagger
  - `{{projeto}}-app/`: Main class, configs, application.yml, Flyway (bootável)

## Comandos Rápidos
```bash
# Front-end
cd {{NOME_DO_PROJETO}}-web && npm run dev

# Back-end (build completo)
cd {{NOME_DO_PROJETO}}-api && ./mvnw clean install

# Back-end (rodar)
cd {{NOME_DO_PROJETO}}-api && ./mvnw spring-boot:run -pl {{projeto}}-app -Dspring-boot.run.profiles=dev

# Docker
docker-compose up -d
```

## ⚠️ Context7 MCP
**SEMPRE consulte o Context7 antes de implementar código!** Valide que a versão da documentação corresponde ao `package.json` / `pom.xml`.

## Credenciais de Desenvolvimento
- **Admin**: {{EMAIL_ADMIN}} / Admin@123
- **PostgreSQL**: {{projeto}}_user / {{projeto}}_pass (porta: {{PORTA_POSTGRES}})
- **Redis**: localhost:{{PORTA_REDIS}}

## URLs
- Front-end: http://localhost:{{PORTA_FRONT}}
- API: http://localhost:{{PORTA_API}}
- Swagger: http://localhost:{{PORTA_API}}/swagger-ui.html
- PostgreSQL: localhost:{{PORTA_POSTGRES}} | Redis: localhost:{{PORTA_REDIS}}
```

### {{NOME_DO_PROJETO}}-api/CLAUDE.md (Backend)

```markdown
# {{NOME_DO_PROJETO}} — Back-end

## Arquitetura Multi-Module Maven
| Módulo | Responsabilidade |
|--------|------------------|
| `{{projeto}}-core` | Entities, repository interfaces, services, exceções de domínio |
| `{{projeto}}-infrastructure` | Implementações JPA, Redis, multi-tenant |
| `{{projeto}}-security` | JWT (provider/filter), SecurityConfig, UserDetailsService, RBAC |
| `{{projeto}}-web` | REST controllers, DTOs, MapStruct mappers, Swagger, @ControllerAdvice |
| `{{projeto}}-app` | Main class, application.yml, Flyway migrations, bootstrap configs |

## Regras de Dependência
- `core` é independente — NUNCA importa outros módulos
- `infrastructure` e `security` dependem apenas de `core`
- `web` depende de `core` e `security`
- `app` agrega todos (único módulo bootável com `spring-boot-maven-plugin`)
- NUNCA crie dependências circulares entre módulos

## Comandos
```bash
./mvnw clean install                                                        # Build completo
./mvnw clean install -pl {{projeto}}-core                                   # Build módulo específico
./mvnw spring-boot:run -pl {{projeto}}-app -Dspring-boot.run.profiles=dev   # Rodar aplicação
./mvnw test                                                                 # Testes de todos os módulos
```

## Convenções
- **Java 21** — use Records para DTOs, pattern matching, text blocks
- **Tabelas**: prefixo `t_`, snake_case (ex: `t_merchant`, `t_user`)
- **Flyway**: migrations em `{{projeto}}-app/src/main/resources/db/migration/`
- **Packages**: `com.{{empresa}}.{{projeto}}.{core|infrastructure|security|web}`
- **Mapeamento**: MapStruct para Entity ↔ DTO (nunca manual)
- **Validação**: `@Valid` + Bean Validation nos DTOs de request
<!-- Se MULTI_TENANT ou HYBRID -->
- **Multi-tenant**: SEMPRE filtrar por `merchant_id` em queries customizadas
- Tenant é extraído do JWT claim e injetado via `TenantContext`

## Context7 — Bibliotecas para consultar
Spring Boot, Spring Security, Spring Data JPA, Flyway, MapStruct, jjwt, SpringDoc OpenAPI
```

### {{NOME_DO_PROJETO}}-web/CLAUDE.md (Frontend)

```markdown
# {{NOME_DO_PROJETO}} — Front-end

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
npm run dev          # Dev server (porta {{PORTA_FRONT}})
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
- **Responsividade**: OBRIGATÓRIO Mobile-First; testar em 375px, 768px, 1024px, 1280px
- **Touch**: Elementos clicáveis min 44x44px; inputs min-height 44px
- **Layout**: Sidebar drawer (mobile), colapsada (tablet), fixa (desktop)

## API
- Base URL: `http://localhost:{{PORTA_API}}/api/v1`
- Auth: Bearer token no header `Authorization`
- Refresh automático via interceptor Axios quando token expira

## Context7 — Bibliotecas para consultar
React, Vite, TanStack Query, React Router, shadcn/ui, Zod, Tailwind CSS, React Hook Form, Axios
```

### {{NOME_DO_PROJETO}}.code-workspace (Se o usuário aceitou criar)

> **IMPORTANTE**: Este arquivo só deve ser criado se o usuário respondeu "sim" na pergunta 10.

```json
{
  "folders": [
    {
      "name": "📁 Root",
      "path": "."
    },
    {
      "name": "🌐 Web (Front-end)",
      "path": "{{NOME_DO_PROJETO}}-web"
    },
    {
      "name": "⚙️ API (Back-end)",
      "path": "{{NOME_DO_PROJETO}}-api"
    }
  ],
  "settings": {
    "files.exclude": {
      "**/node_modules": true,
      "**/target": true,
      "**/.git": true,
      "**/dist": true,
      "**/out": true,
      "{{NOME_DO_PROJETO}}-web": true,
      "{{NOME_DO_PROJETO}}-api": true
    },
    "search.exclude": {
      "**/node_modules": true,
      "**/target": true,
      "**/dist": true,
      "**/out": true
    },
    "typescript.preferences.importModuleSpecifier": "relative",
    "editor.formatOnSave": true,
    "editor.defaultFormatter": "esbenp.prettier-vscode",
    "editor.codeActionsOnSave": {
      "source.fixAll.eslint": "explicit",
      "source.organizeImports": "explicit"
    },
    "[java]": {
      "editor.defaultFormatter": "redhat.java"
    }
  },
  "extensions": {
    "recommendations": [
      "esbenp.prettier-vscode",
      "dbaeumer.vscode-eslint",
      "bradlc.vscode-tailwindcss",
      "redhat.java",
      "vscjava.vscode-java-pack",
      "vmware.vscode-spring-boot",
      "vscjava.vscode-spring-boot-dashboard",
      "redhat.vscode-yaml"
    ]
  }
}
```

**Por que essa estrutura?**
- **`files.exclude`**: Oculta as pastas dos sub-projetos na visão do Root, evitando duplicação na barra lateral (cada módulo já aparece como sua própria pasta)
- **`search.exclude`**: Evita que buscas globais vasculhem `node_modules`, `target`, `dist`, etc.
- **`[java]` formatter**: Usa o formatter do Red Hat para arquivos Java, enquanto o Prettier cuida do restante
- **Ordem das pastas**: Root primeiro para que arquivos de configuração raiz (docker-compose, CLAUDE.md, README) fiquem visíveis no topo

---

## ✅ CHECKLIST DE IMPLEMENTAÇÃO

### ⚠️ Lembrete: Use o Context7 MCP antes de cada fase para consultar documentação atualizada!

### Fase 1: Setup Inicial
- [ ] Criar estrutura de pastas
- [ ] Inicializar projeto React + Vite + TypeScript
- [ ] Inicializar projeto Spring Boot multi-module Maven (parent + 5 módulos)
- [ ] Configurar Parent POM com `dependencyManagement` e módulos
- [ ] Configurar `pom.xml` de cada módulo com dependências corretas
- [ ] Configurar Docker Compose
- [ ] Configurar ESLint, Prettier, Tailwind CSS
- [ ] Criar arquivo `.code-workspace` (se o usuário aceitou)
- [ ] Criar `CLAUDE.md` na raiz do projeto (visão geral, credenciais, URLs)
- [ ] Criar `{{NOME_DO_PROJETO}}-api/CLAUDE.md` (convenções backend, módulos, regras de dependência)
- [ ] Criar `{{NOME_DO_PROJETO}}-web/CLAUDE.md` (convenções frontend, stack, estrutura de pastas)

### Fase 2: Configuração de Tenancy (CONFORME ARQUITETURA ESCOLHIDA)

#### Se Multi-Tenant (Opção A):
- [ ] Criar tabela `t_merchant` com campos de configuração
- [ ] Implementar `TenantContext` (ThreadLocal)
- [ ] Implementar `TenantFilter` para extrair tenant do JWT/header
- [ ] Configurar Hibernate Filters nas entidades
- [ ] Implementar AOP para ativar filtros automaticamente
- [ ] Adicionar `merchant_id` em todas as tabelas de negócio
- [ ] Criar índices compostos com `merchant_id`
- [ ] Incluir `merchant_id` como claim no JWT

#### Se Single-Tenant / On-Premise (Opção B):
- [ ] Configurar variáveis de ambiente para configurações do cliente
- [ ] Criar perfil de aplicação por cliente (`application-{cliente}.yml`)
- [ ] Documentar processo de deploy para novos clientes
- [ ] Configurar CI/CD para múltiplos deploys (se aplicável)

#### Se Híbrido (Opção C):
- [ ] Criar tabela `t_merchant` com campos `tenancy_type` e `dedicated_db_url`
- [ ] Implementar `TenantRoutingDataSource`
- [ ] Configurar múltiplos DataSources
- [ ] Implementar lógica de roteamento baseada no merchant
- [ ] Criar processo de provisioning para novos clientes enterprise

### Fase 3: Back-end Core (por módulo)
- [ ] **`{{projeto}}-core`**: Implementar entities, repository interfaces, service classes, exceções
- [ ] **`{{projeto}}-infrastructure`**: Criar migrations Flyway, configurar Redis, implementar tenant filter (se multi-tenant)
- [ ] **`{{projeto}}-security`**: Configurar Spring Security + JWT (provider, filter, SecurityConfig)
- [ ] **`{{projeto}}-web`**: Implementar controllers, DTOs, mappers MapStruct, Swagger, @ControllerAdvice
- [ ] **`{{projeto}}-app`**: Configurar application.yml, CorsConfig, main class
- [ ] Implementar casos de uso de autenticação (login, register, refresh, logout)
- [ ] Implementar CRUD de usuários
- [ ] Validar build completo: `./mvnw clean install`

### Fase 4: Front-end Core
- [ ] Instalar e configurar shadcn/ui
- [ ] Configurar TanStack Query
- [ ] Configurar Axios com interceptors
- [ ] Implementar AuthContext
- [ ] Implementar ThemeContext (dark/light)
- [ ] Criar layout base (Sidebar + Header)

### Fase 5: Páginas
- [ ] Página de Login (responsiva)
- [ ] Página de Signup (responsiva)
- [ ] Página de Perfil (responsiva)
- [ ] Home/Dashboard (responsiva)

### Fase 6: Responsividade e Layout Adaptativo
- [ ] Implementar Sidebar responsiva (drawer mobile, colapsável tablet, fixa desktop)
- [ ] Implementar Header responsivo com menu hamburger
- [ ] Criar hook `useBreakpoint` para lógica condicional de viewport
- [ ] Configurar grid system responsivo (1/2/3/4 colunas conforme breakpoint)
- [ ] Adaptar formulários para mobile (campos empilhados, botões full-width)
- [ ] Implementar tabelas responsivas (scroll horizontal ou cards em mobile)
- [ ] Garantir áreas de toque mínimas de 44x44px em elementos interativos
- [ ] Testar em viewports: 375px, 768px, 1024px, 1280px, 1536px
- [ ] Validar com Chrome DevTools Device Mode
- [ ] Verificar que zoom não está desabilitado (acessibilidade)

### Fase 7: Integração e Testes
- [ ] Testar fluxo completo de autenticação
- [ ] Testar refresh token
- [ ] Testar rotas protegidas
- [ ] **[Multi-Tenant]** Testar isolamento entre tenants
- [ ] **[Híbrido]** Testar routing entre bancos SHARED e DEDICATED

---

## 🎯 NOTAS IMPORTANTES

### Gerais
1. **Context7 MCP**: SEMPRE consulte a documentação atualizada antes de implementar. Bibliotecas mudam frequentemente!
2. **Segurança**: Nunca exponha secrets em código. Use variáveis de ambiente.
3. **Validação**: Valide dados tanto no front quanto no back-end.
4. **Tratamento de Erros**: Implemente error handling consistente em toda a aplicação.
5. **Logs**: Configure logging adequado (SLF4J no back, console estruturado no front).
6. **Testes**: Escreva pelo menos testes unitários para os casos de uso críticos.
7. **Responsividade**: TODO layout DEVE ser responsivo e testado em múltiplos dispositivos.

### 📱 Responsividade e Mobile-First - Atenção Obrigatória:
- ⚠️ **MOBILE-FIRST**: SEMPRE comece estilizando para mobile e adicione breakpoints para telas maiores
- ⚠️ **BREAKPOINTS**: Use os breakpoints padrão do Tailwind (sm:640px, md:768px, lg:1024px, xl:1280px, 2xl:1536px)
- ⚠️ **TOUCH TARGETS**: Elementos clicáveis DEVEM ter no mínimo 44x44px para touch
- ⚠️ **SIDEBAR**: Mobile usa drawer overlay, tablet usa sidebar colapsada, desktop usa sidebar fixa
- ⚠️ **TABELAS**: Em mobile, use scroll horizontal ou converta para cards empilhados
- ⚠️ **FORMULÁRIOS**: Inputs devem ter altura mínima de 44px; botões full-width em mobile
- ⚠️ **IMAGENS**: Use lazy loading e aspect-ratio para evitar layout shift
- ⚠️ **TESTES**: Teste em pelo menos 5 viewports (375px, 768px, 1024px, 1280px, 1536px)
- ⚠️ **ACESSIBILIDADE**: NUNCA desabilite zoom do usuário (user-scalable=no é proibido)
- ⚠️ **PERFORMANCE**: Evite JavaScript para layouts que CSS pode resolver; use CSS Grid e Flexbox

### Específicas por Arquitetura de Tenancy

#### Multi-Tenant (Opção A) - Atenção Redobrada:
- ⚠️ **CRÍTICO**: NUNCA esqueça de filtrar por `merchant_id` em queries customizadas (SQL nativo, Criteria, etc.)
- ⚠️ **SEGURANÇA**: Valide que o `merchant_id` do JWT corresponde ao recurso acessado
- ⚠️ **PERFORMANCE**: Crie índices compostos iniciando por `merchant_id`
- ⚠️ **AUDITORIA**: Logue sempre o `merchant_id` em operações sensíveis
- ⚠️ **TESTES**: Crie testes específicos para validar isolamento entre tenants

#### Single-Tenant (Opção B) - Considerações:
- ⚠️ **DEPLOY**: Documente claramente o processo de provisionamento de novos clientes
- ⚠️ **VERSIONING**: Mantenha controle de versão por cliente (alguns podem estar em versões diferentes)
- ⚠️ **BACKUP**: Estratégia de backup individual por cliente
- ⚠️ **CUSTOMIZAÇÃO**: Defina limites claros do que pode ser customizado via configuração

#### Híbrido (Opção C) - Complexidade Adicional:
- ⚠️ **ROUTING**: Teste exaustivamente o roteamento de DataSource
- ⚠️ **MIGRATIONS**: Migrations devem funcionar em ambos os cenários
- ⚠️ **CACHE**: Cuidado com cache compartilhado entre tenants SHARED
- ⚠️ **MONITORAMENTO**: Implemente métricas separadas por tipo de tenant

```

---

## 📌 COMO USAR ESTE PROMPT

1. Configure como **Custom Slash Command**: copie para `~/.claude/commands/create-saas-project.md`
2. Execute com `/user:create-saas-project` em qualquer projeto
3. Responda as **10 perguntas** (seção "Perguntas ao Usuário" acima) — as portas têm valores padrão
4. Confirme o resumo e aguarde a criação automática

### Quando Escolher Cada Arquitetura

| Cenário | Recomendação |
|---------|--------------|
| SaaS público com muitos clientes pequenos | **Multi-Tenant (A)** |
| Poucos clientes enterprise com dados sensíveis | **Single-Tenant (B)** |
| Mix de clientes pequenos + alguns enterprise | **Híbrido (C)** |
| Startup validando produto (MVP) | **Multi-Tenant (A)** |
| Produto para setor financeiro/saúde | **Single-Tenant (B)** ou **Híbrido (C)** |
| Produto white-label para revenda | **Multi-Tenant (A)** |

---

## 🔗 REFERÊNCIAS IMPORTANTES

- [React com Vite](https://react.dev/learn/build-a-react-app-from-scratch#vite)
- [shadcn/ui Docs](https://ui.shadcn.com/docs)
- [shadcn/ui MCP](https://ui.shadcn.com/docs/mcp)
- [TanStack Query](https://tanstack.com/query/latest)
- [Context7 MCP](https://context7.com/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
