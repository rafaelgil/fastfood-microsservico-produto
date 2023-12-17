package br.com.fiap.postech.fastfood.produto.domain.valueObjets

import java.math.BigDecimal

class Descricao(val descricao: String) {
    init {
        require(!descricao.trim().isBlank()) {
            "Descrição deve ser informado"
        }
    }
}

class Categoria(val categoria: String) {
    init {
        require(CategoriaProduto::class.java.enumConstants.any { it.name == categoria.toUpperCase()}) {
            "Categoria inválida"
        }
    }
}

class Preco(val valor: BigDecimal) {
    init {
        require(valor.compareTo(BigDecimal.ZERO) > 0) {
            "Preco não pode ser menor ou igual a zero"
        }
    }
}
enum class CategoriaProduto{
    LANCHE,
    BEBIDA,
    ACOMPANHAMENTO,
    SOBREMESA
}