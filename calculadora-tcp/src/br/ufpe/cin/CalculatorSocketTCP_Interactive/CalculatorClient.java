package br.ufpe.cin.CalculatorSocketTCP_Interactive;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class CalculatorClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Socket clientSocket = new Socket("localhost", 9090);
        var outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        var inFromServer = new ObjectInputStream(clientSocket.getInputStream());

        var scanner = new Scanner(System.in);

        var opt = "";
        var operation = "";
        var done = false;
        do {
            clearConsole();
            System.out.println("Qual operacao que deseja realizar?");
            System.out.println("1 - sub");
            System.out.println("2 - sum");
            System.out.println("3 - mul");
            System.out.println("4 - div");
            System.out.println("0 - sair");
            System.out.print("\n\n~> ");

            opt = scanner.nextLine();
            done = true;
            switch (opt) {
                case "0":
                    break;
                case "1":
                    operation = "sub(%.3f, %.3f)";
                    break;
                case "2":
                    operation = "sum(%.3f, %.3f)";
                    break;
                case "3":
                    operation = "mul(%.3f, %.3f)";
                    break;
                case "4":
                    operation = "div(%.3f, %.3f)";
                    break;
                default:
                    done = false;
                    System.out.println("Opcao invalida!");
                    Thread.sleep(200);
                    clearConsole();
            }
        } while (!done);

        if ("0".equals(opt)) {
            return;
        }

        clearConsole();
        System.out.print("Digite o valor do primeiro operando: ");
        var p1 = scanner.nextFloat();
        System.out.print("Digite o valor do segundo operando: ");
        var p2 = scanner.nextFloat();

        outToServer.writeObject(String.format(operation, p1, p2));
        outToServer.flush();

        var msg = (String) inFromServer.readObject();
        System.out.println("\n>>> " + msg);

        clientSocket.close();
        outToServer.close();
        inFromServer.close();
    }

    public static void clearConsole() {
        System.out.println(new String(new char[100]).replace("\0", "\n"));
    }
}
