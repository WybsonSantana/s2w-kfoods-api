package br.dev.s2w.kfoods.api.controller

import br.dev.s2w.kfoods.api.di.model.Customer
import br.dev.s2w.kfoods.api.di.service.CustomerActivationService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MyFirstController(
    private val customerActivationService: CustomerActivationService
) {

    init {
        println("MyFirstController: $customerActivationService")
    }

    @GetMapping("/hello")
    @ResponseBody
    fun hello(): String {
        val john = Customer("John due", "john.due@s2w.dev.br", "+55 321 555-6677")

        customerActivationService.activate(john)

        return "Hello, KFoods!"
    }
}