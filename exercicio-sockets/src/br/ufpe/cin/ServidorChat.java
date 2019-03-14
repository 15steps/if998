package br.ufpe.cin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServidorChat {

    public static void main(String[] args) throws IOException {
        Socket echoSocket = null;
        ServerSocket server = null;
        try {
            server = new ServerSocket(9091);
            do {
                System.out.println("Aguardando nova conexao");
                echoSocket = server.accept();
                System.out.println("Conexao com cliente iniciada");
                new Thread(new ConnectionWriter(echoSocket)).start();
                new Thread(new ConnectionListener(echoSocket)).start();
            } while(echoSocket != null);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        echoSocket.close();
        server.close();
    }
}