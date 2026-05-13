package org.quintilis.common.storage

import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    companion object {
        const val FILE_PROCESS_QUEUE = "q.file-processing"
        const val FILE_PROCESS_EXCHANGE = "ex.file-processing"
    }

    @Bean
    fun fileProcessQueue() = Queue(FILE_PROCESS_QUEUE, true)

    @Bean
    fun fileProcessExchange() = TopicExchange(FILE_PROCESS_EXCHANGE)

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange) =
        BindingBuilder.bind(queue).to(exchange).with("file.process.#")
}