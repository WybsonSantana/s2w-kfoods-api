package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @ManyToMany
    @JoinTable(
        name = "role_permission",
        joinColumns = [JoinColumn(name = "role_id")],
        inverseJoinColumns = [JoinColumn(name = "permission_id")]
    )
    var permission: MutableList<Permission> = mutableListOf()
)