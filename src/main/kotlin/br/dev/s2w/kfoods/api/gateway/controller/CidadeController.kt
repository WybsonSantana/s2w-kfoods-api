package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cidade
import br.dev.s2w.kfoods.api.domain.repository.CidadeRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroCidadeService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cidades")
class CidadeController(
    private val cidadeRepository: CidadeRepository,
    private val cadastroCidade: CadastroCidadeService
) {

    @GetMapping
    fun listar(): List<Cidade> {
        return cidadeRepository.findAll()
    }

    @GetMapping("/{cidadeId}")
    fun buscar(@PathVariable cidadeId: Long): ResponseEntity<Cidade> {
        val cidade = cidadeRepository.findById(cidadeId).orElse(null)
            ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(cidade)
    }

    @PostMapping
    fun adicionar(@RequestBody cidade: Cidade): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(cadastroCidade.salvar(cidade))
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PutMapping("/{cidadeId}")
    fun atualizar(@PathVariable cidadeId: Long, @RequestBody cidade: Cidade): ResponseEntity<Any> {
        return try {
            val cidadeAtual = cidadeRepository.findById(cidadeId).orElse(null)
                ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(cidade, cidadeAtual, "id")

            cadastroCidade.salvar(cidadeAtual)
            ResponseEntity.ok(cidadeAtual)
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping("/{cidadeId}")
    fun remover(@PathVariable cidadeId: Long): ResponseEntity<Any> {
        return try {
            cadastroCidade.excluir(cidadeId)
            ResponseEntity.noContent().build()
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.notFound().build()
        } catch (ex: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
        }
    }

}