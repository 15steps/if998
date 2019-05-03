package br.ufpe.cin.shared

import com.espertech.esper.common.client.EPCompiled
import com.espertech.esper.common.client.configuration.Configuration
import com.espertech.esper.compiler.client.CompilerArguments
import com.espertech.esper.compiler.client.EPCompileException
import com.espertech.esper.compiler.client.EPCompilerProvider
import com.espertech.esper.runtime.client.*
import kotlin.concurrent.thread

inline fun <reified T> buildStatement(name: String, epl: String): EPStatement {
    val compiler = EPCompilerProvider.getCompiler()

    val configuration = Configuration()
    configuration.common.addEventType(T::class.java)

    val cargs = CompilerArguments(configuration)
    val epCompiled: EPCompiled

    try {
        epCompiled = compiler.compile(
            "@name('$name') $epl",
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

    return runtime.deploymentService.getStatement(deployment.deploymentId, name)
}

inline fun <reified T> runWorker(interval: Long = 1000, crossinline cb: (runtime: EPRuntime) -> Unit) {
    val configuration = Configuration()
    configuration.common.addEventType(T::class.java)
    val runtime = EPRuntimeProvider.getDefaultRuntime()
    thread(start = true) {
        while (true) {
            cb(runtime)
            Thread.sleep(interval)
        }
    }
}