package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroCozinhaService(
    private val cozinhaRepository: CozinhaRepository
) {

    fun salvar(cozinha: Cozinha): Cozinha {
        return cozinhaRepository.salvar(cozinha)
    }

    fun excluir(cozinhaId: Long) {
        try {
            cozinhaRepository.remover(cozinhaId)
        } catch (ex: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("O cadastro de cozinha com ID $cozinhaId não foi encontrado")
        } catch (ex: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("A cozinha com ID $cozinhaId não pode ser removida porque está em uso")
        }
    }

}