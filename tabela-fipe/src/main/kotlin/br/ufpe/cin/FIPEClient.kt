package br.ufpe.cin

fun main(args: Array<String>) {
    val service = FIPEServerService().fipeServerPort
    println(service.brands())
    println(service.carsByBrand(1))
    println(service.carPrices(1))
}