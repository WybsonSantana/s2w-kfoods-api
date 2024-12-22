package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.PaymentMethod
import br.dev.s2w.kfoods.api.domain.repository.PaymentMethodRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class PaymentMethodRepositoryImpl : PaymentMethodRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<PaymentMethod> = manager
        .createQuery("from PaymentMethod", PaymentMethod::class.java)
        .resultList

    override fun search(id: Long): PaymentMethod =
        manager.find(PaymentMethod::class.java, id)

    @Transactional
    override fun save(paymentMethod: PaymentMethod): PaymentMethod =
        manager.merge(paymentMethod)

    @Transactional
    override fun remove(paymentMethod: PaymentMethod) =
        manager.remove(search(paymentMethod.id!!))

}