package br.ufpe.cin.client;

import br.ufpe.cin.server.ChatServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import static br.ufpe.cin.server.ChatServerApp.CHAT_URI;

public class ChatClientApp {
    public static void main(String[] args) {
        try {
            String username = "Darryl Filbin";
            ChatServer chatServer = (ChatServer) Naming.lookup(CHAT_URI);
            System.out.println("Found a chat server. Entering chat room...");
            ChatClient chatClient = new ChatClientImpl();
            chatServer.enterChat(chatClient);

            Scanner scanner = new Scanner(System.in);
            String text;

            do {
                System.out.println();
                System.out.print(">> ");
                text = scanner.nextLine();
                chatServer.send(username, text);
            } while (!"EXIT".equals(text));

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
