package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Restaurante

interface RestauranteRepository {
    fun listar(): List<Restaurante>
    fun buscar(id: Long): Restaurante?
    fun salvar(restaurante: Restaurante): Restaurante
    fun remover(restaurante: Restaurante)

}