package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Cozinha
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

    val restaurante = Restaurante(
        id = 1L,
        nome = "Atomic Foods",
        taxaFrete = BigDecimal.valueOf(17),
        Cozinha(
            id = 1L,
            nome = "Tailandesa"
        )
    )

    restauranteRepository.salvar(restaurante)

}