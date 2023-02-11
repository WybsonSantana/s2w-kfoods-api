package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class CozinhaRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : CozinhaRepository {

    override fun listar(): List<Cozinha> {
        return manager.createQuery("from Cozinha", Cozinha::class.java).resultList
    }

    override fun consultarPorNome(nome: String): List<Cozinha> {
        return manager.createQuery("from Cozinha where nome like :nome", Cozinha::class.java)
            .setParameter("nome", "%$nome%")
            .resultList
    }

    override fun buscar(cozinhaId: Long): Cozinha? {
        return manager.find(Cozinha::class.java, cozinhaId)
    }

    @Transactional
    override fun salvar(cozinha: Cozinha): Cozinha {
        return manager.merge(cozinha)
    }

    @Transactional
    override fun remover(cozinhaId: Long) {
        val cozinha = buscar(cozinhaId) ?: throw EmptyResultDataAccessException(1)
        manager.remove(cozinha)
    }

}