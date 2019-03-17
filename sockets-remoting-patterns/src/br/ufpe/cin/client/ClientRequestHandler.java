package br.ufpe.cin.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Responsavel apenas por abstrair os detalhes da comunicacao
 * nesse caso, do socket
 */
public class ClientRequestHandler {
    private static final String HOST = "localhost";
    private static final int PORT = 4300;

    public byte[] send(byte[] msg) throws IOException {
        var socket = new Socket(InetAddress.getByName(HOST), PORT);
        socket.getOutputStream().write(msg, 0, msg.length);
        socket.getOutputStream().flush();
        var buf = new byte[1024];
        socket.getInputStream().read(buf, 0, buf.length);
        return buf;
    }
}
