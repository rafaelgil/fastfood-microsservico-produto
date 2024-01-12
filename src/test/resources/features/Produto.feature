# language: pt
Funcionalidade: API - Produtos

  Cenário: Cadastrar um novo produto
    Quando submeter um novo produto
    Então o produto é registrada com sucesso

  Cenário: Buscar um produto existente
    Dado que produto já foi cadastrado
    Quando requisitar a busca do produto
    Então o produto é exibido com sucesso

  Cenário: Buscar produtos por categoria
    Dado que produto já foi cadastrado
    Quando requisitar a busca por categoria
    Então os produtos são exibidos com sucesso

  Cenário: Alterar um produto existente
    Dado que produto já foi cadastrado
    Quando requisitar a alteração do produto
    Então o produto é atualizado com sucesso

  Cenário: Excluir um produto existente
    Dado que produto já foi cadastrado
    Quando requisitar a exclusão do produto
    Então o produto é removido com sucesso