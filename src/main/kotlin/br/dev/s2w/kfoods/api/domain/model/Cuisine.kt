package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.core.validation.Groups
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class Cuisine(
    @field:NotNull(groups = [Groups.RestaurantRegistration::class])
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank
    @Column(nullable = false)
    var name: String? = null,

    @OneToMany(mappedBy = "cuisine")
    @JsonIgnore
    var restaurants: MutableList<Restaurant> = mutableListOf()
)