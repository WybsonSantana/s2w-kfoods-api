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

    val cozinha1 = Cozinha(nome = "Brasileira")
    val cozinha2 = Cozinha(nome = "Japonesa")

    cadastroCozinha.adicionar(cozinha1)
    cadastroCozinha.adicionar(cozinha2)

    println("${cozinha1.id} - ${cozinha1.nome}")
    println("${cozinha2.id} - ${cozinha2.nome}")

}