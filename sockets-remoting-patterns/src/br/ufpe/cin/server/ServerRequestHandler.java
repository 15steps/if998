package br.ufpe.cin.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Responsavel apenas por receber e enviar mensagens
 * via sockets
 */
public class ServerRequestHandler {
    private static final int PORT = 4300;
    private Socket socket;

    public ServerRequestHandler() throws IOException {
        var sSocket = new ServerSocket(PORT);
        this.socket = sSocket.accept();
    }

    public byte[] receive() throws IOException {
        var buf = new byte[1024];
        socket.getInputStream().read(buf);
        return buf;
    }

    public void send(byte[] data) throws IOException {
        socket.getOutputStream().write(data, 0, data.length);
        socket.getOutputStream().flush();
    }
}
