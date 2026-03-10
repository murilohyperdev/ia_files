# Gerar Documentacao CLAUDE.md

Gerar arquivos CLAUDE.md para fornecer contexto do codigo para assistentes de IA.

## Proposito

Arquivos CLAUDE.md existem exclusivamente para ajudar desenvolvedores IA a entender o codigo ao trabalhar em arquivos de cada diretorio. Eles NAO sao:
- Documentacao para humanos (README, docs/)
- Tutoriais ou guias
- Referencias de API
- Instrucoes de instalacao

Eles SAO:
- Mapas contextuais para IA navegando no codigo
- Guias de integracao mostrando como modulos se conectam
- Documentacao de padroes para geracao consistente de codigo
- Listas de armadilhas prevenindo erros comuns de IA

## O Que Incluir

### Conteudo Essencial
- **Proposito do modulo**: Uma frase explicando o que este codigo faz
- **Catalogo de arquivos**: O que cada arquivo contem e seu papel
- **Padroes principais**: Como as coisas sao feitas aqui (nomenclatura, estrutura, tratamento de erros)
- **Pontos de integracao**: Como este modulo se conecta a outros
- **Armadilhas**: Ciladas que fariam a IA gerar codigo incorreto
- **Anti-padroes**: O que NAO fazer neste codigo

### Excluir
- Numeros de versao, datas, "ultima atualizacao"
- Instrucoes de instalacao/configuracao
- Exemplos de uso CLI (exceto ao explicar implementacao interna)
- Tutoriais legiveis por humanos
- Badges, links para docs externos
- Qualquer coisa que humanos precisem mas contexto de codigo nao requer

## Template de Arquivo

```markdown
# [Nome do Modulo]

[Uma frase: o que este modulo faz]

## Arquivos

- **`arquivo.py`**: [O que contem, classes/funcoes principais]
- **`outro.py`**: [O que contem]

## Padroes

- **[Nome do padrao]**: [Como e implementado aqui, quando usar]

## Integracao

- Importa de: `modulo.x`, `modulo.y`
- Usado por: `outro_modulo.z`
- Fluxo de dados: [Breve descricao de como dados fluem]

## Armadilhas

- [Cilada especifica que causaria geracao de codigo incorreto]
- [Outra armadilha]

## Ao Adicionar Codigo

- [Restricao ou convencao a seguir]
- [Outra convencao]
```

## Regras de Hierarquia

### Ordem de Execucao: Folhas → Tronco

Construir dos diretorios mais profundos ate a raiz:

1. **Arquivos folha** (mais profundos): Documentar cada arquivo, todos os padroes, todas as armadilhas
2. **Diretorios pai**: Focar em como filhos integram, referenciar filhos para detalhes
3. **Raiz** (por ultimo): Apenas mapa de alto nivel, todo resto nos filhos

### Distribuicao de Conteudo

| Nivel | Contem | Evita |
|-------|--------|-------|
| Folha | Todos detalhes de arquivos, todos padroes, todas armadilhas | - |
| Meio | Integracao entre filhos, padroes transversais | Repetir conteudo de filhos |
| Raiz | Mapa de diretorios, apenas padroes do projeto inteiro | Detalhes de implementacao |

### Padrao de Referencia

Arquivos pai devem dizer:
```markdown
## Submodulos

- **[filho/](filho/CLAUDE.md)**: [O que ele trata]

Veja CLAUDE.md do filho para detalhes de implementacao.
```

NAO duplicar o conteudo do filho.

## Anti-Padroes

**NAO incluir:**
- `## Inicio Rapido` ou `## Comecando`
- `## Instalacao`
- `## Exemplos de Uso` (exceto ao mostrar padroes internos)
- `## Versao` ou numeros de versao
- `## Contribuindo` ou `## Licenca`
- `Atual: v1.2.0` ou qualquer string de versao
- `Ultima Atualizacao: Janeiro 2025`
- Links para documentacao externa
- Badges ou indicadores de status

**NAO duplicar:**
- Mesma descricao de arquivo no pai e filho
- Mesma armadilha em multiplos niveis
- Mesma explicacao de padrao repetida

**INCLUIR:**
- O que faria a IA escrever codigo incorreto
- Como modulos realmente se conectam (nao como usuarios os usam)
- Convencoes de nomenclatura que devem ser seguidas
- Casos de borda na implementacao

## Estrategia de Posicionamento

Criar CLAUDE.md em:
- Raiz do projeto (sempre)
- Diretorios com codigo de implementacao substancial (3+ arquivos nao-triviais)
- Diretorios com logica interna complexa que vale documentar

Pular CLAUDE.md para:
- **Diretorios de passagem**: Pastas que contem apenas `__init__.py` + subpastas (ex: `src/package/` quando todo codigo esta em `src/package/core/`, `src/package/utils/`)
- **Niveis de re-exportacao simples**: Se um diretorio so tem `__init__.py` que re-exporta dos filhos, pular - documentar na raiz
- Diretorios de arquivo unico
- Codigo gerado
- Diretorios de configuracao pura
- `node_modules`, `__pycache__`, `.git`, etc.

### Regra de Diretorio de Passagem

Se uma estrutura de diretorio parece com:
```
src/meupacote/
├── __init__.py            # Apenas re-exporta
├── um_arquivo_simples.py  # Pode ser mencionado na raiz
├── core/                  # Tem implementacao real
├── utils/                 # Tem implementacao real
└── api/                   # Tem implementacao real
```

Entao pular `src/meupacote/CLAUDE.md`. Em vez disso:
- CLAUDE.md raiz referencia `core/`, `utils/`, `api/` diretamente
- Mencionar `__init__.py` e `um_arquivo_simples.py` brevemente na raiz

Isso evita arquivos CLAUDE.md que sao apenas "veja filhos para detalhes".

## Execucao

1. Mapear estrutura de diretorios, identificar pontos de documentacao
2. Comecar com diretorios mais profundos (folhas)
3. Subir, referenciando filhos em vez de repetir
4. Finalizar com raiz como hub de navegacao
5. Revisar: remover qualquer conteudo orientado a humanos que entrou
