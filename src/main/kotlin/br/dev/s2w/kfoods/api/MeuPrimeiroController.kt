package br.dev.s2w.kfoods.api

import br.dev.s2w.kfoods.api.di.modelo.Cliente
import br.dev.s2w.kfoods.api.di.service.AtivacaoClienteService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MeuPrimeiroController(ativacaoClienteService: AtivacaoClienteService) {

    private val ativacaoClienteService: AtivacaoClienteService

    init {
        this.ativacaoClienteService = ativacaoClienteService
        println("MeuPrimeiroController: $ativacaoClienteService")
    }

    @GetMapping("/hello")
    @ResponseBody
    fun hello(): String {
        val fulano = Cliente("Fulano de Tal", "fulanodetal@s2w.dev.br", "+55 99 98888-7777")

        ativacaoClienteService.ativar(fulano)

        return "Hello, world!"
    }
}