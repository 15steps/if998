package br.ufpe.cin;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Calculadora {
    @WebMethod(exclude = true)
    public int adicionar(int a, int b) {
        return (a + b);
    }
    public int subtrair(int a, int b) {
        return (a - b);
    }
    public int dividir(int a, int b) {
        return (a / b);
    }
    public int multiplicar(int a, int b) {
        return (a * b);
    }
}
