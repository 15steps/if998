package br.ufpe.cin.exercicio1

import com.espertech.esper.common.client.EPCompiled
import com.espertech.esper.common.client.configuration.Configuration
import com.espertech.esper.compiler.client.CompilerArguments
import com.espertech.esper.compiler.client.EPCompileException
import com.espertech.esper.compiler.client.EPCompilerProvider
import com.espertech.esper.runtime.client.EPDeployException
import com.espertech.esper.runtime.client.EPDeployment
import com.espertech.esper.runtime.client.EPRuntimeProvider


data class TemperatureEvent(val value: Double)

fun main() {
    val compiler = EPCompilerProvider.getCompiler()

    val configuration = Configuration()
    configuration.common.addEventType(TemperatureEvent::class.java)

    val cargs = CompilerArguments(configuration)
    val epCompiled: EPCompiled

    try {
        epCompiled = compiler.compile(
            "@name('temp-statement') select * from TemperatureEvent where value > 25",
            cargs
        )
    } catch (ex: EPCompileException) {
        throw RuntimeException(ex)
    }

    val runtime = EPRuntimeProvider.getDefaultRuntime(configuration)
    val deployment: EPDeployment

    try {
        deployment = runtime.deploymentService.deploy(epCompiled)
    } catch (ex: EPDeployException) {
        throw RuntimeException(ex)
    }

    val stmt = runtime.deploymentService.getStatement(deployment.deploymentId, "temp-statement")

    stmt.addListener { newEvents, oldEvents, statement, runtime ->
        val eventValue = newEvents[0].get("value")
        println("Temperature is above 25. Current value is $eventValue")
    }

    Thread {
        while (true) {
            val temp = Math.random() * 45
            val event = TemperatureEvent(temp)
            println("Publishing $event")
            runtime.apply {
                eventService.sendEventBean(event, "TemperatureEvent")
            }
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

        }
    }.start()
}