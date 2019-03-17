package br.ufpe.cin.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Faz o marshalling/unmarshalling dos dados
 */
public class Invoker {
    private ServerRequestHandler requestHandler;

    public Invoker() throws IOException {
        this.requestHandler = new ServerRequestHandler();
    }

    public String invoke() throws IOException {
        var data = requestHandler.receive();
        return new String(data, 0, data.length, StandardCharsets.UTF_8).trim();
    }

    public void respond(String response) throws IOException {
        requestHandler.send(response.getBytes());
    }
}
