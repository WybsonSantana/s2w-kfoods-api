package br.dev.s2w.kfoods.api.core.jackson

import br.dev.s2w.kfoods.api.adapter.model.mixin.RestaurantMixin
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.stereotype.Component

@Component
class JacksonMixinModule : SimpleModule() {

    init {
        setMixInAnnotation(Restaurant::class.java, RestaurantMixin::class.java)
    }

}
