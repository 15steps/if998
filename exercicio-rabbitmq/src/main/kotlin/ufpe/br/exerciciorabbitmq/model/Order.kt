package ufpe.br.exerciciorabbitmq.model

import java.io.Serializable

data class Order(val customer: String, val restaurant: String?, val items: List<String> = listOf()) : Serializable