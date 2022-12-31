package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CadastroCozinha(
    @PersistenceContext private val manager: EntityManager
) {

    fun listar(): List<Cozinha> {
        return manager.createQuery("from Cozinha", Cozinha::class.java).resultList
    }

    @Transactional
    fun salvar(cozinha: Cozinha): Cozinha {
        return manager.merge(cozinha)
    }

    fun buscar(id: Long): Cozinha {
        return manager.find(Cozinha::class.java, id)
    }

}