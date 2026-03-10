# Bun vs Node.js — Guia Completo de Comparação

> Atualizado em 2025 | Inclui comparação de frameworks e quando utilizá-los

---

## 1. O que são?

| | Node.js | Bun |
|---|---|---|
| Definição | Runtime JavaScript para servidor, criado em 2009 | Runtime JavaScript moderno, all-in-one, criado em 2022 |
| Motor JS | V8 (Google/Chrome) | JavaScriptCore (Apple/Safari) |
| Linguagem base | C++ | Zig |
| Lançamento estável | 2009 | Setembro/2023 (v1.0) |
| Mantido por | OpenJS Foundation | Anthropic (desde dez/2025) |
| Licença | MIT | MIT (open-source) |

---

## 2. Arquitetura & Fundação

| Categoria | Node.js | Bun |
|---|---|---|
| Motor JavaScript | V8 (Google/Chrome) | JavaScriptCore (Apple/Safari) |
| Modelo de I/O | Non-blocking, event-driven via libuv | Non-blocking, event-driven nativo |
| TypeScript nativo | ❌ precisa de `ts-node` ou `tsx` | ✅ roda `.ts` direto, zero config |
| JSX nativo | ❌ precisa de Babel/esbuild | ✅ nativo |
| CommonJS + ESM simultâneo | ✅ com quirks | ✅ sem conflitos |

---

## 3. Performance

| Métrica | Node.js | Bun | Diferença |
|---|---|---|---|
| Startup / cold start | ~100ms | ~25ms | **Bun ~4x mais rápido** |
| HTTP requests/s (nativo) | ~35k–40k req/s | ~100k req/s | **Bun ~2.5x mais rápido** |
| HTTP com Express | ~13k req/s | ~52k req/s | **Bun ~4x mais rápido** |
| File I/O | baseline | ~3x mais rápido | **Bun** |
| CPU-bound (sort 100k itens) | ~3.400ms | ~1.700ms | **Bun ~2x mais rápido** |
| Memória (servidor mínimo) | ~50–60 MB | ~25–30 MB | **Bun ~2x mais eficiente** |
| Apps com banco de dados pesado | ~22ms latência | ~23ms latência | **Empate** (gargalo é o DB) |

> ⚠️ **Importante:** Benchmarks variam conforme o workload. Em aplicações dominadas por queries de banco de dados, a diferença de runtime tende a desaparecer. Os ganhos do Bun são mais expressivos em cold starts, CLIs, serviços leves e instalação de dependências.

---

## 4. Ferramentas Incluídas

| Ferramenta | Node.js | Bun |
|---|---|---|
| Package manager | ❌ npm separado | ✅ `bun install` nativo |
| Velocidade de install | baseline | **10–30x mais rápido** |
| Bundler | ❌ webpack / vite / esbuild | ✅ `bun build` nativo |
| Test runner | ✅ `node:test` (nativo recente) | ✅ `bun test` (compatível com Jest) |
| Watch mode | `--watch` (v18+) | ✅ nativo e mais rápido |
| Executáveis standalone | ✅ experimental | ✅ `bun build --compile` |
| SQLite embutido | ❌ precisa de lib | ✅ nativo via `bun:sqlite` |
| Redis client nativo | ❌ | ✅ `bun:redis` (v1.2+) |
| WebSocket nativo | parcial | ✅ nativo |
| REPL interativo | ✅ | ❌ não possui |
| Linter / Formatter | ❌ ESLint/Prettier separados | ❌ ESLint/Prettier separados |

---

## 5. Ecossistema & Compatibilidade

| Categoria | Node.js | Bun |
|---|---|---|
| Pacotes npm disponíveis | ~2 milhões+ | ~2 milhões (via compatibilidade) |
| Compatibilidade com npm | nativo | ~99% (mira 100%) |
| Módulos nativos (`.node` bindings) | ✅ total | ⚠️ parcial — pode quebrar |
| Express | ✅ | ✅ |
| Fastify | ✅ | ✅ |
| NestJS | ✅ | ✅ (com adaptador) |
| Prisma | ✅ | ✅ |
| Next.js | ✅ | ✅ (suporte oficial) |
| Suporte Docker oficial | ✅ maduro | ✅ imagem `oven/bun` |
| AWS Lambda | ✅ nativo | ⚠️ precisa de layer customizado |
| Vercel / edge functions | ✅ | ✅ suporte oficial |

