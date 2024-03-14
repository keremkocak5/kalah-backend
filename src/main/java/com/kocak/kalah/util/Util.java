package com.kocak.kalah.util;

import com.kocak.kalah.enums.PlayerSide;

import java.util.Arrays;

public class Util {

    private Util() {
    }

    public static final PlayerSide pickRandomPlayer() {
        return PlayerSide.RED;// Arrays.stream(PlayerSide.values()).toList().get((int) Math.random() * (2 - 1)); // kerem calismiyor
    }

    public static boolean isKalah(short pitCount, short pit) {
        return pit % (pitCount+1) == pitCount;
    }

    public static final int initialPitTokenCount = 4;


}
