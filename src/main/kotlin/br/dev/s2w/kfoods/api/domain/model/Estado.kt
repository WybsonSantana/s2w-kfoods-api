package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class Estado(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val nome: String
)