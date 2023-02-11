package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.stereotype.Service

@Service
class CadastroRestauranteService(
    private val restauranteRepository: RestauranteRepository,
    private val cozinhaRepository: CozinhaRepository
) {

    fun salvar(restaurante: Restaurante): Restaurante {
        val cozinhaId = restaurante.cozinha.id

        val cozinha = cozinhaRepository.findById(cozinhaId)
            .orElseThrow { EntidadeNaoEncontradaException("O cadastro de cozinha com ID $cozinhaId não foi encontrado") }

        val restauranteAtualizado = restaurante.copy(cozinha = cozinha)

        return restauranteRepository.save(restauranteAtualizado)
    }
}