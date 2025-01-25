package br.dev.s2w.kfoods.api.domain.model

import br.dev.s2w.kfoods.api.Groups
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
data class City(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @field:NotBlank(groups = [Groups.CityRegistration::class])
    @Column(nullable = false)
    var name: String? = null,

    @field:Valid
    //@field:ConvertGroup(from = Default::class, to = Groups.StateId::class)
    //The @ConvertGroup annotation has a bug in Kotlin that doesn't annotate the right attribute for validation
    //https://stackoverflow.com/questions/79376953/javax-validations-convertgroup-annotation-does-not-work-in-a-spring-project-us
    @field:NotNull(groups = [Groups.CityRegistration::class])
    @ManyToOne
    @JoinColumn(nullable = false)
    var state: State? = null
)