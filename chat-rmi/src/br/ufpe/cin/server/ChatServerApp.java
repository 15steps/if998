package br.ufpe.cin.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatServerApp {
    public static final String SERVER_NAME = "ChatServer";
    public static final String CHAT_URI = String.format("rmi://localhost/%s", SERVER_NAME);
    public static final int PORT = 1099;

    public static void main(String[] args) {
        try {
            ChatServer chatServer = new ChatServerImpl();
            LocateRegistry.createRegistry(PORT);
            Registry registry = LocateRegistry.getRegistry(PORT);
            registry.rebind(SERVER_NAME, chatServer);
            // Wait
            System.out.println("Registry ready. Waiting for clients.");
            new Scanner(System.in).nextLine();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
