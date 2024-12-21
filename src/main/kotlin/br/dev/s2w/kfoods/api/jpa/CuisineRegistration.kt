package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class CuisineRegistration {

    @PersistenceContext
    private lateinit var manager: EntityManager

    fun list(): List<Cuisine> = manager
        .createQuery("from Cuisine", Cuisine::class.java)
        .resultList

}
