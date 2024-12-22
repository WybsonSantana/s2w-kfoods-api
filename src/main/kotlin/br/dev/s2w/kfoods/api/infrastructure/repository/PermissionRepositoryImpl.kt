package br.dev.s2w.kfoods.api.infrastructure.repository

import br.dev.s2w.kfoods.api.domain.model.Permission
import br.dev.s2w.kfoods.api.domain.repository.PermissionRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Component
class PermissionRepositoryImpl : PermissionRepository {

    @PersistenceContext
    private lateinit var manager: EntityManager

    override fun list(): List<Permission> = manager
        .createQuery("from Permission", Permission::class.java)
        .resultList

    override fun search(id: Long): Permission =
        manager.find(Permission::class.java, id)

    @Transactional
    override fun save(permission: Permission): Permission =
        manager.merge(permission)

    @Transactional
    override fun remove(permission: Permission) =
        manager.remove(search(permission.id!!))

}