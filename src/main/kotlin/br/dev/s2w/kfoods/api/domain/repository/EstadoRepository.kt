package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Estado

interface EstadoRepository {
    fun listar(): List<Estado>
    fun buscar(estadoId: Long): Estado?
    fun salvar(estado: Estado): Estado
    fun remover(estadoId: Long)

}