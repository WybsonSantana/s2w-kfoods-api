package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.gateway.model.CozinhasXmlWrapper
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cozinhas")
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository
) {

    @GetMapping
    fun listarJson(): List<Cozinha> {
        return cozinhaRepository.listar()
    }

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun listarXml(): CozinhasXmlWrapper {
        return CozinhasXmlWrapper(cozinhaRepository.listar())
    }

    @GetMapping("/{cozinhaId}")
    fun buscar(@PathVariable cozinhaId: Long): ResponseEntity<Cozinha> {
        val cozinha: Cozinha? = cozinhaRepository.buscar(cozinhaId)

        return if (cozinha != null) {
            ResponseEntity.status(HttpStatus.OK).body(cozinha)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun adicionar(@RequestBody cozinha: Cozinha): Cozinha {
        return cozinhaRepository.salvar(cozinha)
    }

}