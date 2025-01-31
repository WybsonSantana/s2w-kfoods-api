package br.dev.s2w.kfoods.api.domain.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CustomJpaRepository<T, ID> : JpaRepository<T, ID> {

    fun findFirstItem(): T?

}
