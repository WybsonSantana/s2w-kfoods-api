package br.dev.s2w.kfoods.api.util

import org.springframework.util.ResourceUtils
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.StandardCharsets

open class GeneralUtils {

    protected fun getContentFromResource(resourceName: String): String {
        try {
            ResourceUtils::class.java
                .getResourceAsStream(resourceName).also { stream ->
                    return StreamUtils.copyToString(stream, StandardCharsets.UTF_8)
                }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

}
