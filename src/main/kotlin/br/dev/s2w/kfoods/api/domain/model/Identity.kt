package br.dev.s2w.kfoods.api.domain.model

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Identity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @Column(nullable = false)
    var email: String? = null,

    @Column(nullable = false)
    var password: String? = null,

    @CreationTimestamp
    @Column(nullable = false)
    var registrationDate: LocalDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "identity_role",
        joinColumns = [JoinColumn(name = "identity_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    var roles: MutableList<Role> = mutableListOf()
)