package br.dev.s2w.kfoods.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class MyFirstController {

    @GetMapping("/hello")
    @ResponseBody
    fun hello() = "Hello, KFoods!"
}