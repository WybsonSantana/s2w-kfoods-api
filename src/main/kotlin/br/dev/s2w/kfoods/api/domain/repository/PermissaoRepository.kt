package br.dev.s2w.kfoods.api.domain.repository

import br.dev.s2w.kfoods.api.domain.model.Permissao

interface PermissaoRepository {
    fun listar(): List<Permissao>
    fun buscar(id: Long): Permissao
    fun salvar(permissao: Permissao): Permissao
    fun remover(permissao: Permissao)

}