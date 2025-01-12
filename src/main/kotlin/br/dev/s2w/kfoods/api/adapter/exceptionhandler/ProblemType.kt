package br.dev.s2w.kfoods.api.adapter.exceptionhandler

enum class ProblemType(
    private val path: String,
    val title: String
) {

    BUSINESS_ERROR("/business-error", "Business rule violation"),
    ENTITY_IN_USE("/entity-in-use", "Entity in use"),
    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found"),
    MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
    INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
    SYSTEM_ERROR("/system-error", "System error");

    val uri: String
        get() = "https://kfoods.com.br$path"

}
