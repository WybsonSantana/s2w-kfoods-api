package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroRestauranteService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurantes")
class RestauranteController(
    private val restauranteRepository: RestauranteRepository,
    private val cadastroRestaurante: CadastroRestauranteService
) {

    @GetMapping
    fun listar(): List<Restaurante> {
        return restauranteRepository.listar()
    }

    @GetMapping("/{restauranteId}")
    fun buscar(@PathVariable restauranteId: Long): ResponseEntity<Restaurante> {
        val restaurante = restauranteRepository.buscar(restauranteId) ?: return ResponseEntity.notFound().build()

        return ResponseEntity.ok(restaurante)
    }

    @PostMapping
    fun adicionar(@RequestBody restaurante: Restaurante): ResponseEntity<Any> {
        return try {
            ResponseEntity.status(HttpStatus.CREATED).body(cadastroRestaurante.salvar(restaurante))
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

}