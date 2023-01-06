package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.FormaPagamento
import br.dev.s2w.kfoods.api.domain.repository.FormaPagamentoRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val formaPagamentoRepository: FormaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository::class.java)

    val formaPagamentos: List<FormaPagamento> = formaPagamentoRepository.listar()

    for (formaPagamento in formaPagamentos) {
        println(formaPagamento.descricao)
    }


}