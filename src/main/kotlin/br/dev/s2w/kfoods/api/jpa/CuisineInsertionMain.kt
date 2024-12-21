package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder

fun main(args: Array<String>) {
    val applicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val cuisineRegistration = applicationContext.getBean(CuisineRegistration::class.java)

    var cuisine1 = Cuisine(name = "Brasileira")
    var cuisine2 = Cuisine(name = "Japonesa")

    cuisine1 = cuisineRegistration.add(cuisine1)
    cuisine2 = cuisineRegistration.add(cuisine2)

    println("${cuisine1.id} - ${cuisine1.name}")
    println("${cuisine2.id} - ${cuisine2.name}")
}