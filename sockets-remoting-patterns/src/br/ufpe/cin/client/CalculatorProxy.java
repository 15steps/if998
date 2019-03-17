package br.ufpe.cin.client;

import br.ufpe.cin.common.Calculator;

import java.util.Arrays;

import static br.ufpe.cin.common.Operation.*;

/**
 * Proxy funciona como placeholder do objeto remoto
 * nesse caso, Calculator
 */
public class CalculatorProxy implements Calculator {
    private Requestor requestor;

    public CalculatorProxy() {
        this.requestor = new Requestor();
    }

    @Override
    public float sum(float a, float b) {
        return requestor.invoke(SUM.getValue(), Arrays.asList(a, b));
    }

    @Override
    public float sub(float a, float b) {
        return requestor.invoke(SUB.getValue(), Arrays.asList(a, b));
    }

    @Override
    public float mul(float a, float b) {
        return requestor.invoke(MUL.getValue(), Arrays.asList(a, b));
    }

    @Override
    public float div(float a, float b) {
        return requestor.invoke(DIV.getValue(), Arrays.asList(a, b));
    }
}
