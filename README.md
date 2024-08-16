# Projeto Reserva de Restaurantes

Este repositório contém o código-fonte do projeto "Reserva de Restaurantes". O projeto utiliza várias tecnologias modernas para construir uma aplicação de reserva de restaurantes com funcionalidades como gerenciamento de clientes, reservas, restaurantes e avaliações.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Java**: Linguagem de programação principal utilizada.
- **Docker**: Para empacotamento e distribuição da aplicação em containers.
- **MongoDB**: Banco de dados não relacional para persistência de dados.
- **Swagger**: Para documentação e testes da API.
- **Maven**: Gerenciamento de dependências e build do projeto.
- **ModelMapper**: Para conversão entre entidades e DTOs.

## Estrutura do Projeto

### Funcionalidades

- **Gerenciamento de Clientes**: Usuários podem se registrar para realizar a reserva de restaurantes.
- **Gerenciamento de Restaurantes**: Permite a criação, atualização e exclusão de restaurantes.
- **Gerenciamento de Reservas**: Usuários podem fazer e gerenciar reservas em restaurantes.
- **Gerenciamento de Avaliações**: Usuários podem deixar e visualizar avaliações para restaurantes.

### Documentação da API

A documentação da API está disponível através do Swagger. Após iniciar a aplicação, acesse [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) para visualizar e testar os endpoints da API.

## Execução do Projeto

Para executar o projeto localmente, siga os passos abaixo:

1. Clone este repositório.
2. Configure o banco de dados MongoDB e ajuste o arquivo `application.yml` conforme necessário.
3. Utilize Docker para empacotar a aplicação em um container.
4. Execute os containers utilizando `docker-compose up --build` para facilitar a inicialização da aplicação.
