package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.FormaPagamento
import br.dev.s2w.kfoods.api.domain.repository.FormaPagamentoRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class FormaPagamentoRepositoryImpl(
    @PersistenceContext private val manager: EntityManager
) : FormaPagamentoRepository {

    override fun listar(): List<FormaPagamento> {
        return manager.createQuery("from FormaPagamento", FormaPagamento::class.java).resultList
    }

    override fun buscar(id: Long): FormaPagamento {
        return manager.find(FormaPagamento::class.java, id)
    }

    @Transactional
    override fun salvar(formaPagamento: FormaPagamento): FormaPagamento {
        return manager.merge(formaPagamento)
    }

    @Transactional
    override fun remover(formaPagamento: FormaPagamento) {
        manager.remove(buscar(formaPagamento.id))
    }

}