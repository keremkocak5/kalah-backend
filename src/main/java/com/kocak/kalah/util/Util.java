package com.kocak.kalah.util;

import com.kocak.kalah.enums.PlayerSide;

import java.time.Instant;

public class Util {

    private Util() {
    }

    public static final PlayerSide getRandomPlayer() {
        return PlayerSide.values()[Integer.valueOf(Instant.now().toString())%2];
    }

    public static boolean isKalah(short pitCount, short pit) {
        return pit % (pitCount+1) == pitCount;
    }

    public static final int INITIAL_PIT_TOKEN_COUNT = 4;


}
