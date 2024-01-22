package br.com.fiap.postech.fastfood.produto.bdd

import br.com.fiap.postech.fastfood.produto.adapter.presenter.ProdutoRequest
import br.com.fiap.postech.fastfood.produto.adapter.presenter.ProdutoResponse
import io.cucumber.java.Before
import io.cucumber.java.pt.Dado
import io.cucumber.java.pt.Então
import io.cucumber.java.pt.Quando
import io.restassured.response.Response
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

import io.restassured.RestAssured.given
import java.math.BigDecimal
import java.util.*

class ProdutoStepDefinition {

    private var response: Response? = null

    private var produtoResponse: ProdutoResponse? = null

    private val ENDPOINT = "http://localhost:8094/produto"

    lateinit var idUUID: UUID

    @Before
    fun setUp() {
        idUUID = UUID.fromString("259bdc02-1ab5-11ee-be56-0242ac120002")
    }

    @Quando("submeter um novo produto")
    fun `submeter um novo produto`(): ProdutoResponse {
        val produtoRequest: Any = produtoRequest()
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(produtoRequest)
                .`when`().post(ENDPOINT)
        return response!!.then().extract().`as`(ProdutoResponse::class.java)
    }

    @Então("o produto é registrada com sucesso")
    fun `o produto é registrada com sucesso`() {
        response!!.then().statusCode(HttpStatus.CREATED.value())
    }

    @Dado("que produto já foi cadastrado")
    fun `que produto já foi cadastrado`() {
        produtoResponse = `submeter um novo produto`()
    }

    @Quando("requisitar a busca do produto")
    fun `requisitar a busca do produto`() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`()
                .get("$ENDPOINT/{id}", produtoResponse?.id.toString())
    }

    @Então("o produto é exibido com sucesso")
    fun `o produto é exibido com sucesso`() {
        response!!.then().statusCode(HttpStatus.OK.value())
    }

    @Quando("requisitar a busca por categoria")
    fun `requisitar a busca por categoria`() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().get("$ENDPOINT/categoria?nome=lanche")
    }

    @Então("os produtos são exibidos com sucesso")
    fun `os produtos são exibidos com sucesso`() {
        response!!.then().statusCode(HttpStatus.OK.value())
    }

    @Quando("requisitar a alteração do produto")
    fun `requisitar a alteração do produto`() {
        produtoResponse!!.descricao = "batata frita"
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`()
                .body(produtoResponse)
                .put("$ENDPOINT/{id}", produtoResponse?.id.toString())
    }

    @Então("o produto é atualizado com sucesso")
    fun `o produto é atualizado com sucesso`() {
        response!!.then().statusCode(HttpStatus.OK.value())
    }

    @Quando("requisitar a exclusão do produto")
    fun `requisitar a exclusão do produto`() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .`when`().delete("$ENDPOINT/{id}", produtoResponse?.id.toString())
    }

    @Então("o produto é removido com sucesso")
    fun `o produto é removido com sucesso`() {
        response!!.then()
                .statusCode(HttpStatus.OK.value())
    }

    fun produtoRequest(): ProdutoRequest {
        return ProdutoRequest(
                descricao = "X-Bacon",
                categoria = "lanche",
                preco = BigDecimal("45.00")
        )
    }
}