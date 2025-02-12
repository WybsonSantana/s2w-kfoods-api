package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.infrastructure.repository.CustomJpaRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.util.*

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl::class)
class S2wKFoodsApiApplication

fun main(args: Array<String>) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    runApplication<S2wKFoodsApiApplication>(*args)
}
