package br.dev.s2w.kfoods.api.domain.service

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Estado
import br.dev.s2w.kfoods.api.domain.repository.EstadoRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class CadastroEstadoService(
    private val estadoRepository: EstadoRepository
) {

    fun salvar(estado: Estado) {
        estadoRepository.save(estado)
    }

    fun excluir(estadoId: Long) {
        try {
            estadoRepository.deleteById(estadoId)
        } catch (ex: EmptyResultDataAccessException) {
            throw EntidadeNaoEncontradaException("O cadastro de estado com ID $estadoId não foi encontrado")
        } catch (ex: DataIntegrityViolationException) {
            throw EntidadeEmUsoException("O estado com ID $estadoId não pode ser removido porque está em uso")
        }
    }
}