package br.ufpe.cin.exercicios

import br.ufpe.cin.shared.buildStatement
import br.ufpe.cin.shared.runWorker
import kotlin.random.Random.Default.nextInt

data class VehicleEvent(
    val licensePlate: String,
    val owner: String,
    val monthsInDebt: String)

data class SpeedEvent(
    val licensePlate: String,
    val speed: Int,
    val radarNumber: Int)

fun main(args: Array<String>) {
    val stmt1 = buildStatement<SpeedEvent>(
        "average-speed",
        """
        select se.licensePlate as plate, avg(se.speed) as speed
        from pattern [ every se=SpeedEvent ]
        group by se.licensePlate
        """
    )

    val stmt2 = buildStatement<SpeedEvent>(
        "average-speed-radar",
        """
        select se.radarNumber as radar, avg(se.speed) as speed
        from pattern [ every se=SpeedEvent ]
        group by se.radarNumber
        """
    )

    stmt1.addListener { newEvents, _, _, _ ->
//        println("Average speed by vehicle")
        newEvents.forEach {
            val plate = it.get("plate")
            val speed = it.get("speed")
            println("Plate: $plate Avg_speed: $speed")
        }
    }

    stmt2.addListener { newEvents, _, _, _ ->
//        println("Average speed by radar")
        newEvents.forEach {
            val radar = it.get("radar")
            val speed = it.get("speed")
            println("Radar: $radar Avg_speed: $speed")
        }
    }

    var radarNumber = 1
    runWorker<SpeedEvent>(interval = 2000) { runtime ->
        val event1 = SpeedEvent(
            "ABC-1234",
            nextInt(20, 130),
            radarNumber
        )
        val event2 = SpeedEvent(
            "DEF-9999",
            nextInt(20, 130),
            radarNumber
        )
        val event3 = SpeedEvent(
            "KKK-4321",
            nextInt(20, 130),
            radarNumber
        )
        radarNumber = if (radarNumber == 10) 1 else radarNumber + 1
        runtime.eventService.sendEventBean(event1, "SpeedEvent")
        runtime.eventService.sendEventBean(event2, "SpeedEvent")
        runtime.eventService.sendEventBean(event3, "SpeedEvent")
    }
}