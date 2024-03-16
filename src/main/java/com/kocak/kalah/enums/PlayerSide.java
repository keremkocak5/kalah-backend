package com.kocak.kalah.enums;

public enum PlayerSide {

    BLUE,
    RED;

    public PlayerSide nextSide() {
        return this.equals(RED) ? BLUE : RED;
    }

}
