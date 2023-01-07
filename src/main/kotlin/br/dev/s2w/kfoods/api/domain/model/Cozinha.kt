package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import javax.persistence.*

@JsonRootName("Gastronomia")
@Entity
data class Cozinha(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    //@JsonIgnore
    @JsonProperty("titulo")
    @Column(nullable = false)
    val nome: String
)