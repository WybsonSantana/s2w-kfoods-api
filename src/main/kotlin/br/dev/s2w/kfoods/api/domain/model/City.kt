package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @ManyToOne
    @JoinColumn(nullable = false)
    var state: State? = null
)