package br.dev.s2w.kfoods.api.di.modelo

data class Cliente(
    val nome: String,
    val email: String,
    val telefone: String
) {

    private var ativo = false

    fun isAtivo() = ativo

    fun ativar() {
        ativo = true
    }
}
