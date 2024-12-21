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
    val cuisines: List<Cuisine> = cuisineRegistration.list()

    for (cuisine in cuisines)
        println(cuisine.name)
}