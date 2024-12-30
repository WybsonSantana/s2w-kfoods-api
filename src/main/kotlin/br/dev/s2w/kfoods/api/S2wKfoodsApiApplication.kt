package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.domain.repository.CustomJpaRepository
import br.dev.s2w.kfoods.api.infrastructure.repository.CustomJpaRepositoryImpl
import br.dev.s2w.kfoods.api.infrastructure.repository.RestaurantRepositoryImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl::class)
class S2wKfoodsApiApplication

fun main(args: Array<String>) {
    runApplication<S2wKfoodsApiApplication>(*args)
}
