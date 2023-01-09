package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/restaurantes")
class RestauranteController(
    private val restauranteRepository: RestauranteRepository
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
}