package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/teste")
class TesteController(
    private val cozinhaRepository: CozinhaRepository
) {

//    @GetMapping("/cozinhas/por-nome")
//    fun cozinhasPorNome(@RequestParam nome: String): List<Cozinha> {
//        return cozinhaRepository.consultarPorNome(nome)
//    }
}