package ufpe.br.exerciciorabbitmq.service

import com.rabbitmq.client.Channel
import org.springframework.amqp.core.AmqpAdmin
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter
import org.springframework.stereotype.Service
import java.util.function.Consumer

@Service
class RabbitAdminService(
        val rabbitTemplate: RabbitTemplate,
        val amqpAdmin: AmqpAdmin
) {
    companion object {
        const val UTF_8 = "UTF-8"
    }
    private val messagePropertiesConverter = DefaultMessagePropertiesConverter()

    fun <T> processQueue(queueName: String, consumer: Consumer<T>) {
        val messageCount = getMessageCount()!!
        println("Current queue length: $messageCount")
        for (i in 1..messageCount) {
            rabbitTemplate.execute {channel ->
                try {
                    val response = channel.basicGet(queueName, false)
                    val messageProps = messagePropertiesConverter
                            .toMessageProperties(response.props, response.envelope, UTF_8)
                    if (response.messageCount >= 0) {
                        messageProps.messageCount = response.messageCount
                    }
                    val message = Message(response.body, messageProps)
                    val result = rabbitTemplate.messageConverter.fromMessage(message) as T
                    consumer.accept(result)
                    republish(channel, message)
                    channel.basicNack(response.envelope.deliveryTag, false, false)
                    return@execute result
                } catch (e: Exception) {
                    println(e)
                    println("Something went wrong while getting messages from $queueName")
                }
            }
        }
    }

    private fun republish(channel: Channel, message: Message) {
        val exchange = message.messageProperties.receivedExchange
        val routingKey = message.messageProperties.receivedRoutingKey

        rabbitTemplate.send(exchange, routingKey, message)
    }

    private fun getMessageCount() =
            amqpAdmin.getQueueProperties("orders-queue")?.get(RabbitAdmin.QUEUE_MESSAGE_COUNT) as? Int
}