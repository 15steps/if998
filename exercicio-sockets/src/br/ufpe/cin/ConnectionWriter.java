package br.ufpe.cin;

import java.net.Socket;

public class ConnectionWriter {
    private Socket socket;
    public ConnectionWriter(Socket socket) {
        this.socket = socket;
    }
}
