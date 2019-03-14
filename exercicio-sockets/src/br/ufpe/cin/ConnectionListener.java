package br.ufpe.cin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Aguarda novas mensagens e imprime no console
 */
public class ConnectionListener implements Runnable {
    private final Socket socket;

    public ConnectionListener(Socket socket) {
        this.socket = socket;
    }

    private void listen() {
        try (var in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()))) {
            String input;
            do {
                input = in.readLine();
                System.out.println("Nova mensagem: " + input);
            } while (input != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        listen();
    }
}
