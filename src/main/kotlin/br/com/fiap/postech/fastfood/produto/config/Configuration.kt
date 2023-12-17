package br.com.fiap.postech.fastfood.produto.config

import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.AtualizarProdutoUseCase
import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.BuscarProdutoPorCategoriaUseCase
import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.CadastrarProdutoUseCase
import br.com.fiap.postech.fastfood.produto.domain.usecase.produto.RemoverProdutoUseCase
import br.com.fiap.postech.fastfood.produto.adapter.gateway.ProdutoRepositoryImpl
import br.com.fiap.postech.fastfood.produto.adapter.gateway.jpa.ProdutoRepositoryJpa
import br.com.fiap.postech.fastfood.produto.domain.repository.ProdutoRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Configuration {

    @Bean
    fun produtoRepository(produtoRepositoryJpa: ProdutoRepositoryJpa): ProdutoRepository {
        return ProdutoRepositoryImpl(produtoRepositoryJpa)
    }

    @Bean
    fun cadastrarProdutoUseCase(produtoRepository: ProdutoRepository): CadastrarProdutoUseCase {
        return CadastrarProdutoUseCase(produtoRepository)
    }

    @Bean
    fun atualizarProdutoUseCase(produtoRepository: ProdutoRepository): AtualizarProdutoUseCase {
        return AtualizarProdutoUseCase(produtoRepository)
    }

    @Bean
    fun removerProdutoUseCase(produtoRepository: ProdutoRepository): RemoverProdutoUseCase {
        return RemoverProdutoUseCase(produtoRepository)
    }

    @Bean
    fun buscarProdutoPorCategoriaUseCase(produtoRepository: ProdutoRepository): BuscarProdutoPorCategoriaUseCase {
        return BuscarProdutoPorCategoriaUseCase(produtoRepository)
    }
}