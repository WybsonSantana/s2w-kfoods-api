package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Problem(
    val timestamp: LocalDateTime? = LocalDateTime.now(),
    val status: Int? = null,
    val type: String? = null,
    val title: String? = null,
    val detail: String? = null,
    val userMessage: String? = null,
    val fields: List<Field>? = null
) {

    data class Field(
        val name: String? = null,
        val userMessage: String? = null
    )
}