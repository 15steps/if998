package br.ufpe.cin.server;

import br.ufpe.cin.client.ChatClient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServer, Serializable {
    private List<ChatClient> clients = new ArrayList<>();


    protected ChatServerImpl() throws RemoteException {
    }

    @Override
    public synchronized void enterChat(ChatClient client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public synchronized void send(String user, String message) throws RemoteException {
        for (ChatClient client : clients) {
            client.receive(user, message);
        }
    }
}
