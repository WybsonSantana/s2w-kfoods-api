package br.dev.s2w.kfoods.api.jpa

import br.dev.s2w.kfoods.api.S2wKfoodsApiApplication
import br.dev.s2w.kfoods.api.domain.model.Permissao
import br.dev.s2w.kfoods.api.domain.repository.PermissaoRepository
import org.springframework.boot.WebApplicationType
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ApplicationContext

fun main(args: Array<String>) {
    val applicationContext: ApplicationContext = SpringApplicationBuilder(S2wKfoodsApiApplication::class.java)
        .web(WebApplicationType.NONE)
        .run(*args)

    val permissaoRepository: PermissaoRepository = applicationContext.getBean(PermissaoRepository::class.java)

    val permissoes: List<Permissao> = permissaoRepository.listar()

    for (permissao in permissoes) {
        println("${permissao.nome} - ${permissao.descricao}")
    }


}