package com.kocak.kalah.util;

import com.kocak.kalah.model.enums.PlayerSide;
import org.apache.commons.lang3.StringUtils;

import java.util.Random;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Util {

    private Util() {
    }

    private static final Random randomInstance = new Random();

    public static final PlayerSide getRandomPlayer() {
        return PlayerSide.values()[randomInstance.nextInt(2)];
    }

    public static final Predicate<String> IS_STRING_NOT_EMPTY = s -> StringUtils.isNotEmpty(s);

    public static final Predicate<String> IS_STRING_MAX_50_CHARS = s -> s.length() <= 50;

    public static final Predicate<String> IS_STRING_HAS_NO_SPECIAL_CHARS = s -> Pattern.matches("^[a-zA-Z0-9 ]*$", s);

    public static final Predicate<Integer> IS_INTEGER_BETWEEN_1_AND_10 = i -> i >= 1 && i <= 10;


    // gelen talebi valide et: oyun var mi?
    // oyun varsa, talep edilen pit numarasi kisiye ozel mi? kisiye ozelse, dolu mu? oyun statusu nedir
    // oyna.
    // kontrolleri yap, ucluyu calistir
    // oyuncu degismesi gerekiyorsa degistir
    // oyun bitti mi diye kontrol et. kazanma varsa kazanma operasyonu
    // board geri dondur
    // id uuid olsun mu
    // foreing key falan
    // kerem onyuze regex val ekle

    // mysql kaldir
    // secur'ty bak
// onyuz isimler iki comp olsun
    // onyuz test
    // db index ekle

    // problem details
    // docker
    // test container
}
