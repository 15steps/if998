package br.ufpe.cin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConnectionListener {
    private Socket socket;

    public ConnectionListener(Socket socket) {
        this.socket = socket;
    }

    public void listen() throws IOException {
        var in = new BufferedReader(new InputStreamReader(
                this.socket.getInputStream()));
        String input = null;
        do {
            input = in.readLine();
            System.out.println("Nova mensagem: " + input);
        } while (input != null);
    }
}
