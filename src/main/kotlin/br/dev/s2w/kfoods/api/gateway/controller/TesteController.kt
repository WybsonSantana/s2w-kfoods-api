package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.model.Cozinha
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.CozinhaRepository
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/teste")
class TesteController(
    private val cozinhaRepository: CozinhaRepository,
    private val restauranteRepository: RestauranteRepository
) {

    @GetMapping("/cozinhas/por-nome")
    fun cozinhasPorNome(@RequestParam nome: String): List<Cozinha> {
        return cozinhaRepository.findTodasByNomeContaining(nome)
    }

    @GetMapping("/cozinhas/unica-por-nome")
    fun cozinhaPorNome(@RequestParam nome: String): Cozinha? {
        return cozinhaRepository.findByNome(nome)
    }

    @GetMapping("/restaurantes/por-taxa-frete")
    fun restaurantePorTaxaFrete(
        @RequestParam taxaInicial: BigDecimal,
        @RequestParam taxaFinal: BigDecimal
    ): List<Restaurante> {
        return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal)
    }

    @GetMapping("/restaurantes/por-nome")
    fun restaurantesPorNome(@RequestParam nome: String, @RequestParam cozinhaId: Long): List<Restaurante> {
        return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId)
    }

    @GetMapping("/restaurantes/primeiro-por-nome")
    fun restaurantePrimeiroPorNome(@RequestParam nome: String): Restaurante? {
        return restauranteRepository.findFirstRestauranteByNomeContaining(nome)
    }

    @GetMapping("/restaurantes/top2-por-nome")
    fun restaurantesTop2PorNome(@RequestParam nome: String): List<Restaurante> {
        return restauranteRepository.findTop2ByNomeContaining(nome)
    }

    @GetMapping("/restaurantes/count-por-cozinha")
    fun restaurantesCountPorCozinha(@RequestParam cozinhaId: Long): Int {
        return restauranteRepository.countByCozinhaId(cozinhaId)
    }
}