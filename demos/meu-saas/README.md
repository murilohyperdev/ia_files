# MeuSaaS

Sistema de gestão empresarial - Projeto SaaS Full Stack

## Stack Tecnológica

### Front-end
- React 18 + TypeScript
- Vite
- Tailwind CSS + shadcn/ui
- TanStack Query (React Query)
- React Router DOM v6
- React Hook Form + Zod
- Zustand

### Back-end
- Java 21
- Spring Boot 3.2
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL 15
- Redis 7
- Flyway
- MapStruct

## Arquitetura

**Multi-Tenant** com isolamento por `merchant_id` em todas as tabelas de negócio.

## Estrutura do Projeto

```
meu-saas/
├── meu-saas-web/           # Front-end React
├── meu-saas-api/           # Back-end Spring Boot (multi-module Maven)
│   ├── meusaas-core/       # Domain (entities, repositories, services)
│   ├── meusaas-infrastructure/  # JPA, Redis, multi-tenant
│   ├── meusaas-security/   # JWT, Spring Security
│   ├── meusaas-web/        # REST controllers, DTOs, mappers
│   └── meusaas-app/        # Main class, configs, Flyway (bootável)
├── docker-compose.yml
└── meu-saas.code-workspace
```

## Quick Start

### 1. Subir os serviços (PostgreSQL + Redis)

```bash
docker-compose up -d
```

### 2. Rodar o Back-end

```bash
cd meu-saas-api
./mvnw clean install
./mvnw spring-boot:run -pl meusaas-app -Dspring-boot.run.profiles=dev
```

### 3. Rodar o Front-end

```bash
cd meu-saas-web
npm install
npm run dev
```

## URLs

| Serviço | URL |
|---------|-----|
| Front-end | http://localhost:3000 |
| API | http://localhost:8444 |
| Swagger | http://localhost:8444/swagger-ui.html |
| PostgreSQL | localhost:5433 |
| Redis | localhost:6380 |

## Credenciais de Desenvolvimento

| Usuário | Senha |
|---------|-------|
| admin@minhaempresa.com | Admin@123 |

## Comandos Úteis

```bash
# Build completo do back-end
cd meu-saas-api && ./mvnw clean install

# Build de produção do front-end
cd meu-saas-web && npm run build

# Parar containers
docker-compose down

# Ver logs dos containers
docker-compose logs -f
```

## Endpoints Principais

### Autenticação
- `POST /api/v1/auth/login` - Login
- `POST /api/v1/auth/register` - Cadastro
- `POST /api/v1/auth/refresh` - Renovar token
- `POST /api/v1/auth/logout` - Logout

### Perfil
- `GET /api/v1/me` - Dados do usuário logado
- `PUT /api/v1/me` - Atualizar perfil
- `PUT /api/v1/me/password` - Alterar senha

## Licença

Projeto privado.
