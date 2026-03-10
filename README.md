# IA Files - Kit de Desenvolvimento com Claude AI

Repositório de configurações, agentes, comandos e recursos para auxiliar desenvolvedores a utilizar o Claude AI no desenvolvimento de aplicações.

## Estrutura do Repositório

```
ia_files/
├── .claude/          # Configurações do Claude Code
│   ├── agents/       # Agentes especializados
│   └── commands/     # Comandos de workflow (slash commands)
├── docs/             # Base de conhecimento e guias
├── prompts/          # Templates de prompts reutilizáveis
└── demos/            # Projetos de exemplo
```

## `.claude/agents/` — Agentes Especializados

Agentes com papéis definidos que o Claude assume conforme o contexto:

| Agente | Descrição |
|--------|-----------|
| `code-reviewer.md` | Revisão de código pré-PR: qualidade, bugs, segurança e boas práticas |
| `react-developer.md` | Desenvolvimento React com componentes shadcn/ui |
| `python-developer.md` | Desenvolvimento Python idiomático e performático |
| `test-engineer.md` | Escrita de testes unitários eficazes sem alterar implementação |
| `research-agent.md` | Pesquisa multi-fonte usando Web Search, Perplexity e Context7 |
| `metaspec-gate-keeper.md` | Guardião de consistência arquitetural e DNA do projeto |
| `frontend-design/SKILL.md` | Criação de interfaces frontend com alto padrão de design |

Também inclui variantes `branch-*` de alguns agentes para workflows focados em branches.

## `.claude/commands/` — Comandos de Workflow

Slash commands organizados por área que definem processos estruturados de desenvolvimento:

### `engineer/` — Engenharia

| Comando | Uso | Descrição |
|---------|-----|-----------|
| `start.md` | `/engineer:start` | Inicia desenvolvimento de feature com análise e setup |
| `work.md` | `/engineer:work` | Gerencia fases de trabalho em andamento |
| `plan.md` | `/engineer:plan` | Cria planos de implementação em fases |
| `warm-up.md` | `/engineer:warm-up` | Aquecimento — análise inicial do projeto |
| `pre-pr.md` | `/engineer:pre-pr` | Verificações pré-PR (testes, lint, revisão) |
| `pr.md` | `/engineer:pr` | Criação de Pull Request |
| `review-from.md` | `/engineer:review-from` | Code review a partir de um commit específico |
| `docs.md` | `/engineer:docs` | Geração de documentação do branch |
| `bump.md` | `/engineer:bump` | Bump de versão para release |

### `product/` — Produto

| Comando | Uso | Descrição |
|---------|-----|-----------|
| `spec.md` | `/product:spec` | Análise e refinamento de requisitos |
| `architecture.md` | `/product:architecture` | Início da engenharia e arquitetura |
| `collect.md` | `/product:collect` | Coleta de novas ideias e funcionalidades |
| `refine.md` | `/product:refine` | Refinamento de requisitos existentes |
| `warm-up.md` | `/product:warm-up` | Aquecimento do projeto |
| `check.md` | `/product:check` | Verificação de produto |

### `metaspecs/` — Documentação e Metadados

| Comando | Uso | Descrição |
|---------|-----|-----------|
| `build-business-docs.md` | `/metaspecs:build-business-docs` | Gera documentação de contexto empresarial |
| `build-tech-docs.md` | `/metaspecs:build-tech-docs` | Gera documentação técnica |
| `build-repo-summary.md` | `/metaspecs:build-repo-summary` | Sintetiza resumo do repositório |
| `build-index.md` | `/metaspecs:build-index` | Cria índice de documentação |
| `extract-adr-from-repo-docs.md` | `/metaspecs:extract-adr-from-repo-docs` | Extrai ADRs do repositório |
| `extract-ecosystem-architecture.md` | `/metaspecs:extract-ecosystem-architecture` | Extrai arquitetura multi-repo |

### `repodocs/` — Geração de CLAUDE.md

| Comando | Uso | Descrição |
|---------|-----|-----------|
| `generate-all-claude-mds.md` | `/repodocs:generate-all-claude-mds` | Gera CLAUDE.md para repositórios (PT-BR) |
| `generate-all-claude-mds-en.md` | `/repodocs:generate-all-claude-mds-en` | Gera CLAUDE.md para repositórios (EN) |
| `generate-docs.md` | `/repodocs:generate-docs` | Gera documentação geral do repositório |

## `docs/` — Base de Conhecimento

| Arquivo | Conteúdo |
|---------|----------|
| `MCPs.md` | Guia de referência para servidores MCP (Context7, Code Expert, GitHub, Jira, Confluence, etc.) |
| `context7.md` | Guia completo de uso do Context7 MCP para documentação atualizada de bibliotecas |
| `bun_x_node.md` | Comparativo detalhado entre Bun e Node.js com frameworks (Elysia, Hono, NestJS) |

## `prompts/` — Templates de Prompts

| Arquivo | Conteúdo |
|---------|----------|
| `create-saas-project.md` | Template interativo para criação de projetos SaaS full-stack |

## `demos/` — Projetos de Exemplo

### `meu-saas/` — Exemplo de SaaS Full-Stack

Projeto completo demonstrando a aplicação prática dos recursos deste repositório:

- **Backend** (`meu-saas-api/`): Java 21 + Spring Boot 3.2, arquitetura multi-módulo Maven, multi-tenant com isolamento por `merchant_id`, PostgreSQL + Redis, autenticação JWT com RBAC
- **Frontend** (`meu-saas-web/`): React 18 + TypeScript + Vite, Tailwind CSS + shadcn/ui, TanStack Query, Zustand, React Hook Form + Zod
- Inclui `docker-compose.yml` para ambiente local e `CLAUDE.md` com guias de desenvolvimento

## Como Usar

1. **Copie a pasta `.claude/`** para o seu projeto — os agentes e comandos ficam disponíveis automaticamente no Claude Code
2. **Copie os arquivos de `docs/`** relevantes para a pasta `.claude/` do seu projeto como referência
3. **Use os slash commands** no Claude Code (ex: `/engineer:start`, `/product:spec`) para seguir workflows estruturados
4. **Consulte `demos/meu-saas/`** como referência de arquitetura e padrões

## Requisitos

- [Claude Code](https://docs.anthropic.com/en/docs/claude-code) instalado e configurado
- Para o projeto demo: Docker, Java 21+, Node.js 18+
