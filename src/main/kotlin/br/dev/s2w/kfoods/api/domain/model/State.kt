package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.Groups
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class State(
    //@field:NotNull(groups = [Groups.StateId::class])
    @field:NotNull(groups = [Groups.CityRegistration::class])
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank
    @Column(nullable = false)
    var name: String? = null
)