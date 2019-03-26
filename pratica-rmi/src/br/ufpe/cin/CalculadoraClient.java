package br.ufpe.cin;

import java.rmi.Naming;

public class CalculadoraClient {
    public static void main(String[] args) {
        try {
            ICalculadora calculadora = (ICalculadora) Naming.lookup(String.format("//localhost:%d/MyCalc", CalculadoraServer.PORT));
            int result = calculadora.div(2, 2);
            System.out.println("result = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
