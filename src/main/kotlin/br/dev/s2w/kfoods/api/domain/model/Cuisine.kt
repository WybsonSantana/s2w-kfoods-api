package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Cuisine(
    @field:NotNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "cuisine")
    @JsonIgnore
    var restaurants: MutableList<Restaurant> = mutableListOf()
)