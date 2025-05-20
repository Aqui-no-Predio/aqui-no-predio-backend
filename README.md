# 🏢 Aqui no Prédio

<div align="center">
  <h3>Uma plataforma para integração de serviços e comunicação em condomínios</h3>

[![Java](https://img.shields.io/badge/Java-17-ED8B00?logo=java&logoColor=white&style=flat)](https://www.oracle.com/br/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![TypeScript](https://img.shields.io/badge/TypeScript-5-3178C6?logo=typescript&logoColor=white&style=flat)](https://www.typescriptlang.org/)
[![Angular](https://img.shields.io/badge/Angular-19-red.svg)](https://angular.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
</div>

## 🌟 Sobre o Projeto

**Aqui no Prédio** é uma plataforma que facilita a comunicação e o acesso a serviços dentro de condomínios residenciais. O sistema permite o registro de pets, divulgação de anúncios e oferta de serviços entre moradores, criando um ambiente de comunidade e colaboração.

## 💻 Tecnologias Utilizadas

### Backend
- **Java JDK 17**
- **Spring Boot 3.4.4**
- **MySQL 8.0** (Banco de Dados)
- **Maven**

### Frontend
- **Angular 19.2.0**
- **TypeScript 5.7.2**
- **HTML5/CSS3**


## 🔧 Configuração do Ambiente

### 📦 Pré-requisitos

Para executar este projeto, você precisará instalar ou ter instalado:

- [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL Server 8.0](https://dev.mysql.com/downloads/mysql/)
- [MySQL Workbench](https://dev.mysql.com/downloads/workbench/)
- Uma IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) e/ou [VS Code](https://code.visualstudio.com/download)

### ⚙️ Instalação do Backend

#### 1. Clone o repositório
```bash
git clone https://github.com/Aqui-no-Predio/aqui-no-predio-backend.git
cd aqui-no-predio-backend
```

#### 2. Configure o MySQL

- Abra o MySQL Workbench e conecte-se à sua instância local
- Crie um usuário para o projeto (ou use o usuário root)

#### 3. Configure o ambiente de desenvolvimento

- Abra o arquivo `src/main/resources/application.properties` e altere:
  ```properties
  spring.profiles.active=prod
  ```
  para
  ```properties
  spring.profiles.active=dev
  ```

- Abra o arquivo `src/main/resources/application-dev.properties` e configure:
  ```properties
  spring.datasource.url=${DB_URL:jdbc:mysql://localhost/db_aquinopredio?createDatabaseIfNotExist=true&serverTimezone=America/Sao_Paulo&useSSL=false&allowPublicKeyRetrieval=true}
  spring.datasource.username=${DB_USERNAME:seu_usuario}
  spring.datasource.password=${DB_PASSWORD:sua_senha_aqui}
  app.jwt.secret=${JWT_SECRET:ChaveDeAcessoEmDEV}
  ```

  > ⚠️ **IMPORTANTE**: Substitua `seu_usuario` e `sua_senha_aqui` pelos valores que você criou no MySQL.
  >
  > ⚠️ **ATENÇÃO**: Note o parâmetro `allowPublicKeyRetrieval=true` na URL, ele é necessário para conexões seguras com MySQL 8+.

#### 4. Instale as dependências

Pelo terminal dentro da pasta do projeto, execute:

```bash
mvn clean install -DskipTests
```

#### 5. Execute o backend

**Usando o Maven**:
```bash
mvn spring-boot:run -DskipTests
```

#### 6. Verifique se o backend está funcionando

Acesse o Swagger para verificar as APIs disponíveis:
```
http://localhost:8080/swagger-ui/index.html
```

### 🖥️ Instalação do Frontend

#### 1. Clone o repositório
```bash
git clone https://github.com/Aqui-no-Predio/aqui-no-predio-frontend.git
cd aqui-no-predio-frontend
```

#### 2. Instale o Angular CLI (se ainda não tiver)
```bash
npm install -g @angular/cli
```

#### 3. Instale as dependências do projeto
```bash
npm install
```

#### 4. Execute o frontend

```bash
npm start 
```

O frontend estará disponível em `http://localhost:4200`.

## 🚀 Executando o Projeto

Para o sistema funcionar corretamente, é necessário executar tanto o backend quanto o frontend simultaneamente.

---

<div align="center">
  <p>
    <b>Projeto desenvolvido por alunos da UNIVESP - 2025</b>
    <br>
    <a href="https://univesp.br/">UNIVESP - Universidade Virtual do Estado de São Paulo</a>
  </p>
</div>