package br.dev.s2w.kfoods.api.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tab_cuisines")
data class Cuisine(
    @Id
    var id: Long,

    @Column(name = "cuisine_name")
    var name: String
)