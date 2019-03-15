package br.ufpe.cin.CalculatorSocketUDP_Interactive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class CalculatorServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Socket initialization
        var datagramSocket = new DatagramSocket(3333);
        var buf = new byte[1024];
        var packet = new DatagramPacket(buf, buf.length);
        datagramSocket.receive(packet);

        var clientMessage = new String(packet.getData(), 0, packet.getLength());
        System.out.println("clientMessage = " + clientMessage);

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

        buf = Float.toString(res).getBytes();
        packet = new DatagramPacket(buf, buf.length, packet.getAddress(), packet.getPort());
        datagramSocket.send(packet);

        datagramSocket.close();
    }
}
