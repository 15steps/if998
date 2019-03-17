package br.ufpe.cin.server;

import br.ufpe.cin.common.Calculator;

public class CalculatorImpl implements Calculator {
    @Override
    public float sum(float a, float b) {
        return a + b;
    }

    @Override
    public float sub(float a, float b) {
        return a - b;
    }

    @Override
    public float mul(float a, float b) {
        return a * b;
    }

    @Override
    public float div(float a, float b) {
        return a / b;
    }
}
