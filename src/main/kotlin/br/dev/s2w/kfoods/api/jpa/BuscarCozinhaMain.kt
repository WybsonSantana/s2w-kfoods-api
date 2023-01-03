package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.infrastructure.repository.CozinhaRepositoryImpl
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val cozinhaRepository: CozinhaRepositoryImpl = applicationContext.getBean(CozinhaRepositoryImpl::class.java)

    val cozinha: Cozinha = cozinhaRepository.buscar(1L)

    println(cozinha.nome)

}