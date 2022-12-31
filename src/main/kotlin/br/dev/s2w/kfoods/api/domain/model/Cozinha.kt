package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class Cozinha(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val nome: String? = null
)