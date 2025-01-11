package br.dev.s2w.kfoods.api.adapter.exceptionhandler

enum class ProblemType(
    private val path: String,
    val title: String
) {

    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found");

    val uri: String
        get() = "https://jfoods.com.br$path"

}