---

## 6. Segurança & Suporte Corporativo

| Categoria | Node.js | Bun |
|---|---|---|
| LTS (Long Term Support) | ✅ ciclos definidos e previsíveis | ❌ sem LTS formal ainda |
| Auditoria de segurança | ✅ anos de histórico | ⚠️ ainda madurando |
| Modelo de permissões/sandboxing | ✅ experimental (v20+) | ❌ não possui |
| CVEs documentados e corrigidos | ✅ histórico extenso | poucos (projeto novo) |
| Suporte corporativo | OpenJS Foundation | Anthropic |
| Adoção enterprise | ✅ amplamente adotado | ⚠️ crescendo, mas limitado |

---

## 7. Developer Experience (DX)

| Categoria | Node.js | Bun |
|---|---|---|
| Configuração inicial | ❌ múltiplas ferramentas necessárias | ✅ zero config, tudo incluso |
| TypeScript sem configuração | ❌ | ✅ |
| Debugging | ✅ maduro (Chrome DevTools) | ✅ suporte crescente |
| Documentação | ✅ extensa e madura | ✅ boa, mas menor volume |
| Comunidade | ✅ enorme e consolidada | crescendo rapidamente |
| CI/CD mais rápido | baseline | ✅ install muito mais rápido |
| Curva de aprendizado | moderada | baixa (API similar ao Node) |

---

## 8. Bun Puro vs Com Framework

### Quando o Bun puro resolve

Para projetos simples, o Bun já oferece um servidor HTTP embutido e performático:

```typescript
// server.ts — sem nenhuma dependência
Bun.serve({
  port: 3000,
  fetch(req) {
    const url = new URL(req.url);

    if (url.pathname === "/") {
      return new Response("Hello World");
    }

    if (url.pathname === "/health") {
      return Response.json({ status: "ok" });
    }

    return new Response("Not Found", { status: 404 });
  },
});
```

### O problema: crescimento sem framework

Conforme a API cresce, o código manual vira um problema:

```typescript
// Roteamento manual que vira espaguete rapidamente
fetch(req) {
  const url = new URL(req.url);
  const method = req.method;

  if (method === "GET" && url.pathname === "/users") { ... }
  if (method === "POST" && url.pathname === "/users") { ... }
  if (method === "GET" && url.pathname.startsWith("/users/")) {
    const id = url.pathname.split("/")[2]; // frágil
    ...
  }
  // Middlewares? Validação? Auth? Tudo manual...
}
```

### Comparação: Bun Puro vs Com Framework

| Necessidade | Bun Puro | Com Framework |
|---|---|---|
| Servidor HTTP | ✅ nativo | ✅ |
| Roteamento básico | ✅ manual | ✅ automático |
| Roteamento com parâmetros e grupos | ❌ trabalhoso e frágil | ✅ |
| Middlewares (auth, log, CORS) | ❌ manual | ✅ |
| Validação de dados (body, query) | ❌ manual | ✅ |
| Injeção de dependência | ❌ | ✅ (NestJS) |
| Type-safety ponta a ponta | ❌ | ✅ (Elysia) |
| Swagger / OpenAPI automático | ❌ | ✅ |
| Tratamento de erros centralizado | ❌ manual | ✅ |
| Escalabilidade da codebase | ❌ você define tudo | ✅ arquitetura guiada |
| WebSockets com rooms | ✅ nativo básico | ✅ mais ergonômico |
| Código inicial necessário | mínimo | um pouco mais |

---

## 9. Frameworks Recomendados para Bun

### 🥇 Elysia — Recomendado Principal

Framework nativo do Bun, construído especificamente para ele. TypeScript-first com inferência de tipos ponta a ponta.

**Por que usar:**
- Performance máxima (aproveita 100% do Bun)
- Type-safety automática: o tipo do `body` é inferido da validação
- Validação, Swagger, JWT, cookies — tudo como plugins oficiais
- Sintaxe limpa e ergonômica
- Equivalente ao Fastify no ecossistema Node

