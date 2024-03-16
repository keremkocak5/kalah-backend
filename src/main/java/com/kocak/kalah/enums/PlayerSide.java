package com.kocak.kalah.enums;

public enum PlayerSide {
    BLUE, RED;

    public PlayerSide nextSide() {
        if (this.equals(RED)) {
            return BLUE;
        }
        return RED;
    }

}
