package br.ufpe.cin;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Aguarda entrada do usuario e envia mensagem
 */
public class ConnectionWriter implements Runnable {
    private final Socket socket;

    public ConnectionWriter(Socket socket) {
        this.socket = socket;
    }

    private void write() {
        try (var out = new PrintWriter(this.socket.getOutputStream(), true)) {
            var scanner = new Scanner(System.in);
            String input;

            do {
                input = scanner.nextLine();
                out.println(input);
                out.flush();
            } while (!"".equals(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        write();
    }
}
