package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val restauranteRepository: RestauranteRepository = applicationContext.getBean(RestauranteRepository::class.java)

    val restaurantes: List<Restaurante> = restauranteRepository.listar()

    for (restaurante in restaurantes) {
    println("${restaurante.nome} - ${restaurante.taxaFrete} - ${restaurante.cozinha.nome}")
    }


}