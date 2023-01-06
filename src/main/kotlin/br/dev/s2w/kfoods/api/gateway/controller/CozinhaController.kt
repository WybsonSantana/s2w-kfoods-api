package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cozinhas") /* , [produces = MediaType.APPLICATION_XML_VALUE]) */
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository
) {

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun listarJson(): List<Cozinha> {
        println("listarJson()")
        return cozinhaRepository.listar()
    }

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun listarXml(): List<Cozinha> {
        println("listarXml()")
        return cozinhaRepository.listar()
    }
}