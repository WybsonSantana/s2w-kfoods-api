package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeEmUsoException
import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroCozinhaService
import br.dev.s2w.kfoods.api.gateway.model.CozinhasXmlWrapper
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cozinhas")
class CozinhaController(
    private val cozinhaRepository: CozinhaRepository,
    private val cadastroCozinha: CadastroCozinhaService
) {

    @GetMapping
    fun listarJson(): List<Cozinha> {
        return cozinhaRepository.listar()
    }

    @GetMapping(produces = [MediaType.APPLICATION_XML_VALUE])
    fun listarXml(): CozinhasXmlWrapper {
        return CozinhasXmlWrapper(cozinhaRepository.listar())
    }

    @GetMapping("/{cozinhaId}")
    fun buscar(@PathVariable cozinhaId: Long): ResponseEntity<Cozinha> {
        val cozinha: Cozinha? = cozinhaRepository.buscar(cozinhaId)

        return if (cozinha != null) {
            ResponseEntity.status(HttpStatus.OK).body(cozinha)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun adicionar(@RequestBody cozinha: Cozinha): Cozinha {
        return cadastroCozinha.salvar(cozinha)
    }

    @PutMapping("/{cozinhaId}")
    fun atualizar(@PathVariable cozinhaId: Long, @RequestBody cozinha: Cozinha): ResponseEntity<Cozinha> {
        val cozinhaAtual = cozinhaRepository.buscar(cozinhaId)

        return if (cozinhaAtual != null) {
            BeanUtils.copyProperties(cozinha, cozinhaAtual, "id")
            ResponseEntity.ok(cozinhaRepository.salvar(cozinhaAtual))
        } else {
            ResponseEntity.notFound().build()
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