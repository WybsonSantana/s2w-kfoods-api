package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tab_cozinhas")
data class Cozinha(
    @Id
    private val id: Long,

    @Column(name = "nom_cozinha")
    private val nome: String
)