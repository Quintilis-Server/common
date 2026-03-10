package org.quintilis.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.ShallowEtagHeaderFilter

@Configuration
class EtagConfig {
    @Bean
    fun etagFilter(): ShallowEtagHeaderFilter {
        return ShallowEtagHeaderFilter()
    }
}