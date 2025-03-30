# 🏥 Sistema de Gestão Hospitalar

Este é um sistema de gestão hospitalar desenvolvido para auxiliar no gerenciamento de pacientes, prontuários, internações, agendamentos e demais operações hospitalares. O objetivo é otimizar processos internos e melhorar a eficiência da administração hospitalar.

## 🚀 Funcionalidades

- 📋 **Cadastro de Usuários**: Pacientes, profissionais de saúde e administradores.
- 📑 **Gerenciamento de Prontuários**: Registro de históricos médicos dos pacientes.
- 🩺 **Agendamentos de Consultas e Exames**: Controle de horários e profissionais responsáveis.
- 💊 **Prescrições Médicas**: Cadastro de medicamentos e instruções de uso.
- 🏨 **Gestão de Internações e Leitos**: Controle de internações, trocas de leito e suprimentos utilizados.
- 📊 **Auditoria de Operações**: Registro de modificações no sistema para rastreabilidade.
- 💰 **Relatórios Financeiros**: Monitoramento de custos hospitalares.

## 🛠️ Tecnologias Utilizadas

- **Back-end**: Java com Spring Boot
- **Banco de Dados**: PostgreSQL
- **Front-end**: HTML, CSS, JavaScript *(futuro desenvolvimento)*
- **Gerenciamento de Dependências**: Maven
- **Versionamento de Código**: Git e GitHub

## 📂 Estrutura do Banco de Dados

O sistema utiliza PostgreSQL e possui tabelas para usuários, permissões, prontuários, agendamentos, internações, suprimentos, entre outras.

Abaixo está o diagrama entidade-relacionamento (DER) do banco de dados utilizado:

![Diagram Entidade Relacionamento drawio](https://github.com/user-attachments/assets/d87c8257-607a-46d0-a3be-8500decd606f)

## 🏗️ Como Executar o Projeto

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git

2. **Configure o banco de dados no PostgreSQL**:
- Crie um banco de dados chamado **`gestao_hospitalar`**.
- Execute os scripts de criação das tabelas.

3. **Configure o ambiente no IntelliJ IDEA**:
- Importe o projeto como um **projeto Maven**.
- Configure o arquivo **`application.properties`** com as credenciais do banco de dados.

4. **Execute o projeto**:
- No IntelliJ, rode a classe principal **`MainApplication.java`**.
- A API estará disponível em **[`http://localhost:8080`](http://localhost:8080)**.
