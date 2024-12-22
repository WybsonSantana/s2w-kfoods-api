package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Permission

interface PermissionRepository {
    fun list(): List<Permission>
    fun search(id: Long): Permission
    fun save(permission: Permission): Permission
    fun remove(permission: Permission)
}