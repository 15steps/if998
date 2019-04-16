package br.ufpe.cin.client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientImpl extends UnicastRemoteObject implements ChatClient, Serializable {
    protected ChatClientImpl() throws RemoteException {
    }

    @Override
    public void receive(String user, String message) throws RemoteException {
        System.out.println(String.format("%s: \n\t%s\n", user, message));
    }
}
