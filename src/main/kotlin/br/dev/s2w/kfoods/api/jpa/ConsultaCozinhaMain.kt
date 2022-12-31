package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val cadastroCozinha: CadastroCozinha = applicationContext.getBean(CadastroCozinha::class.java)

    val cozinhas: List<Cozinha> = cadastroCozinha.listar()

    for (cozinha in cozinhas) {
        println(cozinha.nome)
    }


}