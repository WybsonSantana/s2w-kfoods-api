package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class Cidade(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var nome: String = "",

    @ManyToOne
    @JoinColumn(nullable = false)
    var estado: Estado
)