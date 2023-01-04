package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext
import java.math.BigDecimal

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val restauranteRepository: RestauranteRepository = applicationContext.getBean(RestauranteRepository::class.java)

    val restaurante1 = Restaurante(nome = "Atomic Foods", taxaFrete = BigDecimal.valueOf(17))
    val restaurante2 = Restaurante(nome = "KotlinNuts", taxaFrete = BigDecimal.valueOf(11))

    restauranteRepository.salvar(restaurante1)
    restauranteRepository.salvar(restaurante2)

    println("${restaurante1.id} - ${restaurante1.nome}")
    println("${restaurante2.id} - ${restaurante2.nome}")

}