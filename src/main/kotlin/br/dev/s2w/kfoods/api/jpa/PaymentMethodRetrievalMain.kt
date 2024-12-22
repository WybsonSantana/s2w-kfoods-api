package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.repository.PaymentMethodRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder

fun main(args: Array<String>) {
    val applicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository::class.java)
    val paymentMethods = paymentMethodRepository.list()

    for (paymentMethod in paymentMethods)
        println(paymentMethod.description)
}