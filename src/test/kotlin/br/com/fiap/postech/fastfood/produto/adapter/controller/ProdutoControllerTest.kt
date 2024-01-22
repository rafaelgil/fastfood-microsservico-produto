package br.com.fiap.postech.fastfood.produto.adapter.controller

import br.com.fiap.postech.fastfood.produto.adapter.presenter.ProdutoRequest
import br.com.fiap.postech.fastfood.produto.domain.entity.Produto
import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.*
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Categoria
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Descricao
import br.com.fiap.postech.fastfood.produto.domain.valueObjets.Preco
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal
import java.util.*
import java.util.UUID


class ProdutoControllerTest {

    lateinit var mockMvc: MockMvc
    lateinit var openMocks: AutoCloseable

    @Mock
    lateinit var cadastrarProdutoUseCase: CadastrarProdutoUseCase

    @Mock
    lateinit var atualizarProdutoUseCase: AtualizarProdutoUseCase

    @Mock
    lateinit var removerProdutoUseCase: RemoverProdutoUseCase

    @Mock
    lateinit var buscarProdutoPorCategoriaUseCase: BuscarProdutoPorCategoriaUseCase

    @Mock
    lateinit var buscarProdutoPorIdUseCase: BuscarProdutoPorIdUseCase

    @BeforeEach
    fun setUp() {
        openMocks = MockitoAnnotations.openMocks(this)
        var produtoController = ProdutoController(
            cadastrarProdutoUseCase,
            atualizarProdutoUseCase,
            removerProdutoUseCase,
            buscarProdutoPorCategoriaUseCase,
            buscarProdutoPorIdUseCase
        )
        mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
            .setControllerAdvice(ControllerAdvice())
            .build()
    }

    @AfterEach
    @Throws(Exception::class)
    fun tearDown() {
        openMocks.close()
    }

    @Nested
    inner class CadastrarProduto {

        @Test
        fun devePermitirCadastrarUmProduto() {
            `when`(cadastrarProdutoUseCase.executa(any()))
                .thenReturn(produtoResponse())

            mockMvc.perform(
                post("/produto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest()))
            )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.preco").value("50.0"))

            verify(cadastrarProdutoUseCase, times(1)).executa(any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoCadastrarCategoriaInvalida`() {
            mockMvc.perform(
                post("/produto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest(categoria = "almoço")))
            )
                .andExpect(status().is4xxClientError)
                .andExpect(jsonPath("$.mensagem").value("Categoria inválida"))

            verify(cadastrarProdutoUseCase, never()).executa(any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoCadastrarDescricaoInvalida`() {
            mockMvc.perform(
                post("/produto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest(descricao = "")))
            )
                .andExpect(status().is4xxClientError)
                .andExpect(jsonPath("$.mensagem").value("Descrição deve ser informado"))

            verify(cadastrarProdutoUseCase, never()).executa(any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoCadastrarPrecoInvalida`() {
            mockMvc.perform(
                post("/produto")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest(preco = BigDecimal(0))))
            )
                .andExpect(status().is4xxClientError)
                .andExpect(jsonPath("$.mensagem").value("Preco não pode ser menor ou igual a zero"))

            verify(cadastrarProdutoUseCase, never()).executa(any())
        }
    }

    @Nested
    inner class AtualizarProduto {

        @Test
        fun devePermitirAtualizarUmProduto() {
            `when`(atualizarProdutoUseCase.executa(any(), any()))
                .thenReturn(produtoResponse())

            mockMvc.perform(
                put("/produto/{id}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest()))
            ).andExpect(status().isOk)

            verify(atualizarProdutoUseCase, times(1)).executa(any(), any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoAtualizarPrecoInvalido`() {
            mockMvc.perform(
                put("/produto", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(produtoRequest(preco = BigDecimal("0"))))
            ).andExpect(status().is4xxClientError)

            verify(atualizarProdutoUseCase, never()).executa(any(), any())
        }
    }

    @Nested
    inner class RemoverProduto {

        @Test
        fun devePermitirRemoverUmProduto() {

            mockMvc.perform(
                delete("/produto/{id}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(UUID.randomUUID()))
            ).andExpect(status().isOk)

            verify(removerProdutoUseCase, times(1)).executa(any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoDeletaProduto_comIdInvalido`() {
            mockMvc.perform(
                delete("/produto/{id}", "2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(UUID.randomUUID()))
            ).andExpect(status().is4xxClientError)

            verify(removerProdutoUseCase, never()).executa(any())
        }
    }

    @Nested
    inner class BuscaProduto {

        @Test
        fun devePermitirBuscarUmProduto() {
            `when`(buscarProdutoPorIdUseCase.executa(any()))
                .thenReturn(produtoResponse())

            mockMvc.perform(
                get("/produto/{id}", UUID.randomUUID())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(UUID.randomUUID()))
            ).andExpect(status().isOk)

            verify(buscarProdutoPorIdUseCase, times(1)).executa(any())
        }

        @Test
        @Throws(java.lang.Exception::class)
        fun `deveGerarExcecao_QuandoBuscaProduto_comIdInvalido`() {
            mockMvc.perform(
                get("/produto/{id}", "2")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(UUID.randomUUID()))
            ).andExpect(status().is4xxClientError)

            verify(buscarProdutoPorIdUseCase, never()).executa(any())
        }
    }

    @Nested
    inner class BuscarProdutoPorCategoria {

        @Test
        fun devePermitirBuscarProdutoPorCategoria() {

            val listaDeProdutos = mutableListOf<Produto>()
            listaDeProdutos.add(produtoResponse())

            `when`(buscarProdutoPorCategoriaUseCase.executa(any()))
                .thenReturn(listaDeProdutos)

            mockMvc.perform(
                get("/produto/categoria?nome=lanche")
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(status().isOk)

            verify(buscarProdutoPorCategoriaUseCase, times(1)).executa(any())
        }
    }

    fun produtoRequest(
        id: UUID? = null,
        descricao: String? = null,
        categoria: String? = null,
        preco: BigDecimal? = null
    ): ProdutoRequest {
        return ProdutoRequest(
            id = id,
            descricao = descricao ?: "X-Bacon",
            categoria = categoria ?: "lanche",
            preco = preco ?: BigDecimal("50.00")
        )
    }

    fun produtoResponse(
        id: UUID? = null,
        descricao: String? = null,
        categoria: String? = null,
        preco: BigDecimal? = null
    ): Produto {
        return Produto(
            id = id ?: UUID.randomUUID(),
            descricao = Descricao(descricao ?: produtoRequest().descricao),
            categoria = Categoria(categoria ?: produtoRequest().categoria),
            preco = Preco(preco ?: produtoRequest().preco)
        )
    }

    fun asJsonString(obj: Any?): String {
        try {
            return ObjectMapper().writeValueAsString(obj)
        } catch (e: java.lang.Exception) {
            throw RuntimeException(e)
        }
    }
}