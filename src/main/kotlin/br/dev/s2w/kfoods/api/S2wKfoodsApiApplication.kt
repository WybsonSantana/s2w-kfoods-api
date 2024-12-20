package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.di.notification.NotifierProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(NotifierProperties::class)
class S2wKfoodsApiApplication

fun main(args: Array<String>) {
    runApplication<S2wKfoodsApiApplication>(*args)
}
