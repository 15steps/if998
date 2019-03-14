package br.ufpe.cin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteChat {
    public static void main(String[] args) {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int port = 9091;
        try {
            echoSocket = new Socket("localhost", port);
            new Thread(new ConnectionWriter(echoSocket)).start();
            new Thread(new ConnectionListener(echoSocket)).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
