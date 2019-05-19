package ufpe.br.exerciciorabbitmq.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer
import ufpe.br.exerciciorabbitmq.websocket.Mappings.BROKER_URI
import ufpe.br.exerciciorabbitmq.websocket.Mappings.ORDERS_SUBSCRIPTION

@Configuration
@EnableWebSocketMessageBroker
class WebsocketConfig : WebSocketMessageBrokerConfigurer {
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker(ORDERS_SUBSCRIPTION)
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint(BROKER_URI).withSockJS()
    }
}