package org.quintilis.common.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FaviconController {

    @GetMapping("favicon.ico")
    fun favicon(): String {
        // O Pulo do Gato:
        // Quando o navegador pede o .ico, nós entregamos o .svg
        // Isso mantém a qualidade vetorial (infinita) em navegadores modernos.
        return "forward:/favicon.svg"
    }
}
