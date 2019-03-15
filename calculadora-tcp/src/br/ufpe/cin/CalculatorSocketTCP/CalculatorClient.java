package br.ufpe.cin.CalculatorSocketTCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CalculatorClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket clientSocket = new Socket("localhost", 9090);
        var outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        var inFromServer = new ObjectInputStream(clientSocket.getInputStream());

        outToServer.writeObject("sub(1,3)");
        outToServer.flush();

        var msg = (String) inFromServer.readObject();
        System.out.println(">>> " + msg);

        clientSocket.close();
        outToServer.close();
        inFromServer.close();
    }
}
