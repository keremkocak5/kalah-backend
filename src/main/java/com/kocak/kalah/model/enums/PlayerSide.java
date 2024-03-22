package com.kocak.kalah.model.enums;

public enum PlayerSide {

    BLUE,
    RED;

    public PlayerSide getNextSide() {
        return this.equals(RED) ? BLUE : RED;
    }

}
