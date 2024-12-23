package br.dev.s2w.kfoods.api.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@JsonRootName("gastronomy")
@Entity
data class Cuisine(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    //@JsonIgnore
    @JsonProperty("title")
    @Column(nullable = false)
    var name: String? = null
)