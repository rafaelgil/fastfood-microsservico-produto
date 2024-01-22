# fast-food Produto
Esse microsserviço faz parte de uma solução controle de pedidos que permite aos clientes selecionar e fazer os pedidos sem interagir com o atendente e integração com as areas da lanchonete como cozinha e o balcão para recebimento.

## Tecnologias
* Kotlin
* Database Migration
* Spring Boot Data Jpa
* Spring Boot Web
* Docker
* Docker Compose
* Cucumber

## Banco de Dados
* PostgreSQL

### Comandos para iniciar a aplicação

Subir ambiente local com docker compose
```bash
gradle build
docker compose up -d
```
A aplicação deverá criar uma imagem com o dockerfile local
Então você poderá fazer suas requisições na porta 8094

## Endpoints

### Cadastrar produto
 ```bash
curl --request POST \
  --url http://localhost:8094/produto \
  --header 'Content-Type: application/json' \
  --data '{
    "descricao": "x-burguer",
    "categoria": "lanche",
    "preco": "50.54"
}'
```
OBS: As categorias aceitas são LANCHE, BEBIDA, ACOMPANHAMENTO e SOBREMESA

### Buscar por categoria
 ```bash
curl --request GET \
  --url 'http://localhost:8094/produto/categoria?nome=lanche' \
  --header 'Content-Type: application/json'
```

### Buscar por Id
 ```bash
curl --request GET \
  --url http://localhost:8094/produto/f813515d-b0cf-4f46-b4a2-82612b77b130 \
  --header 'Content-Type: application/json'
```

### Atualizar produto
 ```bash
curl --request PUT \
  --url http://localhost:8094/produto/8dc62a6b-c8d7-41b9-8bf9-54acf08e2f40 \
  --header 'Content-Type: application/json' \
  --data '{
	"descricao": "x-burguer",
	"categoria": "Lanche",
	"preco": 8.74
}'
```

### Remover produto
 ```bash
curl --request DELETE \
  --url http://localhost:8094/produto/8dc62a6b-c8d7-41b9-8bf9-54acf08e2f40 \
  --header 'Content-Type: application/json'
```

