package br.dev.s2w.kfoods.api.adapter.exceptionhandler

import java.time.LocalDateTime

data class Problem(
    val timestamp: LocalDateTime,
    val message: String
)