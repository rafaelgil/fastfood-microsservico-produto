package br.com.fiap.postech.fastfood.produto.adapter.gateway

import br.com.fiap.postech.fastfood.produto.adapter.gateway.jpa.ProdutoRepositoryJpa
import br.com.fiap.postech.fastfood.produto.adapter.gateway.schema.ProdutoSchema
import br.com.fiap.postech.fastfood.produto.adapter.presenter.toProdutoSchema
import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.CategoriaProduto
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Descricao
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Preco
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import java.math.BigDecimal
import java.util.*

class ProdutoRepositoryImplTest {

    lateinit var produtoRepositoryImpl: ProdutoRepositoryImpl

    lateinit var openMocks: AutoCloseable

    lateinit var idUUID: UUID

    @Mock
    lateinit var produtoRepository: ProdutoRepositoryJpa

    @BeforeEach
    fun setUp() {
        idUUID = UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002")
        openMocks = MockitoAnnotations.openMocks(this)
        produtoRepositoryImpl = ProdutoRepositoryImpl(
            produtoRepository,
        )
    }

    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        openMocks.close()
    }

    @Test
    fun devePermitirCadastrarProduto() {

        `when`(produtoRepository.save(any()))
            .thenReturn(createProduto().toProdutoSchema())

        var cadastrar = produtoRepositoryImpl.cadastrar(createProduto())

        assertThat(cadastrar).isNotNull()
        assertThat(cadastrar.categoria?.categoria).isEqualTo(Categoria("lanche").categoria.toUpperCase())
        verify(produtoRepository, times(1)).save(any())
    }

    @Test
    fun devePermitirAtualizarProduto() {

        `when`(produtoRepository.save(any()))
            .thenReturn(createProduto().toProdutoSchema())

        var cadastrar = produtoRepositoryImpl.atualizar(idUUID, createProduto())

        assertThat(cadastrar).isNotNull()
        assertThat(cadastrar.categoria?.categoria).isEqualTo(Categoria("lanche").categoria.toUpperCase())
        verify(produtoRepository, times(1)).save(any())
    }

    @Test
    fun devePermitirBuscarPorCategoria() {

        val listaDeProdutos = mutableListOf<ProdutoSchema>()
        listaDeProdutos.add(createProduto().toProdutoSchema())

        `when`(produtoRepository.findByCategoria(any()))
            .thenReturn(listaDeProdutos)

        var buscarPorCategoria = produtoRepositoryImpl.buscarPorCategoria(CategoriaProduto.BEBIDA)

        assertThat(buscarPorCategoria).isNotNull()
        verify(produtoRepository, times(1)).findByCategoria(any())
    }

    @Test
    fun devePermitirExisteProduto() {

        `when`(produtoRepository.existsById(any()))
            .thenReturn(true)

        var existeProduto = produtoRepositoryImpl.existeProduto(idUUID)

        assertThat(existeProduto).isNotNull()
        assertThat(existeProduto).isTrue()
        verify(produtoRepository, times(1)).existsById(any())
    }

    @Test
    fun devePermitirDeletarProduto() {

        produtoRepositoryImpl.deletaProduto(idUUID)

        verify(produtoRepository, times(1)).deleteById(any())
    }

    @Test
    fun devePermitirBuscaPorId() {

        val optionalValue: Optional<ProdutoSchema> = Optional.of(createProduto().toProdutoSchema())

        `when`(produtoRepository.findById(any()))
            .thenReturn(optionalValue)

        var buscaPorId = produtoRepositoryImpl.buscaPorId(idUUID)

        assertThat(buscaPorId).isNotNull()
        assertThat(buscaPorId?.categoria?.categoria).isEqualTo(Categoria("lanche").categoria.toUpperCase())
        verify(produtoRepository, times(1)).findById(any())
    }

    @Test
    fun devePermitirBuscaPorIdNulla() {

        val optionalValue: Optional<ProdutoSchema> = Optional.of(createProduto().toProdutoSchema())

        `when`(produtoRepository.findById(any()))
            .thenReturn(optionalValue)

        var buscaPorId = produtoRepositoryImpl.buscaPorId(UUID.randomUUID())

        assertThat(buscaPorId?.id).isNotEqualTo(idUUID)
        verify(produtoRepository, times(1)).findById(any())
    }

    fun createProduto(
        id: UUID? = null,
        descricao: String? = null,
        categoria: String? = null,
        preco: BigDecimal? = null
    ): Produto {
        return Produto(
            id = id ?: idUUID,
            descricao = Descricao(descricao ?: "X-Bacon"),
            categoria = Categoria(categoria ?: "lanche"),
            preco = Preco(preco ?: BigDecimal("50.00"))
        )
    }
}