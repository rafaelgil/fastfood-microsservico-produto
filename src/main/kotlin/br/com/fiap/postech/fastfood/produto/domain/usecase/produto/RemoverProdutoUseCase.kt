package br.com.fiap.postech.fastfood.produto.domain.usecase.produto

import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import java.util.*

class RemoverProdutoUseCase(
    private val produtoRepository: ProdutoRepository
) {
    fun executa(id: UUID) {
        if(produtoRepository.existeProduto(id))
            produtoRepository.deletaProduto(id)
        else {
            throw IllegalArgumentException("Produto $id não encontrado")
        }
    }
}