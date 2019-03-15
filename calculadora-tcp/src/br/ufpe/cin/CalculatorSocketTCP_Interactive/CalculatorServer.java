package br.ufpe.cin.CalculatorSocketTCP_Interactive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Socket initialization
        var sSocket = new ServerSocket(9090);
        var connectionSocket = sSocket.accept();

        var outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
        var inFromClient = new ObjectInputStream(connectionSocket.getInputStream());

        // Receiving client request
        var clientMessage = (String) inFromClient.readObject();

        // Processing request
        ICalculator calculator = new CalculatorImpl();
        String operation = clientMessage.substring(0,3);
        String[] operands = clientMessage.substring(
                clientMessage.indexOf("(") + 1,
                clientMessage.indexOf(")")).split(",");
        float p1 = Float.parseFloat(operands[0]);
        float p2 = Float.parseFloat(operands[1]);
        float res = 0;

        if ("sub".equals(operation)) {
            res = calculator.sub(p1, p2);
        } else if ("sum".equals(operation)) {
            res = calculator.sum(p1, p2);
        } else if ("div".equals(operation)) {
            res = calculator.div(p1, p2);
        } else if ("mul".equals(operation)) {
            res = calculator.mul(p1, p2);
        }

        // Sending back result
        outToClient.writeObject(Float.toString(res));
        outToClient.flush();

        connectionSocket.close();
        sSocket.close();
        outToClient.close();
        inFromClient.close();
    }
}
