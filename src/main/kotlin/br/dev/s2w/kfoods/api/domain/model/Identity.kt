package br.dev.s2w.kfoods.api.domain.model

import org.hibernate.annotations.CreationTimestamp
import java.time.OffsetDateTime
import javax.persistence.*

@Entity
data class Identity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val name: String? = null,

    @Column(nullable = false)
    val email: String? = null,

    @Column(nullable = false)
    val password: String? = null,

    @CreationTimestamp
    @Column(nullable = false)
    val registrationDate: OffsetDateTime? = null,

    @ManyToMany
    @JoinTable(
        name = "identity_role",
        joinColumns = [JoinColumn(name = "identity_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val roles: MutableList<Role> = mutableListOf()
)