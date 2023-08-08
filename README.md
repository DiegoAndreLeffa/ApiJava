# Documentação da API em Java

Esta documentação descreve uma API Java para gerenciamento de usuários e transações.

## Tabelas do Banco de Dados

### Tabela User

A tabela "User" armazena informações sobre os usuários.

| Campo     | Tipo       | Descrição                                  |
|-----------|------------|--------------------------------------------|
| id        | INT        | Identificador único do usuário.            |
| name      | VARCHAR    | Nome do usuário.                           |
| cpf       | VARCHAR    | CPF do usuário.                            |
| email     | VARCHAR    | Endereço de e-mail do usuário.             |
| password  | VARCHAR    | Senha do usuário (criptografada).          |
| balance   | FLOAT      | Saldo do usuário.                          |
| type      | VARCHAR    | Tipo do usuário ("commun" ou "seller").    |

### Tabela Transaction

A tabela "Transaction" armazena informações sobre transações.

| Campo     | Tipo       | Descrição                                        |
|-----------|------------|--------------------------------------------------|
| id        | INT        | Identificador único da transação.                |
| payer     | INT        | ID do usuário que realiza o pagamento.           |
| payee     | INT        | ID do usuário que recebe o pagamento.            |
| value     | DECIMAL    | Valor da transação.                              |
| date      | DATE       | Data da transação.                               |

## Funcionalidades

### Funcionalidades de User

#### 1. Criar Usuário

Endpoint: `POST /api/user`

Cria um novo usuário.

Parâmetros de entrada:
- `name`: Nome do usuário.
- `cpf`: CPF do usuário.
- `email`: Endereço de e-mail do usuário.
- `password`: Senha do usuário.
- `balance`: Saldo inicial do usuário (opcional, padrão é 0).
- `type`: Tipo do usuário ("commun" ou "seller").

#### 2. Ler Usuário

Endpoint: `GET /api/user/{id}`

Recupera informações de um usuário específico.

Parâmetros de entrada:
- `id`: ID do usuário a ser recuperado.

#### 3. Atualizar Usuário

Endpoint: `PUT /api/user/{id}`

Atualiza as informações de um usuário.

Parâmetros de entrada:
- `id`: ID do usuário a ser atualizado.
- Campos a serem atualizados:
  - `name`: Novo nome do usuário.
  - `cpf`: Novo CPF do usuário.
  - `email`: Novo endereço de e-mail do usuário.
  - `password`: Nova senha do usuário.
  - `balance`: Novo saldo do usuário.
  - `type`: Novo tipo do usuário ("commun" ou "seller").

#### 4. Excluir Usuário

Endpoint: `DELETE /api/user/{id}`

Exclui um usuário.

Parâmetros de entrada:
- `id`: ID do usuário a ser excluído.

#### 5. Criar Depósito

Endpoint: `POST /api/user/{id}/deposit`

Realiza um depósito na conta de um usuário.

Parâmetros de entrada:
- `id`: ID do usuário.
- `value`: Valor a ser depositado.

### Funcionalidades de Transaction

#### 1. Criar Transação

Endpoint: `POST /api/transaction`

Cria uma nova transação.

Parâmetros de entrada:
- `payer`: ID do usuário que realiza o pagamento.
- `payee`: ID do usuário que recebe o pagamento.
- `value`: Valor da transação.
- `date`: Data da transação.

#### 2. Recuperar Transação

Endpoint: `GET /api/transaction/{id}`

Recupera informações de uma transação específica.
