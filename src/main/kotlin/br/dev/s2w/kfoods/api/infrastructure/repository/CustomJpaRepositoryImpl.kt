package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.repository.CustomJpaRepository
import org.springframework.data.jpa.repository.support.JpaEntityInformation
import org.springframework.data.jpa.repository.support.SimpleJpaRepository
import javax.persistence.EntityManager

class CustomJpaRepositoryImpl<T, ID>(
    private val entityInformation: JpaEntityInformation<T, Any>,
    private val manager: EntityManager,
) : SimpleJpaRepository<T, ID>(entityInformation, manager), CustomJpaRepository<T, ID> {

    override fun findFirstItem(): T? {
        val jpql = "from ${super.getDomainClass().name}"

        val entity: T = manager.createQuery(jpql, super.getDomainClass())
            .setMaxResults(1)
            .singleResult

        return entity
    }

}
