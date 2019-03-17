package br.ufpe.cin.server;

import br.ufpe.cin.common.Calculator;
import br.ufpe.cin.common.Operation;

import java.io.IOException;

/**
 * O servidor, por fim, executa a operacao do cliente
 */
public class Server {
    public static void main(String[] args) throws IOException {
        Calculator calculator = new CalculatorImpl();
        System.out.println("Server is waiting for operation...");
        var invoker = new Invoker();
        System.out.println("Client connected...");
        var operation = invoker.invoke();
        var op = Operation.fromString(operation.substring(0, 3));
        var vals = operation.substring(operation.indexOf("(") + 1, operation.indexOf(")")).split(",");
        var p1 = Float.parseFloat(vals[0]);
        var p2 = Float.parseFloat(vals[1]);
        var result = 0f;

        switch (op) {
            case SUM:
                result = calculator.sum(p1, p2);
                break;
            case SUB:
                result = calculator.sub(p1, p2);
                break;
            case MUL:
                result = calculator.mul(p1, p2);
                break;
            case DIV:
                result = calculator.div(p1, p2);
                break;
        }
        System.out.println("Sending back result...");
        invoker.respond("" + result);
    }
}
