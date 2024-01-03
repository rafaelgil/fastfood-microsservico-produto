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
import org.mockito.invocation.InvocationOnMock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.stubbing.Answer
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.math.BigDecimal
import java.util.*


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
            .setControllerAdvice(ControllerAdvice()).build()
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

        private fun produtoRequest(
            descricao: String? = null,
            categoria: String? = null,
            preco: BigDecimal? = null
        ): ProdutoRequest {
            return ProdutoRequest(
                descricao = descricao ?: "X-Bacon",
                categoria = categoria ?: "lanche",
                preco = preco ?: BigDecimal("50.00")
            )
        }

        private fun produtoResponse(): Produto {
            return Produto(
                id = UUID.randomUUID(),
                descricao = Descricao(produtoRequest().descricao),
                categoria = Categoria(produtoRequest().categoria),
                preco = Preco(produtoRequest().preco)
            )
        }

        private fun asJsonString(obj: Any?): String {
            try {
                return ObjectMapper().writeValueAsString(obj)
            } catch (e: java.lang.Exception) {
                throw RuntimeException(e)
            }
        }
    }
}