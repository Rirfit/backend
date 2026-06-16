# Sistema de Condomínio - Backend

## Descrição do Projeto

Backend desenvolvido em Kotlin + Spring Boot para gerenciamento de condomínio residencial.

O sistema possui autenticação JWT, controle de usuários, apartamentos, reservas, visitantes, veículos, encomendas, avisos e documentos.

---

# Tecnologias Utilizadas

- Kotlin
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- MySQL
- Swagger/OpenAPI
- JUnit 5
- Mockito
- JaCoCo
- Gradle

---

# Funcionalidades

## Autenticação
- Login com JWT
- Senhas criptografadas com BCrypt
- Proteção de rotas via Spring Security
- Bearer Token Authentication

## Módulos do Sistema

### Usuários
- Cadastro de usuários
- Listagem de usuários
- Controle de permissões
- Associação com apartamentos

### Apartamentos
- Cadastro de apartamentos
- Controle de bloco e número
- Validação de duplicidade

### Reservas
- Criação de reservas
- Aprovação/Rejeição
- Controle de conflitos de horário

### Visitantes
- Cadastro de visitantes
- Controle de validade
- Bloqueio de visitantes

### Veículos
- Cadastro de veículos
- Controle de autorização
- Bloqueio de veículos

### Encomendas
- Registro de encomendas
- Controle de retirada

### Avisos
- Cadastro de avisos
- Exclusão de avisos

### Documentos
- Upload de documentos
- Controle de tipos permitidos

---

# Estrutura do Projeto

```text
src/main/kotlin/com/condominio/backend
│
├── apartment
├── auth
├── delivery
├── document
├── notice
├── reservation
├── security
├── user
├── vehicle
└── visitor
```

---

# Segurança

O sistema utiliza:

- Spring Security
- JWT Authentication
- BCrypt Password Encoder
- JwtFilter para validação automática do token

## Fluxo de autenticação

1. Usuário realiza login
2. Backend gera token JWT
3. Frontend armazena token
4. Token é enviado nas próximas requisições
5. JwtFilter valida o token automaticamente

---

# Endpoints Principais

## Login

### POST /auth/login

Request:

```json
{
  "email": "gabriel@email.com",
  "password": "123456"
}
```

Response:

```json
{
  "token": "eyJhbGciOi..."
}
```

---

# Exemplo de uso do JWT

Header:

```text
Authorization: Bearer TOKEN
```

---

# Swagger/OpenAPI

Swagger disponível em:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Banco de Dados

Banco utilizado:
- MySQL

Configuração localizada em:

```text
src/main/resources/application.properties
```

---

# Regras de Negócio Implementadas

## Usuários
- Email não pode ser duplicado
- Limite de moradores por apartamento
- Senha criptografada automaticamente

## Reservas
- Não permite conflito de horários
- Reserva inicia como PENDING

## Veículos
- Placa não pode ser duplicada
- Veículo pode ser bloqueado

## Visitantes
- Visitante deve possuir morador responsável
- Visitante expirado não pode entrar

## Encomendas
- Não permite retirada duplicada

## Documentos
- Apenas PDF/imagens
- Documento deve possuir arquivo

---

# Testes

O projeto possui:

- Testes unitários
- Mockito
- JaCoCo
- Testes estruturais (caixa-branca)
- Testes funcionais (Swagger)

## Cobertura

Relatório JaCoCo:

```text
build/reports/jacoco/test/html/index.html
```

---

# Como Executar

## 1. Clonar projeto

```bash
git clone URL_DO_REPOSITORIO
```

## 2. Configurar banco MySQL

Criar banco:

```sql
CREATE DATABASE condominio;
```

## 3. Configurar application.properties

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/condominio
spring.datasource.username=root
spring.datasource.password=senha
```

## 4. Executar aplicação

```bash
./gradlew bootRun
```

ou

```bash
gradle bootRun
```

---

# Executar Testes

```bash
./gradlew test
```

---

# Executar JaCoCo

```bash
./gradlew jacocoTestReport
```

---

# Responsabilidades

## Backend
- APIs REST
- Segurança JWT
- Regras de negócio
- Persistência de dados
- Testes unitários

---

# Integrantes

Este projeto foi realizado com a colaboração dos seguintes membros, listados em ordem alfabética:

- <a href="https://www.linkedin.com/in/rirfit/">Gabriel de Oliveira</a>
- <a href="https://www.linkedin.com/in/gustavo-morais-arruda/">Gustavo Morais</a>
- <a href="https://www.linkedin.com/in/joseclaudiley/">José Claudiley</a>
- <a href="https://www.linkedin.com/in/kauan-pires-21aa12288/">Kauan Pires</a>

---

# Observações

Projeto desenvolvido para a disciplina de Laboratório de Desenvolvimento Multiplataforma - FATEC.
