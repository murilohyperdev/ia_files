# meu-saas

## Descrição

Sistema de gestão empresarial

## Arquitetura de Tenancy

**Tipo**: Multi-Tenant

> **Multi-Tenant**: Isolamento por `merchant_id`. Todas as tabelas de negócio possuem `merchant_id`. Filtro automático via Hibernate Filters. NUNCA esqueça de filtrar por `merchant_id` em queries customizadas!

## Estrutura do Projeto

- `meu-saas-web/`: Front-end React 18 + TypeScript + Vite
- `meu-saas-api/`: Back-end Java 21 + Spring Boot (multi-module Maven)
  - `meusaas-core/`: Domain (entities, repositories, services, exceptions)
  - `meusaas-infrastructure/`: JPA, Redis, multi-tenant
  - `meusaas-security/`: JWT, SecurityConfig, RBAC
  - `meusaas-web/`: REST controllers, DTOs, mappers, Swagger
  - `meusaas-app/`: Main class, configs, application.yml, Flyway (bootável)

## Comandos Rápidos

```bash
# Front-end
cd meu-saas-web && npm run dev

# Back-end (build completo)
cd meu-saas-api && ./mvnw clean install

# Back-end (rodar)
cd meu-saas-api && ./mvnw spring-boot:run -pl meusaas-app -Dspring-boot.run.profiles=dev

# Docker
docker-compose up -d
```

## Context7 MCP

**SEMPRE consulte o Context7 antes de implementar código!** Valide que a versão da documentação corresponde ao `package.json` / `pom.xml`.

## Credenciais de Desenvolvimento

- **Admin**: admin@minhaempresa.com / Admin@123
- **PostgreSQL**: meusaas_user / meusaas_pass (porta: 5433)
- **Redis**: localhost:6380

## URLs

- Front-end: http://localhost:3001
- API: http://localhost:8444
- Swagger: http://localhost:8444/swagger-ui.html
- PostgreSQL: localhost:5433 | Redis: localhost:6380
