package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonRootName
import javax.persistence.*

@JsonRootName("cozinha")
@Entity
data class Cozinha(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(nullable = false)
    var nome: String = ""
)