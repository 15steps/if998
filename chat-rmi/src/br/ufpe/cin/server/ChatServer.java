package br.ufpe.cin.server;

import br.ufpe.cin.client.ChatClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
    void enterChat(ChatClient client) throws RemoteException;
    void send(String user, String message) throws RemoteException;
}
