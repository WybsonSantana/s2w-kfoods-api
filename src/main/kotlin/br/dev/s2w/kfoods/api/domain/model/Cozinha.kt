package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class Cozinha(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    private val nome: String
)