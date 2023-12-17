package br.com.fiap.postech.fastfood.produto.domain.usecase.produto

import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository

class CadastrarProdutoUseCase(
    private val produtoRepository: ProdutoRepository
) {

    fun executa(produto: Produto): Produto {

        return produtoRepository.cadastrar(produto)
    }
}