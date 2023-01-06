package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Estado
import br.dev.s2w.kfoods.api.domain.repository.EstadoRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val estadoRepository: EstadoRepository = applicationContext.getBean(EstadoRepository::class.java)

    val estados: List<Estado> = estadoRepository.listar()

    for (estado in estados) {
        println(estado.nome)
    }


}