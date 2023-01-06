package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Cidade
import br.dev.s2w.kfoods.api.domain.repository.CidadeRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val cidadeRepository: CidadeRepository = applicationContext.getBean(CidadeRepository::class.java)

    val cidades: List<Cidade> = cidadeRepository.listar()

    for (cidade in cidades) {
        println(cidade.nome)
    }


}