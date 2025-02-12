package br.dev.s2w.kfoods.api.core.jackson

import br.dev.s2w.kfoods.api.adapter.model.mixin.CityMixin
import br.dev.s2w.kfoods.api.adapter.model.mixin.CuisineMixin
import br.dev.s2w.kfoods.api.adapter.model.mixin.RestaurantMixin
import br.dev.s2w.kfoods.api.domain.model.City
import br.dev.s2w.kfoods.api.domain.model.Cuisine
import br.dev.s2w.kfoods.api.domain.model.Restaurant
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.stereotype.Component

@Component
class JacksonMixinModule : SimpleModule() {

    init {
        setMixInAnnotation(City::class.java, CityMixin::class.java)
        setMixInAnnotation(Cuisine::class.java, CuisineMixin::class.java)
        setMixInAnnotation(Restaurant::class.java, RestaurantMixin::class.java)
    }

}
