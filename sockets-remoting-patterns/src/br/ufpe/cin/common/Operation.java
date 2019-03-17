package br.ufpe.cin.common;

import java.util.Arrays;

public enum Operation {
    SUM("sum"), SUB("sub"), MUL("mul"), DIV("div");

    private String value;

    Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static Operation fromString(String value) {
        return Arrays.asList(values())
                .stream()
                .filter(op -> op.value.equals(value))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
