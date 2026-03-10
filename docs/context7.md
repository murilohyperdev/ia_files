# Context7 MCP — Documentação Sempre Atualizada para LLMs

> Criado pela Upstash | Integração via Model Context Protocol (MCP)

---

## 1. O Problema que ele resolve

LLMs como Claude, GPT e Gemini são treinados em datasets estáticos com uma **data de corte**. Isso significa que ao pedir ajuda com bibliotecas que evoluíram desde o treinamento, você recebe:

- ❌ APIs que não existem mais
- ❌ Padrões e sintaxes deprecados
- ❌ Exemplos de versões antigas
- ❌ Imports incorretos
- ❌ Configurações que mudaram

### Exemplos reais do problema

| Você pergunta sobre | O modelo pode gerar |
|---|---|
| Next.js 15 App Router | código da Pages Router (v12) |
| React 19 `use()` hook | workarounds manuais com useEffect |
| Prisma v5 | sintaxe do Prisma v3 |
| Spring Boot 3.x | configurações do Spring Boot 2.x |
| React Native 0.74+ | APIs da Bridge antiga |

### Por que isso acontece?

```
Treinamento do modelo  →  Data de corte  →  Você usando hoje
       (passado)              (gap)           (presente)

Bibliotecas lançadas ou atualizadas nesse gap = alucinações
```

---

## 2. O que é o Context7?

**Context7** é um servidor MCP (Model Context Protocol) que age como uma ponte entre seu assistente de IA e a documentação oficial e atualizada de mais de **9.000 bibliotecas e frameworks**.

Em vez de o modelo "lembrar" de algo do seu treinamento, ele **busca a documentação real** no momento em que você faz a pergunta.

```
Sem Context7:
Você → LLM (memória estática, possivelmente desatualizada) → Resposta

Com Context7:
Você → LLM → Context7 → Documentação oficial atualizada → Resposta precisa
```

---

## 3. Como Funciona

O Context7 expõe duas ferramentas principais via MCP:

### `resolve-library-id`
Resolve o nome de uma biblioteca para um ID interno do Context7.

```
Input:  "next.js"
Output: "/vercel/next.js/v15.0.0"
```

### `get-library-docs` (ou `query-docs`)
Busca a documentação relevante para um ID específico, filtrando pelo tópico da sua pergunta.

```
Input:  ID "/vercel/next.js/v15.0.0" + query "middleware authentication"
Output: Trechos relevantes da documentação oficial do Next.js 15
```

### Fluxo completo

```
1. Você escreve: "Crie um middleware Next.js que valida JWT. use context7"
2. O LLM chama resolve-library-id("next.js")
3. Context7 retorna: "/vercel/next.js/v15.0.0"
4. O LLM chama get-library-docs com o ID + sua query
5. Context7 injeta a documentação real no contexto
6. O LLM responde com código correto e atualizado ✅
```

---

## 4. Compatibilidade

### Editores e clientes suportados

| Editor / Cliente | Suporte |
|---|---|
| **Claude Code** | ✅ |
| **Cursor** | ✅ |
| **VS Code** (Copilot/Cline/RooCode) | ✅ |
| **Windsurf** | ✅ |
| **Claude Desktop** | ✅ |
| **Opencode** | ✅ |
| BoltAI | ✅ |
| Qualquer cliente MCP | ✅ |

### Transportes disponíveis

| Tipo | Configuração |
|---|---|
| **HTTP remoto** | `https://mcp.context7.com/mcp` |
| **Local (stdio)** | `npx @upstash/context7-mcp` |

---

## 5. Como Instalar

### Claude Code (recomendado)

```bash
# Com API key (recomendado — mais rate limit)
claude mcp add --scope user context7 -- npx -y @upstash/context7-mcp --api-key YOUR_API_KEY

# Via HTTP remoto
claude mcp add --scope user --header "CONTEXT7_API_KEY: YOUR_API_KEY" \
  --transport http context7 https://mcp.context7.com/mcp
```

> 💡 Remova `--scope user` para instalar apenas no projeto atual.

### Cursor

```json
// .cursor/mcp.json ou Settings > MCP
{
  "mcpServers": {
    "context7": {
      "command": "npx",
      "args": ["-y", "@upstash/context7-mcp", "--api-key", "YOUR_API_KEY"]
    }
  }
}
```

### Via HTTP (qualquer cliente)

```json
{
  "mcpServers": {
    "context7": {
      "url": "https://mcp.context7.com/mcp",
      "headers": {
        "CONTEXT7_API_KEY": "YOUR_API_KEY"
      }
    }
  }
}
```

### Obter API Key gratuita

Acesse: **`context7.com/dashboard`**

