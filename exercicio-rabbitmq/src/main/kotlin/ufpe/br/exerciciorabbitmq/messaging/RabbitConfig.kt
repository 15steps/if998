package ufpe.br.exerciciorabbitmq.messaging

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class RabbitConfig {
    companion object {
        const val exchangeName = "orders-exchange"
        const val queueName = "orders-queue"
    }

    val logger = LoggerFactory.getLogger(RabbitConfig.javaClass)

    @Bean
    fun queue() = Queue(queueName, true)

    @Bean
    fun exchange() = TopicExchange(exchangeName)

    @Bean
    fun binding(queue: Queue, topicExchange: TopicExchange) =
            BindingBuilder.bind(queue).to(topicExchange).with("order")

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        logger.warn("HOST = ${connectionFactory.host}")
        return RabbitTemplate(connectionFactory).apply {
            containerAckMode(AcknowledgeMode.MANUAL)
        }
    }
}