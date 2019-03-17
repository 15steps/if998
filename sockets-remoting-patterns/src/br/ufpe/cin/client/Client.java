package br.ufpe.cin.client;

/**
 * Cliente apenas chama os metodos do proxy
 * como se fosse uma chamada local
 */
public class Client {
    public static void main(String[] args) {
        CalculatorProxy calculator = new CalculatorProxy();
        var result = calculator.mul(-4, 9);
        System.out.println(String.format(">> %.2f", result));
    }
}
