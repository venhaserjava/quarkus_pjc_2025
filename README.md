# 🛠️ quarkus_pjc_2025 - PROJETO PRÁTICO IMPLEMENTAÇÃO BACK-END

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Quarkus](https://img.shields.io/badge/Quarkus-4695EB?style=for-the-badge&logo=quarkus&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![MinIO](https://img.shields.io/badge/MinIO-EF2D5E?style=for-the-badge&logo=min.io&logoColor=white)
![Maven](https://img.shields.io/badge/maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![OpenAPI](https://img.shields.io/badge/OpenAPI-6BA539?style=for-the-badge&logo=openapi-initiative&logoColor=white)

---

## 📌 Índice

- [📄 Descrição](#descrição)
- [🎯 Finalidade](#finalidade)
- [🛠️ Tecnologias Utilizadas](#tecnologias-utilizadas)
- [⚙️ Instalação e Execução](#instalação-e-execução)
- [📚 Documentação da API](#documentação-da-api)
- [🔐 Autenticação](#autenticação)
- [🗄️ Banco de Dados](#banco-de-dados)

---

## 📄 Descrição

**quarkus_pjc_2025** é uma **API RESTful** desenvolvida com o framework **Quarkus**, com foco em performance e simplicidade. O sistema realiza o gerenciamento de **Servidores Públicos**, contemplando:

- Cadastro, edição e consulta de dados pessoais e profissionais;
- Upload de **fotos** dos servidores;
- Registro e distinção entre:
    - **Servidor Temporário**
    - **Servidor Efetivo** (com dados de lotação/unidade);
- Armazenamento de fotos com **MinIO**;
- Segurança com **JWT + Refresh Token**.

---

## 🎯 Finalidade

Este projeto foi desenvolvido para a banca examinadora do processo seletivo:

> **EDITAL DE PROCESSO SELETIVO SIMPLIFICADO Nº 002/2025**  
> **SEPLAG – Secretaria de Estado de Planejamento e Gestão**

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Quarkus Framework** (RESTEasy Reactive, Hibernate ORM, Panache, Security JWT, OpenAPI)
- **PostgreSQL**
- **MinIO** (S3-compatible object storage)
- **JWT (JSON Web Token)** com **refresh token**
- **Docker** + **Docker Compose**

---

## ⚙️ Instalação e Execução

### 1. Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### 2. Build da aplicação

```bash
./mvnw clean package -Dquarkus.package.type=uber-jar
```

## 📚 Documentação da API
Toda a documentação dos endpoints está disponível no arquivo api-docs.md.

Se estiver usando Quarkus com OpenAPI, acesse:

http://localhost:8080/q/swagger-ui

## 🔐 Autenticação
A autenticação é feita via JWT Token. Após o login (/auth/login), um token de acesso e um refresh token são retornados.

Fluxo de autenticação:

Login → JWT Token + Refresh

Renovação → Enviar refreshToken em /auth/refresh

## 🗄️ Banco de Dados
O banco usado é o PostgreSQL. As credenciais padrão (ajustáveis via .env):

DB: spring_pjc_2025_db

User: postgres

Password: root_pwd

Para criar ou resetar o banco:
```
DROP DATABASE IF EXISTS quarkus_pjc_2025_db;
CREATE DATABASE quarkus_pjc_2025_db;
```
Caso precise registrar um usuário manualmente:
```
INSERT INTO usuario (username, password) VALUES (
  'admin',
  '$2a$10$w.xpQKqgqXPLv4l4oLZiUuj83J4tcyEMu7eDuh1vKugEMsC1blf7K'
);
```

## 📸 Upload de Fotos
O upload de fotos dos servidores é realizado por multipart/form-data no endpoint /servidores. 
Os arquivos são armazenados no MinIO e retornados com link de acesso temporário.

MinIO padrão:

URL: http://localhost:9001

Acesso: admin / adminpassword


