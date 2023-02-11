package br.dev.s2w.kfoods.api.gateway.controller

import br.dev.s2w.kfoods.api.domain.exception.EntidadeNaoEncontradaException
import br.dev.s2w.kfoods.api.domain.model.Restaurante
import br.dev.s2w.kfoods.api.domain.repository.RestauranteRepository
import br.dev.s2w.kfoods.api.domain.service.CadastroRestauranteService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.BeanUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.ReflectionUtils
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/restaurantes")
class RestauranteController(
    private val restauranteRepository: RestauranteRepository,
    private val cadastroRestaurante: CadastroRestauranteService
) {

    @GetMapping
    fun listar(): List<Restaurante> {
        return restauranteRepository.findAll()
    }

    @GetMapping("/{restauranteId}")
    fun buscar(@PathVariable restauranteId: Long): ResponseEntity<Restaurante> {
        val restaurante = restauranteRepository.findById(restauranteId).orElse(null)
            ?: return ResponseEntity.notFound().build()

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

    @PutMapping("/{restauranteId}")
    fun atualizar(@PathVariable restauranteId: Long, @RequestBody restaurante: Restaurante): ResponseEntity<Any> {
        return try {
            val restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null)
                ?: return ResponseEntity.notFound().build()

            BeanUtils.copyProperties(restaurante, restauranteAtual, "id")

            cadastroRestaurante.salvar(restauranteAtual)
            ResponseEntity.ok(restauranteAtual)
        } catch (ex: EntidadeNaoEncontradaException) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PatchMapping("/{restauranteId}")
    fun atualizarParcial(
        @PathVariable restauranteId: Long,
        @RequestBody campos: Map<String?, Any?>?
    ): ResponseEntity<Any>? {
        val restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null)
            ?: return ResponseEntity.notFound().build()

        merge(campos, restauranteAtual)

        return atualizar(restauranteId, restauranteAtual)
    }

    private fun merge(dadosOrigem: Map<String?, Any?>?, restauranteDestino: Restaurante?) {
        val objectMapper = ObjectMapper()
        val restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante::class.java)

        dadosOrigem?.forEach { (nomePropriedade: String?, valorPropriedade: Any?) ->
            val field = ReflectionUtils.findField(Restaurante::class.java, nomePropriedade!!)
            field!!.isAccessible = true

            val novoValor = ReflectionUtils.getField(field, restauranteOrigem)

            println("nome propriedade: $nomePropriedade \n valor propriedade: $valorPropriedade \n novo valor: $novoValor")

            ReflectionUtils.setField(field, restauranteDestino, novoValor)
        }
    }


}