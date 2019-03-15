package br.ufpe.cin.CalculatorCentralized;

public class CalculatorClient {
    public static void main(String[] args) {
        ICalculator calculator = new CalculatorImpl();
        float result = calculator.sum(1, 1);
        System.out.println(result);
    }
}
