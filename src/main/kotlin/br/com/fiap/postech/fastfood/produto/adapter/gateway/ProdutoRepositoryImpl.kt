package br.com.fiap.postech.fastfood.produto.adapter.gateway

import br.com.fiap.postech.fastfood.produto.adapter.gateway.jpa.ProdutoRepositoryJpa
import br.com.fiap.postech.fastfood.produto.adapter.presenter.toProduto
import br.com.fiap.postech.fastfood.produto.adapter.presenter.toProdutoSchema
import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto
import java.util.*
import java.util.stream.Collectors

class ProdutoRepositoryImpl(
    private val produtoRepositoryJpa: ProdutoRepositoryJpa
): ProdutoRepository {

    override fun cadastrar(produto: Produto): Produto {
        return produtoRepositoryJpa.save(produto.toProdutoSchema()).toProduto()
    }

    override fun atualizar(id: UUID, produto: Produto): Produto {
        return produtoRepositoryJpa.save(produto.toProdutoSchema(id)).toProduto()
    }

    override fun buscarPorCategoria(categoria: CategoriaProduto): List<Produto> {
        return produtoRepositoryJpa.findByCategoria(categoria)?.
                                    stream()?.
                                    map { it.toProduto() }!!.
                                    collect(Collectors.toList())
    }

    override fun existeProduto(id: UUID): Boolean {
        return produtoRepositoryJpa.existsById(id)
    }

    override fun deletaProduto(id: UUID) {
        produtoRepositoryJpa.deleteById(id)
    }

    override fun buscaPorId(id: UUID): Produto? {
        var produto = produtoRepositoryJpa.findById(id)

        if (produto.isPresent) {
            return produto.get().toProduto()
        }
        return null
    }
}