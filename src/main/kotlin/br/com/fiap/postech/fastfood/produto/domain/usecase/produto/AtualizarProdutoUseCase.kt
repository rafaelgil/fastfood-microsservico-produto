package br.com.fiap.postech.fastfood.produto.domain.usecase.produto

import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import java.util.*

class AtualizarProdutoUseCase(
    private val produtoRepository: ProdutoRepository
) {
    fun executa(id: UUID, produto: Produto): Produto {
        if(produtoRepository.existeProduto(id)) {
            return produtoRepository.atualizar(id, produto)
        } else {
            throw IllegalArgumentException("Produto ${id} não encontrado")
        }
    }
}