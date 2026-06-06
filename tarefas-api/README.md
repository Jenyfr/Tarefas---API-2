# API de Gerenciamento de Tarefas Pessoais

Projeto autoral desenvolvido em Spring Boot para praticar a criacao de uma API RESTful com os metodos HTTP `GET`, `POST`, `PUT` e `DELETE`, aplicando boas praticas de arquitetura em camadas, validacao de dados e tratamento padronizado de respostas.

## Tecnologias usadas

- Java 17
- Spring Boot 3
- Spring Web
- Bean Validation
- Maven
- JUnit e MockMvc para testes

## Modelo arquitetural aplicado

O projeto foi organizado em camadas para separar responsabilidades:

- `controller`: recebe as requisicoes HTTP e define os endpoints REST.
- `service`: concentra as regras de negocio da aplicacao.
- `repository`: simula a camada de persistencia usando armazenamento em memoria.
- `model`: representa as entidades centrais do sistema.
- `dto`: separa os dados de entrada e saida da API.
- `exception`: centraliza o tratamento de erros e padroniza as respostas.

Essa organizacao facilita manutencao, testes e evolucao futura do projeto, por exemplo, trocando o repositorio em memoria por banco de dados.

## Como executar

No terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

A API ficara disponivel em:

```text
http://localhost:8080
```

## Como testar

Para executar os testes automatizados:

```bash
mvn test
```

Tambem existe o arquivo `requests.http` com requisicoes prontas para testar no VS Code, IntelliJ, Postman ou Insomnia.

## Endpoints da API

### Listar todas as tarefas

```http
GET /api/tarefas
```

Resposta de sucesso:

```http
200 OK
```

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Praticar metodos HTTP",
    "concluida": false,
    "prioridade": "ALTA",
    "criadaEm": "2026-06-05T20:30:00",
    "atualizadaEm": "2026-06-05T20:30:00"
  }
]
```

### Buscar uma tarefa por ID

```http
GET /api/tarefas/{id}
```

Respostas possiveis:

- `200 OK`: tarefa encontrada.
- `400 Bad Request`: ID invalido, por exemplo `0` ou valor nao numerico.
- `404 Not Found`: tarefa nao encontrada.

### Criar uma nova tarefa

```http
POST /api/tarefas
Content-Type: application/json
```

Corpo da requisicao:

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Praticar metodos HTTP",
  "concluida": false,
  "prioridade": "ALTA"
}
```

Respostas possiveis:

- `201 Created`: tarefa criada com sucesso.
- `400 Bad Request`: dados invalidos ou corpo JSON mal formatado.

### Atualizar uma tarefa existente

```http
PUT /api/tarefas/{id}
Content-Type: application/json
```

Corpo da requisicao:

```json
{
  "titulo": "Estudar API REST",
  "descricao": "Testar PUT no Postman ou Insomnia",
  "concluida": true,
  "prioridade": "MEDIA"
}
```

Respostas possiveis:

- `200 OK`: tarefa atualizada com sucesso.
- `400 Bad Request`: ID ou dados invalidos.
- `404 Not Found`: tarefa nao encontrada.

### Excluir uma tarefa

```http
DELETE /api/tarefas/{id}
```

Respostas possiveis:

- `204 No Content`: tarefa excluida com sucesso.
- `400 Bad Request`: ID invalido.
- `404 Not Found`: tarefa nao encontrada.

## Campos aceitos

| Campo | Tipo | Obrigatorio | Regra |
| --- | --- | --- | --- |
| `titulo` | texto | sim | Entre 3 e 100 caracteres; nao aceita `<` ou `>` |
| `descricao` | texto | nao | Ate 255 caracteres; nao aceita `<` ou `>` |
| `concluida` | booleano | nao | Quando ausente, assume `false` |
| `prioridade` | texto | nao | Valores aceitos: `BAIXA`, `MEDIA`, `ALTA`; quando ausente, assume `MEDIA` |

Campos desconhecidos no JSON nao sao aceitos, reforcando a integridade da API.

## Formato padrao de erro

Exemplo de erro de validacao:

```json
{
  "timestamp": "2026-06-05T20:30:00",
  "status": 400,
  "erro": "Bad Request",
  "mensagem": "Existem campos invalidos na requisicao.",
  "path": "/api/tarefas",
  "campos": {
    "titulo": "O titulo e obrigatorio."
  }
}
```

Exemplo de tarefa nao encontrada:

```json
{
  "timestamp": "2026-06-05T20:30:00",
  "status": 404,
  "erro": "Not Found",
  "mensagem": "Tarefa com id 99 nao encontrada.",
  "path": "/api/tarefas/99",
  "campos": null
}
```

## Evidencias de atendimento da unidade

- Revisitou os metodos HTTP e manteve respostas adequadas: `200`, `201`, `204`, `400`, `404` e `500`.
- Implementou controladores RESTful com mapeamento em `/api/tarefas`.
- Aplicou validacoes adicionais em campos, ID positivo, prioridade e JSON desconhecido.
- Personalizou respostas de erro com status, mensagem, caminho da requisicao e campos invalidos.
- Separou entrada e saida com DTOs.
- Organizou o projeto em controller, service, repository, model, dto e exception.
- Incluiu testes automatizados cobrindo CRUD e cenarios de falha.
