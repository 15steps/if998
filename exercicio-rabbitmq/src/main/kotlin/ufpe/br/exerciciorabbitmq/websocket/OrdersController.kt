package ufpe.br.exerciciorabbitmq.websocket

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller
import ufpe.br.exerciciorabbitmq.model.Order
import ufpe.br.exerciciorabbitmq.service.RabbitAdminService
import ufpe.br.exerciciorabbitmq.websocket.Mappings.LIST_ORDERS
import ufpe.br.exerciciorabbitmq.websocket.Mappings.ORDERS_SUBSCRIPTION
import ufpe.br.exerciciorabbitmq.websocket.Mappings.PROCESS_ORDER_URI
import ufpe.br.exerciciorabbitmq.websocket.Mappings.PLACE_ORDER_URI
import java.util.function.Consumer

@Controller
class OrdersController(
        val rabbitTemplate: RabbitTemplate,
        val rabbitAdminService: RabbitAdminService) {

    @MessageMapping(value = [PROCESS_ORDER_URI])
    @SendTo(value = [ORDERS_SUBSCRIPTION])
    fun processNextOrder(): List<Order> {
        println("Processing next order")
        rabbitTemplate.receiveAndConvert("orders-queue")
        return getAllOrdersInQueue("orders-queue")
    }

    @MessageMapping(value = [PLACE_ORDER_URI])
    @SendTo(value = [ORDERS_SUBSCRIPTION])
    fun placeOrder(order: Order): List<Order> {
        rabbitTemplate.convertAndSend("orders-exchange", "order", order)
        println("Received new order $order")
        return getAllOrdersInQueue("orders-queue")
    }

    @MessageMapping(value = [LIST_ORDERS])
    @SendTo(value = [ORDERS_SUBSCRIPTION])
    fun listOrders(): List<Order> {
        return getAllOrdersInQueue("orders-queue")
    }

    private fun getAllOrdersInQueue(queueName: String): List<Order> {
        val orders = arrayListOf<Order>()
        rabbitAdminService.processQueue<Order>("orders-queue", Consumer {
            orders.add(it)
        })
        return orders
    }
}