package com.kocak.kalah.util;

import com.kocak.kalah.model.enums.PlayerSide;

import java.util.Random;

public class Util {

    private Util() {
    }

    public static final PlayerSide getRandomPlayer() {
        Random random = new Random();
        return PlayerSide.values()[random.nextInt(2)];
    }

    // gelen talebi valide et: oyun var mi?
    // oyun varsa, talep edilen pit numarasi kisiye ozel mi? kisiye ozelse, dolu mu? oyun statusu nedir
    // oyna.
    // kontrolleri yap, ucluyu calistir
    // oyuncu degismesi gerekiyorsa degistir
    // oyun bitti mi diye kontrol et. kazanma varsa kazanma operasyonu
    // board geri dondur
    // id uuid olsun mu
    // foreing key falan

    // mysql kaldir
    // secur'ty bak
// onyuz isimler iki comp olsun
    // onyuz test
    // db index ekle

    // problem details
    // docker
    // test container
}
