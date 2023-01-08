package br.dev.s2w.kfoods.api.domain.exception

class EntidadeEmUsoException(
    message: String?
) : RuntimeException(message) {

    companion object {
        private const val serialVersionUID = 1L
    }
}
