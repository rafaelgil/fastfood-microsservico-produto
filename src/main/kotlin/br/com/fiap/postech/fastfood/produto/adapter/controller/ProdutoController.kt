package br.com.fiap.postech.fastfood.produto.adapter.controller

import br.com.fiap.postech.fastfood.produto.adapter.presenter.ProdutoRequest
import br.com.fiap.postech.fastfood.produto.adapter.presenter.ProdutoResponse
import br.com.fiap.postech.fastfood.produto.adapter.presenter.toProduto
import br.com.fiap.postech.fastfood.produto.adapter.presenter.toProdutoResponse
import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("produto")
class ProdutoController (
    private val cadastrarProdutoUseCase: CadastrarProdutoUseCase,
    private val atualizarProdutoUseCase: AtualizarProdutoUseCase,
    private val removerProdutoUseCase: RemoverProdutoUseCase,
    private val buscarProdutoPorCategoriaUseCase: BuscarProdutoPorCategoriaUseCase,
    private val buscarProdutoPorIdUseCase: BuscarProdutoPorIdUseCase
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrarProdutoCleanArch(@RequestBody request: ProdutoRequest): ProdutoResponse {
        return cadastrarProdutoUseCase.executa(request.toProduto()).toProdutoResponse()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: UUID, @RequestBody request: ProdutoRequest) {
        atualizarProdutoUseCase.executa(id, request.toProduto())
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) {
        removerProdutoUseCase.executa(id)
    }

    @GetMapping("/categoria")
    fun buscarPorCategoria(@RequestParam nome: String): List<ProdutoResponse>? {
        return buscarProdutoPorCategoriaUseCase.executa(nome).map { it.toProdutoResponse() }
    }

    @GetMapping("/{id}")
    fun buscarPorPorId(@PathVariable id: UUID): ProdutoResponse? {
        return buscarProdutoPorIdUseCase.executa(id).toProdutoResponse()
    }
}