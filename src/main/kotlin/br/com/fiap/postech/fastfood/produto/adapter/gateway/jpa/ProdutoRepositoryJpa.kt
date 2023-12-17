package br.com.fiap.postech.fastfood.produto.adapter.gateway.jpa

import br.com.fiap.postech.fastfood.produto.adapter.gateway.schema.ProdutoSchema
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ProdutoRepositoryJpa : JpaRepository<ProdutoSchema, UUID> {
    fun findByCategoria(categoria: CategoriaProduto): List<ProdutoSchema>?
}