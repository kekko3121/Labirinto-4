package com.labirinto;

public enum Hardships {
    EASY(1), MEDIUM(2), HARD(3);

    private final int value;

    private Hardships(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}