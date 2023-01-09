package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroCozinhaService
import org.springframework.beans.BeanUtils
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.PersistenceException

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
    fun adicionar(@RequestBody cozinha: Cozinha): ResponseEntity<Cozinha> {
        try {
            if (cozinha.nome != "") {
                return ResponseEntity.status(HttpStatus.CREATED).body(cadastroCozinha.salvar(cozinha))
            }

            return ResponseEntity.badRequest().build()
        } catch (ex: PersistenceException) {
            return ResponseEntity.badRequest().build()
        }
    }

    @PutMapping("/{cozinhaId}")
    fun atualizar(@PathVariable cozinhaId: Long, @RequestBody cozinha: Cozinha): ResponseEntity<Cozinha> {
        return try {
            val cozinhaAtual = cozinhaRepository.buscar(cozinhaId) ?: return ResponseEntity.notFound().build()

            if (cozinha.nome != "") {
                BeanUtils.copyProperties(cozinha, cozinhaAtual, "id")

                return ResponseEntity.ok(cadastroCozinha.salvar(cozinhaAtual))
            }

            ResponseEntity.badRequest().build()
        } catch (ex: DataIntegrityViolationException) {
            ResponseEntity.badRequest().build()
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