package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.core.validation.Groups
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:NotBlank(groups = [Groups.CityRegistration::class])
    @Column(nullable = false)
    val name: String? = null,

    @field:Valid
    @field:NotNull(groups = [Groups.CityRegistration::class])
    @ManyToOne
    @JoinColumn(nullable = false)
    val state: State? = null
)