package br.com.fiap.postech.fastfood.produto.bdd

import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Então
import io.cucumber.java.pt.Quando

class ProdutoStepDefinition {

    @Quando("submeter um novo produto")
    fun `submeter um novo produto`() {
        assert(true)
    }

    @Então("o produto é registrada com sucesso")
    fun `o produto é registrada com sucesso`() {
        assert(true)
    }

    @Dado("que produto já foi cadastrado")
    fun `que produto já foi cadastrado`() {
        assert(true)
    }

    @Quando("requisitar a busca do produto")
    fun `requisitar a busca do produto`() {
        assert(true)
    }

    @Então("o produto é exibido com sucesso")
    fun `o produto é exibido com sucesso`() {
        assert(true)
    }

    @Quando("requisitar a busca por categoria")
    fun `requisitar a busca por categoria`() {
        assert(true)
    }

    @Então("os produtos são exibidos com sucesso")
    fun `os produtos são exibidos com sucesso`() {
        assert(true)
    }

    @Quando("requisitar a alteração do produto")
    fun `requisitar a alteração do produto`() {
        assert(true)
    }

    @Então("o produto é atualizado com sucesso")
    fun `o produto é atualizado com sucesso`() {
        assert(true)
    }

    @Quando("requisitar a exclusão do produto")
    fun `requisitar a exclusão do produto`() {
        assert(true)
    }

    @Então("o produto é removido com sucesso")
    fun `o produto é removido com sucesso`() {
        assert(true)
    }

}