package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;

import java.util.function.Function;

public interface GamePlayRule {

    Game apply(Game game, short pit);
}
