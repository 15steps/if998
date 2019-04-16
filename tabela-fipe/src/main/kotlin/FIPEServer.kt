import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import java.net.URL

data class Brand(
    val name: String,
    @Json(name = "fipe_name") val fipeName: String,
    val order: Int,
    val key: String,
    val id: Long
)

data class Car(
    val marca: String,
    val name: String,
    val id: Long,
    val key: String,
    @Json(name = "ano_modelo") var anoModelo: String,
    var veiculo: String,
    var preco: String,
    var combustivel: String,
    var referencia: String,
    @Json(name = "fipe_codigo") var fipeCodigo: String
)

class FIPEServer {
    companion object {
        val API_URL = "http://fipeapi.appspot.com/api/1"
        var klaxon = Klaxon()
    }

    // GET: http://fipeapi.appspot.com/api/1/carros/marcas.json
    open fun brands(): List<Brand>? {
        val content = URL("$API_URL/carros/marcas.json").readText()
        return klaxon.parseArray(content)
    }

    // GET: http://fipeapi.appspot.com/api/1/carros/veiculos/21.json
    open fun carsByBrand(idMarca: Int): List<Car>? {
        val content = URL("$API_URL/carros/veiculos/$idMarca.json").readText()
        return klaxon.parseArray(content)
    }
}

fun main() {
    val res = FIPEServer().carsByBrand(21)
    println(res)
}