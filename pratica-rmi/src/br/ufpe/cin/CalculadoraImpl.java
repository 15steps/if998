package br.ufpe.cin;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraImpl extends UnicastRemoteObject implements ICalculadora, Serializable {

    protected CalculadoraImpl() throws RemoteException {
    }

    @Override
    public int add(int v1, int v2) {
        return v1 + v2;
    }

    @Override
    public int sub(int v1, int v2) {
        return v1 - v2;
    }

    @Override
    public int mul(int v1, int v2) {
        return v1 * v2;
    }

    @Override
    public int div(int v1, int v2) {
        return v1 / v2;
    }
}
