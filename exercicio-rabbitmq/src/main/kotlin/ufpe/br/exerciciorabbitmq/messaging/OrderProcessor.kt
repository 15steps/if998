package ufpe.br.exerciciorabbitmq.messaging

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import ufpe.br.exerciciorabbitmq.model.Order
import ufpe.br.exerciciorabbitmq.websocket.Mappings.ORDERS_SUBSCRIPTION

@Component
class OrderProcessor(val template: SimpMessagingTemplate) {
    fun process(order: Order) {
        template.convertAndSend(ORDERS_SUBSCRIPTION, order)
    }
}