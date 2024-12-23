package br.dev.s2w.kfoods.api.adapter.model

import br.dev.s2w.kfoods.api.domain.model.Cuisine
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper

@JsonRootName("cuisines")
//@JacksonXmlRootElement(localName = "cuisines")
data class CuisinesXmlWrapper(
    @JsonProperty("cuisine")
    //@JacksonXmlProperty(localName = "cuisine")
    @JacksonXmlElementWrapper(useWrapping = false)
    val cuisines: List<Cuisine>
)