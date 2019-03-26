package br.ufpe.cin;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadora extends Remote {
    public int add(int v1, int v2) throws RemoteException;
    public int sub(int v1, int v2) throws RemoteException;
    public int mul(int v1, int v2) throws RemoteException;
    public int div(int v1, int v2) throws RemoteException;
}
