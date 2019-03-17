package br.ufpe.cin.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Requestor faz o marshalling da operacao escolhida
 * pelo cliente no proxy e passa para o ClientRequestHandler
 */
public class Requestor {
    private ClientRequestHandler requestHandler;
    private final String operationFormat = "%s(%f, %f)";

    public Requestor() {
        this.requestHandler = new ClientRequestHandler();
    }

    public float invoke(String methodName, List<Float> arguments) {
        try {
            String operation = String.format(operationFormat, methodName, arguments.get(0), arguments.get(1));
            byte[] buf = requestHandler.send(operation.getBytes());
            String response = new String(buf, 0, buf.length, StandardCharsets.UTF_8).trim();
            return Float.parseFloat(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Float.NaN;
    }
}