A API key é gratuita e aumenta o rate limit. É possível usar sem ela para testes básicos.

---

## 6. Como Usar no Dia a Dia

### Invocação manual

Adicione `use context7` ao final do seu prompt:

```
"Configure autenticação JWT no Spring Boot 3.x. use context7"
"Como fazer upload de imagem no React Native 0.74? use context7"
"Configure um Worker em Go com goroutines e channels. use context7"
"Crie uma query com Prisma v5 usando transactions. use context7"
```

### Invocação automática (recomendado)

Configure uma regra no seu cliente para invocar automaticamente:

```
# .cursorrules ou regra no Claude Code
Always use Context7 MCP when I need library/API documentation,
code generation, setup or configuration steps without me
having to explicitly ask.
```

### Especificando a versão

```
"Como usar middleware no Next.js 14? use context7"
"Configure o Prisma com PostgreSQL v5.10. use context7"
```

### Usando o ID diretamente (mais preciso)

```
"Implemente autenticação com supabase. use library /supabase/supabase"
```

---

## 7. Context7 vs Sem Context7

### Exemplo prático: Next.js Middleware

**Sem Context7** (modelo usando memória do treinamento):
```typescript
// ❌ Código gerado pode ser da versão antiga
// Next.js 12 - Pages Router (deprecado para este caso)
import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

export function middleware(request: NextRequest) {
  const token = request.cookies.get('token') // API antiga
  if (!token) {
    return NextResponse.redirect('/login') // path sem URL base
  }
}
```

**Com Context7** (documentação real do Next.js 15 injetada):
```typescript
// ✅ Código correto para Next.js 15
import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'

export function middleware(request: NextRequest) {
  const token = request.cookies.get('session_token')?.value

  if (!token) {
    return NextResponse.redirect(new URL('/login', request.url))
  }
  return NextResponse.next()
}

export const config = {
  matcher: ['/((?!api|_next/static|_next/image|favicon.ico).*)'],
}
```

### Comparativo geral

| Aspecto | Sem Context7 | Com Context7 |
|---|---|---|
| Fonte da informação | Memória do treinamento | Documentação oficial atual |
| APIs utilizadas | Podem estar deprecadas | Versão correta e atual |
| Exemplos de código | Podem não compilar | Funcionais e testados |
| Versões específicas | Genérico | Preciso por versão |
| Tempo debugando código gerado | Alto | Baixo |
| Confiança no output | Média | Alta |
| Bibliotecas pós-cutoff | ❌ alucinações | ✅ documentação real |

---

## 8. Bibliotecas Indexadas (exemplos)

O Context7 indexa mais de **9.000 bibliotecas**. Alguns exemplos relevantes:

| Categoria | Bibliotecas |
|---|---|
| Frontend | React, Next.js, Vue, Nuxt, Svelte, Angular, Tailwind |
| Mobile | React Native, Expo |
| Backend JS/TS | Fastify, Express, Hono, Elysia, NestJS, Bun |
| Backend Java | Spring Boot, Quarkus, Micronaut |
| Backend Go | Gin, Fiber, Echo, chi |
| ORM / DB | Prisma, Drizzle, TypeORM, Sequelize |
| Auth | NextAuth, Supabase, Clerk, Auth.js |
| Cloud | AWS SDK, Vercel SDK, Cloudflare Workers |
| Testes | Jest, Vitest, Playwright, Cypress |

---

## 9. Planos e Limites

| Plano | Rate Limit | Preço |
|---|---|---|
| Sem API key | Baixo (compartilhado) | Gratuito |
| Free (com API key) | Maior | Gratuito |
| Pro | Alto | Pago |
| Enterprise | Ilimitado + SLA | Contato |

> A API key gratuita já é suficiente para uso individual em desenvolvimento.

---

## 10. Pontos de Atenção

- **Documentação community-driven:** parte das libs é mantida pela comunidade, não pelo time oficial. Qualidade pode variar em libs menos populares.
- **Não substitui testes:** mesmo com docs corretas, sempre teste o código gerado.
- **Latência adicional:** há um round-trip extra para buscar a documentação antes de responder.
- **Privacidade:** queries enviadas ao Context7 incluem o nome da lib e o tópico pesquisado.

---

## 11. Resumo

```
Context7 = Documentação oficial atualizada injetada no contexto do LLM

Problema resolvido:  Alucinações por documentação desatualizada
Como usar:          Adicione "use context7" ao prompt
Integração:         MCP — funciona com Claude Code, Cursor, VS Code, Windsurf
Custo:              Gratuito com API key em context7.com/dashboard
Cobertura:          9.000+ bibliotecas e frameworks
```

---

*Referências: context7.com · github.com/upstash/context7 · upstash.com/blog/context7-mcp*