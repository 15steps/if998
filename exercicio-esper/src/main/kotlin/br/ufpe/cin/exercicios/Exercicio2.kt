@file:JvmName("Exercicio2")

package br.ufpe.cin.exercicios

import br.ufpe.cin.shared.buildStatement
import br.ufpe.cin.shared.runWorker
import kotlin.random.Random.Default.nextBoolean
import kotlin.random.Random.Default.nextInt

data class CatracaEvent(val badgeId: String, val owner: String, val arriving: Boolean)

fun main(args: Array<String>) {
    val stmt1 = buildStatement<CatracaEvent>(
        "arrivals-last-hour",
        "select count(*) as count from CatracaEvent#time(60 min) where arriving = true"
    )

    val stmt2 = buildStatement<CatracaEvent>(
        "exits-last-hour",
        "select count(*) as count from CatracaEvent#time(60 min) where arriving = false"
    )

    val stmt3 = buildStatement<CatracaEvent>(
        "repeated-attempts",
        """
            select a[0] as event
            from pattern [ every [2] a=CatracaEvent() -> b=CatracaEvent(badgeId=a[0].badgeId, arriving=a[0].arriving) ]
            """
    )

    stmt1.addListener { newEvents, _, _, _ ->
        val count = newEvents[0].get("count")
        println("$count people arrived in the last hour")
    }

    stmt2.addListener { newEvents, _, _, _ ->
        val count = newEvents[0].get("count")
        println("$count people left in the last hour")
    }

    stmt3.addListener { newEvents, _, _, _ ->
        val event = newEvents[0].get("event")
        println("Repeated attemp detected by $event")
    }

    runWorker<CatracaEvent> { runtime ->
        val id = nextInt(1, 11)
        val event = CatracaEvent("$id", "person_$id", nextBoolean())
        runtime.eventService.sendEventBean(event, "CatracaEvent")
//        println("Published $event")
    }
}