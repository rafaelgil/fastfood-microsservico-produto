package br.com.fiap.postech.fastfood.produto.domain.exception

class ProdutoPrecoException(s: String, exception: Exception?): Exception(s, exception)
{
    constructor(s: String) : this(s, null)
}