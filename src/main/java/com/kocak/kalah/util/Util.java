package com.kocak.kalah.util;

import com.kocak.kalah.enums.PlayerSide;

import java.util.Arrays;

public class Util {

    private Util() {
    }

    public static final PlayerSide pickRandomPlayer() {
        return PlayerSide.B; //Arrays.stream(PlayerSide.values()).toList().get((int) Math.random() * (2 - 1)); kerem
    }

    public static boolean isKalah(short pitCount, short pit) {
        return pit % (pitCount + 1) == 0;
    }

    public static final int initialPitTokenCount = 4;


}
