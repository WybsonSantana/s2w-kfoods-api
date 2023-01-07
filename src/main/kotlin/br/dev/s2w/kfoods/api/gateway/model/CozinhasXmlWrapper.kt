package br.dev.s2w.kfoods.api.gateway.model

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty


/*
 * @JacksonXmlRootElement é uma alternativa à @JsonRootName e
 * @JacksonXmlProperty à @JsonProperty.
 *
 * A diferença é que as anotações iniciadas com @JacksonXml só afetam
 * a serialização em XML. Já as anotações iniciadas com @Json
 * afetam tanto a serialização JSON como também XML.
 */

@JsonRootName("cozinhas")
//@JacksonXmlRootElement(localName = "cozinhas")
data class CozinhasXmlWrapper(
    @JsonProperty("cozinha")
    @JacksonXmlProperty(localName = "cozinha")
    @JacksonXmlElementWrapper(useWrapping = false)
    val cozinhas: List<Cozinha>
)