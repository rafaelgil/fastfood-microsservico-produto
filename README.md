# fast-food
Sistema de controle de pedidos que permite aos clientes selecionar e fazer os pedidos sem interagir com o atendente e integração com as areas da lanchonete como cozinha e o balcão para recebimento.

## Tecnologias
* Kotlin
* Database Migration
* Spring Boot Data Jpa
* Spring Boot Web
* Docker
* Docker Compose

## Banco de Dados
* PostgreSQL

### Comandos para iniciar a aplicação
Iniciar a aplicação, a porta da aplicação é 8080(http://localhost:8080)
```bash
docker compose up -d
```
Parar a aplicação
```bash
docker compose down
```

## Endpoints
### Criação de Clientes
 ```bash
 curl --location 'http://localhost:8080/cliente' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cpf":"45612378952",
    "nome":"João da Silva",
    "email":"joao@mock.com"
}'
```

### Buscar Clientes por CPF
```bash
curl --request GET \
  --url 'http://localhost:8080/cliente?cpf=45612378952' \
  --header 'Content-Type: application/json'
```
### Cadastrar produto
```bash
curl --location 'http://localhost:8080/produto' \
--header 'Content-Type: application/json' \
--data '{
    "descricao": "X-Bacon",
    "categoria": "Lanche",
    "preco": "45.56"
}'
```
OBS: As categorias aceitas são LANCHE, BEBIDA, ACOMPANHAMENTO e SOBREMESA

### Atualizar produto
```bash
curl --location --request PUT 'http://localhost:8080/produto/6c90811d-08ca-4116-a900-5a6f420ac1c1' \
--header 'Content-Type: application/json' \
--data '{    
    "descricao": "X-Bacon",
    "categoria": "LANCHE",
    "preco": 34.00
}'
```

### Buscar por categoria
```bash
curl --location 'http://localhost:8080/produto/categoria?nome=lanche' \
--header 'Content-Type: application/json'
```

### Remover produto
```bash
curl --location --request DELETE 'http://localhost:8080/produto/6c90811d-08ca-4116-a900-5a6f420ac1c1' \
--header 'Content-Type: application/json'
```

### Criar checkout do pedido
```bash 
curl --request POST \
  --url http://localhost:8080/pedidos/checkout \
  --header 'Content-Type: application/json' \
  --data '{
	"id_cliente":"13907e60-0971-4856-93ba-7e184916e0e6",
	"itens": [
		{
			"id_produto":"abf2da35-a638-4db6-a553-00a9dfd76e4e",
			"quantidade": 1,
			"preco": 39.99
		},
		{
			"id_produto":"a2a22642-e7e1-4cae-9b18-db2f7c0f3f42",
			"quantidade": 1,
			"preco": 9.99
		},
		{
			"id_produto":"8e2053d7-d93a-4b4a-ade6-f53c4e043730",
			"quantidade": 2,
			"preco": 20.00
		}
	]
}'
```

### Listar pedido por id
```bash 
curl --request GET \
  --url http://localhost:8080/pedidos/eee0b41a-f582-4288-8548-9292ac95f2ec
```

### Acompanhar o status do pedido
```bash 
curl --request GET \
  --url http://localhost:8080/pedidos/eee0b41a-f582-4288-8548-9292ac95f2ec
```

### Lista de pedidos ordenados por recebimento e por status
```bash 
curl --request GET \
  --url http://localhost:8080/pedidos
```

### Notificar o recebimento de pagamento
```bash
curl --request PUT \
--url http://localhost:8080/checkouts/76178a33-114f-44d8-b817-c5e06674a0ac/webhook/pagar \
  --header 'Content-Type: application/json'
```

### Mudar o status do pedido para Em Preparação
```bash
curl --request PUT \
  --url http://localhost:8080/pedidos/ba90138d-0fc1-4555-9e3f-5ca79a8e2cb5/mudar-status/preparacao
```

### Mudar o status do pedido para Pronto
```bash
curl --request PUT \
  --http://localhost:8080/pedidos/ba90138d-0fc1-4555-9e3f-5ca79a8e2cb5/mudar-status/pronto
```

### Mudar o status do pedido para Finalizado
```bash
curl --request PUT \
  --url http://localhost:8080/pedidos/eee0b41a-f582-4288-8548-9292ac95f2ec/mudar-status/confirmar-entrega
```

### Pipeline 4


