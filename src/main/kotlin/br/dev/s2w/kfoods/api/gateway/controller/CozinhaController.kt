package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroCozinhaService
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cozinhas")
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository,
    private val cadastroCozinha: CadastroCozinhaService
) {

    @GetMapping
    fun listar(): List<Cozinha> {
        return cozinhaRepository.listar()
    }

    @GetMapping("/{cozinhaId}")
    fun buscar(@PathVariable cozinhaId: Long): ResponseEntity<Cozinha> {
        val cozinha = cozinhaRepository.buscar(cozinhaId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(cozinha)
    }

    @PostMapping
    fun adicionar(@RequestBody cozinha: Cozinha): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(cadastroCozinha.salvar(cozinha))
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PutMapping("/{cozinhaId}")
    fun atualizar(@PathVariable cozinhaId: Long, @RequestBody cozinha: Cozinha): ResponseEntity<Any> {
        return try {
            val cozinhaAtual = cozinhaRepository.buscar(cozinhaId) ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id")

            cadastroCozinha.salvar(cozinhaAtual)
            ResponseEntity.ok(cozinhaAtual)
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @DeleteMapping("/{cozinhaId}")
    fun remover(@PathVariable cozinhaId: Long): ResponseEntity<Any> {
        return try {
            cadastroCozinha.excluir(cozinhaId)
            ResponseEntity.noContent().build()
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.notFound().build()
        } catch (ex: EntidadeEmUsoException) {
            ResponseEntity.status(HttpStatus.CONFLICT).build()
        }
    }

}