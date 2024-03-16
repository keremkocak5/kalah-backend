package com.kocak.kalah.util;

import com.kocak.kalah.model.enums.PlayerSide;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

public class Util {

    private Util() {
    }

    public static final PlayerSide getRandomPlayer() {
        Random random = new Random();
        return PlayerSide.values()[random.nextInt(2)];
    }

    public static boolean isKalah(short pitCount, short pit) {
        return pit % (pitCount + 1) == pitCount;
    }

}
