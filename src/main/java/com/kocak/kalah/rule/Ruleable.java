package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;

public interface Ruleable {

    Ruleable applyRule(Game game, int pit);

}
