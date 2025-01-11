package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Problem(
    val status: Int? = null,
    val type: String? = null,
    val title: String? = null,
    val detail: String? = null
)