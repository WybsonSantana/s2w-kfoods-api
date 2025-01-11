package br.dev.s2w.kfoods.api.adapter.exceptionhandler

enum class ProblemType(
    private val path: String,
    val title: String
) {

    BUSINESS_ERROR("/business-error", "Business rule violation"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable");


    val uri: String
        get() = "https://jfoods.com.br$path"

}
