package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.stereotype.Service

@Service
class CadastroCozinhaService(
    private val cozinhaRepository: CozinhaRepository
) {

    fun salvar(cozinha: Cozinha): Cozinha {
        return cozinhaRepository.salvar(cozinha)
    }
}