package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/cozinhas")
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository
) {

    @GetMapping
    fun listar(): List<Cozinha> {
        return cozinhaRepository.listar()
    }
}