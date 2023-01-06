package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Cidade
import br.dev.s2w.kfoods.api.domain.repository.CidadeRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CidadeRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : CidadeRepository {

    override fun listar(): List<Cidade> {
        return manager.createQuery("from Cidade", Cidade::class.java).resultList
    }

    override fun buscar(id: Long): Cidade {
        return manager.find(Cidade::class.java, id)
    }

    @Transactional
    override fun salvar(cidade: Cidade): Cidade {
        return manager.merge(cidade)
    }

    @Transactional
    override fun remover(cidade: Cidade) {
        manager.remove(buscar(cidade.id))
    }

}