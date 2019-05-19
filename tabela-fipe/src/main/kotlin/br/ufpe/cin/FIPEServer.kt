package br.ufpe.cin

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import java.net.URL
import javax.jws.WebMethod
import javax.jws.WebService
import javax.xml.ws.Endpoint

data class Brand(
    var name: String = "",
    @Json(name = "fipe_name") var fipeName: String = "",
    val order: Int = 0,
    var key: String = "",
    var id: Long = 0
)

data class Car(
    var marca: String = "",
    var name: String = "",
    var id: Long = 0,
    var key: String = "",
    @Json(name = "ano_modelo") var anoModelo: String = "",
    var veiculo: String = "",
    var preco: String = "",
    var combustivel: String = "",
    var referencia: String = "",
    @Json(name = "fipe_codigo") var fipeCodigo: String = ""
)

@WebService
class FIPEServer {
    companion object {
        val API_URL = "http://fipeapi.appspot.com/api/1"
        var klaxon = Klaxon()
    }

    // GET: http://fipeapi.appspot.com/api/1/carros/marcas.json
    @WebMethod
    open fun brands(): List<Brand>? {
        val content = URL("$API_URL/carros/marcas.json").readText()
        return klaxon.parseArray(content)
    }

    // GET: http://fipeapi.appspot.com/api/1/carros/veiculos/21.json
    @WebMethod
    open fun carsByBrand(idMarca: Int): List<Car>? {
        val content = URL("$API_URL/carros/veiculos/$idMarca.json").readText()
        return klaxon.parseArray(content)
    }

    @WebMethod
    open fun carPrices(idMarca: Int): List<String>? {
        return carsByBrand(idMarca)
            ?.map {
                it.preco
            }
    }
}

fun main(args: Array<String>) {
    Endpoint.publish("http://localhost:8080/", FIPEServer())
    println("Server started on port 8080")
}