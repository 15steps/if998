package br.ufpe.cin;

import javax.xml.ws.Endpoint;

public class CalculadoraPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/MeuWS/Calculadora", new Calculadora());
    }
}
