package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class RestauranteRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : RestauranteRepository {

    override fun listar(): List<Restaurante> {
        return manager.createQuery("from Restaurante", Restaurante::class.java).resultList
    }

    override fun buscar(id: Long): Restaurante? {
        return manager.find(Restaurante::class.java, id)
    }

    @Transactional
    override fun salvar(restaurante: Restaurante): Restaurante {
        return manager.merge(restaurante)
    }

    @Transactional
    override fun remover(restaurante: Restaurante) {
        manager.remove(buscar(restaurante.id))
    }

}