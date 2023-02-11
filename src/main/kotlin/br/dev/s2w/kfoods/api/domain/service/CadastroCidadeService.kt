package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cidade
import br.dev.s2w.kfoods.api.domain.repository.CidadeRepository
import br.dev.s2w.kfoods.api.domain.repository.EstadoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroCidadeService(
    private val cidadeRepository: CidadeRepository,
    private val estadoRepository: EstadoRepository
) {

    fun salvar(cidade: Cidade): Cidade {
        val estadoId = cidade.estado.id

        val estado = estadoRepository.findById(estadoId)
            .orElseThrow { EntidadeNaoEncontradaException("O cadastro de estado com ID $estadoId não foi encontrado") }

        val cidadeAtualizada = cidade.copy(estado = estado)

        return cidadeRepository.save(cidadeAtualizada)
    }

    fun excluir(cidadeId: Long) {
        try {
            cidadeRepository.deleteById(cidadeId)
        } catch (ex: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("O cadastro de cidade com ID $cidadeId não foi encontrado")
        } catch (ex: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("A cidade com ID $cidadeId não pode ser removida porque está em uso")
        }
    }
}