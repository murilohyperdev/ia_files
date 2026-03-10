# meu-saas - Back-end

## Arquitetura Multi-Module Maven
| Módulo | Responsabilidade |
|--------|------------------|
| `meusaas-core` | Entities, repository interfaces, services, exceções de domínio |
| `meusaas-infrastructure` | Implementações JPA, Redis, multi-tenant |
| `meusaas-security` | JWT (provider/filter), SecurityConfig, UserDetailsService, RBAC |
| `meusaas-web` | REST controllers, DTOs, MapStruct mappers, Swagger, @ControllerAdvice |
| `meusaas-app` | Main class, application.yml, Flyway migrations, bootstrap configs |

## Regras de Dependência
- `core` é independente — NUNCA importa outros módulos
- `infrastructure` e `security` dependem apenas de `core`
- `web` depende de `core` e `security`
- `app` agrega todos (único módulo bootável com `spring-boot-maven-plugin`)
- NUNCA crie dependências circulares entre módulos

## Comandos
```bash
./mvnw clean install                                                        # Build completo
./mvnw clean install -pl meusaas-core                                       # Build módulo específico
./mvnw spring-boot:run -pl meusaas-app -Dspring-boot.run.profiles=dev       # Rodar aplicação
./mvnw test                                                                 # Testes de todos os módulos
```

## Convenções
- **Java 21** — use Records para DTOs, pattern matching, text blocks
- **Tabelas**: prefixo `t_`, snake_case (ex: `t_merchant`, `t_user`)
- **Flyway**: migrations em `meusaas-app/src/main/resources/db/migration/`
- **Packages**: `com.minhaempresa.meusaas.{core|infrastructure|security|web}`
- **Mapeamento**: MapStruct para Entity ↔ DTO (nunca manual)
- **Validação**: `@Valid` + Bean Validation nos DTOs de request
- **Multi-tenant**: SEMPRE filtrar por `merchant_id` em queries customizadas
- Tenant é extraído do JWT claim e injetado via `TenantContext`

## Context7 — Bibliotecas para consultar
Spring Boot, Spring Security, Spring Data JPA, Flyway, MapStruct, jjwt, SpringDoc OpenAPI
