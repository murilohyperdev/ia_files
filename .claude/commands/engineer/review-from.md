# Code Review a partir de commit

Realize uma revisao de codigo focada nas mudancas feitas **a partir do commit `$ARGUMENTS`** (inclusive) ate HEAD.

## Processo

### 1. Coletar Mudancas
- Execute `git log $ARGUMENTS~1..HEAD --oneline` para listar os commits no escopo (incluindo o proprio commit informado)
- Execute `git diff $ARGUMENTS~1..HEAD --stat` para ver o resumo dos arquivos alterados
- Execute `git diff $ARGUMENTS~1..HEAD` para ver o diff completo

Nota: Usamos `$ARGUMENTS~1` (pai do commit) para que o diff inclua as mudancas do proprio commit informado.

### 2. Revisao de Codigo
Invoque o agente `branch-code-reviewer` para revisar o codigo e garantir que esta bom para lancar. Passe o contexto de que o escopo da revisao e limitado ao diff `$ARGUMENTS~1..HEAD` (nao o branch inteiro).

### 3. Cobertura de Testes
Invoque o agente `branch-test-planner` para analisar cobertura de testes das mudancas no escopo `$ARGUMENTS~1..HEAD`.

### 4. Feedback e Correcoes
Voce tambem precisara lidar com todo o feedback que esses agentes fornecerem e fazer mudancas e correcoes conforme necessario.

Uma vez terminado, me apresente um resumo consolidado dos achados e pergunte se devo prosseguir com as correcoes.