```typescript
import { Elysia, t } from "elysia";
import { swagger } from "@elysiajs/swagger";
import { jwt } from "@elysiajs/jwt";

const app = new Elysia()
  .use(swagger())
  .use(jwt({ secret: "secret" }))
  .get("/users", () => db.query("SELECT * FROM users"))
  .post("/users", ({ body }) => db.insert(body), {
    body: t.Object({
      name: t.String(),
      email: t.String({ format: "email" }),
    }),
  })
  .listen(3000);

console.log(`Rodando em http://localhost:${app.server?.port}`);
```

---

### 🥈 Hono — Leve e Multiplataforma

Micro-framework que roda em Bun, Node, Deno, Cloudflare Workers e edge runtimes.

**Por que usar:**
- Extremamente leve (sem opinião forte sobre arquitetura)
- Ideal para APIs simples e funções serverless/edge
- Fácil de migrar entre plataformas
- Boa alternativa quando portabilidade é prioridade

```typescript
import { Hono } from "hono";

const app = new Hono();

app.get("/users", (c) => c.json({ users: [] }));
app.post("/users", async (c) => {
  const body = await c.req.json();
  return c.json(body, 201);
});

export default app; // funciona em Bun, Deno, Cloudflare Workers
```

---

### 🥉 NestJS — Para Times que já conhecem

Framework robusto e opinionado, inspirado no Angular. Roda no Bun com adaptador.

**Por que usar (com Bun):**
- Time já familiarizado com a estrutura (similar ao Spring Boot)
- Injeção de dependência, módulos, decorators
- Ecossistema maduro e corporativo

**Cuidados:**
- Perde parte da performance do Bun (overhead do framework)
- Adiciona complexidade desnecessária para projetos pequenos

```typescript
// Estrutura similar ao Spring Boot
@Controller("users")
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Get()
  findAll() {
    return this.usersService.findAll();
  }

  @Post()
  create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }
}
```

---

### Comparação entre os Frameworks

| Critério | Elysia | Hono | NestJS |
|---|---|---|---|
| Performance | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ | ⭐⭐⭐ |
| Nativo para Bun | ✅ | ❌ (multiplataforma) | ❌ |
| Type-safety ponta a ponta | ✅ automático | parcial | manual |
| Validação de dados | ✅ nativo | ✅ via middleware | ✅ via class-validator |
| Swagger automático | ✅ plugin oficial | ✅ plugin | ✅ plugin |
| Injeção de dependência | ❌ | ❌ | ✅ |
| Curva de aprendizado | baixa | muito baixa | alta |
| Arquitetura opinionada | ❌ flexível | ❌ flexível | ✅ |
| Ideal para | APIs REST/novas | Serviços leves/edge | Times enterprise |

---

## 10. Quando Usar Cada Combinação

| Cenário | Recomendação |
|---|---|
| Script / CLI / automação | Bun puro |
| API pequena, equipe de 1–2 devs | Bun + Hono |
| API REST com crescimento previsto | **Bun + Elysia** ✅ |
| Microserviço leve ou serverless | Bun + Hono |
| Time que já usa NestJS | Bun + NestJS (ou Node + NestJS) |
| App legada em produção crítica | Node.js (não migre) |
| Projeto com libs nativas (`.node`) | Node.js |
| Requisito de LTS corporativo | Node.js |
| AWS Lambda sem config extra | Node.js |
| CI/CD com muitas dependências | Bun (install 10–30x mais rápido) |

---

## 11. Resumo Final

```
Bun = Node.js mais rápido + TypeScript nativo + ferramentas incluídas
Elysia = o "Spring Boot leve" do ecossistema Bun
```

**Use Bun + Elysia quando:** projeto novo, TypeScript, performance importa, quer zero configuração.

**Use Node.js + NestJS quando:** time consolidado, libs nativas, requisitos enterprise/LTS.

**Use Bun + Hono quando:** serviço leve, edge/serverless, ou precisa de portabilidade entre plataformas.

---

*Referências: bun.com/docs · elysiajs.com · hono.dev · nestjs.com*