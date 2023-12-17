package br.com.fiap.postech.fastfood.produto.domain.usecase.produto

import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto
import java.util.*

class BuscarProdutoPorIdUseCase(
    private val produtoRepository: ProdutoRepository
) {

    fun executa(id: UUID): Produto {
        if(produtoRepository.existeProduto(id)) {
            return produtoRepository.buscaPorId(id)!!
        } else {
            throw IllegalArgumentException("Produto $id não encontrado")
        }
    }
}