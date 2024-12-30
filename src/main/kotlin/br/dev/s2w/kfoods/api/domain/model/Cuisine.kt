package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Cuisine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String? = null,

    @JsonIgnore
    @OneToMany
    var restaurants: MutableList<Restaurant> = mutableListOf()
)