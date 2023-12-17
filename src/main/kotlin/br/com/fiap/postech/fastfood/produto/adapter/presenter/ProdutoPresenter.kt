package br.com.fiap.postech.fastfood.produto.adapter.presenter

import br.com.fiap.postech.fastfood.produto.adapter.gateway.schema.ProdutoSchema
import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Descricao
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Preco
import java.math.BigDecimal
import java.util.*

data class ProdutoRequest(var id: UUID? = null,
                          var descricao: String,
                          var categoria: String,
                          var preco: BigDecimal
) {
    constructor(descricao: String,
                categoria: String,
                preco: BigDecimal) : this(null, descricao, categoria, preco) {
    }
}

data class ProdutoResponse(var id: UUID,
                          var descricao: String,
                          var categoria: String,
                          var preco: BigDecimal
)

fun ProdutoRequest.toProduto(): Produto {
    return Produto(
        id = this.id,
        descricao = Descricao(this.descricao),
        categoria = Categoria(this.categoria),
        preco = Preco(this.preco)
    )
}

fun Produto.toProdutoResponse(): ProdutoResponse {
    return ProdutoResponse(
        id = this.id!!,
        descricao = this.descricao!!.descricao,
        categoria = this.categoria!!.categoria,
        preco = this.preco!!.valor
    )
}

fun Produto.toProdutoSchema(id: UUID? = null): ProdutoSchema {
    return ProdutoSchema(
        id = id,
        descricao = this.descricao!!.descricao,
        categoria = CategoriaProduto.valueOf(this.categoria!!.categoria.uppercase()),
        preco = this.preco!!.valor
    )
}

fun ProdutoSchema.toProduto(): Produto {
    return Produto(
        id = this.id,
        descricao = Descricao(this.descricao),
        categoria = Categoria(this.categoria.name),
        preco = Preco(this.preco)
    )
}