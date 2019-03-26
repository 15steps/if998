package br.ufpe.cin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculadoraServer {
    static final int PORT = 1099;
    public static void main(String[] args) {
        try {
            ICalculadora calc = new CalculadoraImpl();
            System.out.println("Criando registro");
//            LocateRegistry.createRegistry(PORT);
            System.out.println("Obtendo registro");
            Registry registry = LocateRegistry.getRegistry(PORT);
            System.out.println("Servidor Criado");
            // Bind the remote object's stub in the registry
            registry.rebind("MyCalc", calc);
                    System.out.println("calculadora server ready.");
            System.out.println("Digite <ENTER> para encerrar");
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            // O buffered reader serve apenas para "parar" a execução
            b.read();
        } catch (Exception e) {
            System.out.println("calculadora server main " + e.getMessage());
        }
    }
}
