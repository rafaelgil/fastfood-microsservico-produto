package br.com.fiap.postech.fastfood.produto.domain.usecase.produto

import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto

class BuscarProdutoPorCategoriaUseCase(
    private val produtoRepository: ProdutoRepository
) {

    fun executa(categoria: String): List<Produto> {
        val categoria = CategoriaProduto.valueOf(Categoria(categoria).categoria.toUpperCase())
        return produtoRepository.buscarPorCategoria(categoria)
    }
}