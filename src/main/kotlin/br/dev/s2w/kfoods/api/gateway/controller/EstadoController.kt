package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Estado
import br.dev.s2w.kfoods.api.domain.repository.EstadoRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroEstadoService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/estados")
class EstadoController(
    private val estadoRepository: EstadoRepository,
    private val cadastroEstado: CadastroEstadoService
) {

    @GetMapping
    fun listar(): List<Estado> {
        return estadoRepository.listar()
    }

    @GetMapping("/{estadoId}")
    fun buscar(@PathVariable estadoId: Long): ResponseEntity<Estado> {
        val estado = estadoRepository.buscar(estadoId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(estado)
    }

    @PostMapping
    fun adicionar(@RequestBody estado: Estado): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(cadastroEstado.salvar(estado))
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PutMapping("/{estadoId}")
    fun atualizar(@PathVariable estadoId: Long, @RequestBody estado: Estado): ResponseEntity<Any> {
        return try {
            val estadoAtual = estadoRepository.buscar(estadoId) ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(estado, estadoAtual, "id")

            cadastroEstado.salvar(estadoAtual)
            ResponseEntity.ok(estadoAtual)
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping("/{estadoId}")
    fun remover(@PathVariable estadoId: Long): ResponseEntity<Any> {
        return try {
            cadastroEstado.excluir(estadoId)
            ResponseEntity.noContent().build()
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.notFound().build()
        } catch (ex: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        }
    }


}