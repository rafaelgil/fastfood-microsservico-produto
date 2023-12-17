package br.com.fiap.postech.fastfood.produto.domain.entity

import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Descricao
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Preco
import java.util.*

data class Produto (
    var id: UUID? = null,
    var descricao: Descricao? = null,
    var categoria: Categoria? = null,
    var preco: Preco? = null
)