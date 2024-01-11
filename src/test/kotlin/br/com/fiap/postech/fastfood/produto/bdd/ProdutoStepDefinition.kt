package br.com.fiap.postech.fastfood.produto.bdd

import io.cucumber.java.Before
import io.cucumber.java.en.And
import io.cucumber.java.en.Given

class ProdutoStepDefinition {

    private var firstNumber: Int = 0
    private var secondNumber: Int = 0
    private var result: Int = 0

    @Before
    fun setUp() {
        firstNumber = 0
        secondNumber = 0
        result = 0
    }

    @Given("a integer {int}")
    fun `a integer`(number: Int) {
        firstNumber = number
    }

    @And("a second integer {int}")
    fun `a second integer`(number: Int) {
        secondNumber = number
    }
}