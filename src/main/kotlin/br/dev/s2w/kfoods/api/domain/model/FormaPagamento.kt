package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.*

@Entity
data class FormaPagamento(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    val descricao: String
)