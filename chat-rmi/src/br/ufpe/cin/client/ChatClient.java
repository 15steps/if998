package br.ufpe.cin.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(String user, String message) throws RemoteException;
}
