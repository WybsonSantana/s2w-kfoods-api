package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Permissao
import br.dev.s2w.kfoods.api.domain.repository.PermissaoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class PermissaoRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : PermissaoRepository {

    override fun listar(): List<Permissao> {
        return manager.createQuery("from Permissao", Permissao::class.java).resultList
    }

    override fun buscar(permissaoId: Long): Permissao? {
        return manager.find(Permissao::class.java, permissaoId)
    }

    @Transactional
    override fun salvar(permissao: Permissao): Permissao {
        return manager.merge(permissao)
    }

    @Transactional
    override fun remover(permissao: Permissao) {
        manager.remove(buscar(permissao.id))
    }

}