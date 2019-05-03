
@file:JvmName("Exercicio1")

package br.ufpe.cin.exercicios

import br.ufpe.cin.shared.buildStatement
import br.ufpe.cin.shared.runWorker
import jdk.nashorn.internal.runtime.regexp.joni.Config.log


data class TemperatureEvent(val value: Double)

fun main() {
    val stmt1 = buildStatement<TemperatureEvent>(
        "average-100-last",
        "select avg(value) as average from TemperatureEvent#length(100)"
    )

    val stmt2 = buildStatement<TemperatureEvent>(
        "events-3-minutes",
        "select count(*) as count from TemperatureEvent#time(3 min)"
    )

    val stmt3 = buildStatement<TemperatureEvent>(
        "temp-above-25",
        "select value from TemperatureEvent where value > 25.0"
    )

    stmt1.addListener { newEvents, _, _, _ ->
        val eventValue = newEvents[0].get("average")
        println("Average of the last 100 events is $eventValue")
    }

    stmt2.addListener { newEvents, _, _, _ ->
        val count = newEvents[0].get("count")
        println("Events fired in the past 3 minutes: $count")
    }

    stmt3.addListener { newEvents, _, _, _ ->
        val currentTemp = newEvents[0].get("value")
        println("Temperature is above 25! Current value is $currentTemp")
    }

    runWorker<TemperatureEvent> { runtime ->
        val temp = Math.random() * 45
        val event = TemperatureEvent(temp)
        runtime.eventService.sendEventBean(event, "TemperatureEvent")
    }
}