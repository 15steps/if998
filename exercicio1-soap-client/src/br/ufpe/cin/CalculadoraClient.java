package br.ufpe.cin;

public class CalculadoraClient {
    public static void main(String[] args) {
        CalculadoraService calculadoraService = new CalculadoraService();
        Calculadora calc = calculadoraService.getCalculadoraPort();
        int result = calc.subtrair(42, 7);
        System.out.println("result = " + result);
    }
}
