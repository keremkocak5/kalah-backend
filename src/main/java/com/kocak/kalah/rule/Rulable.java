package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;

import java.util.Optional;

public interface Rulable {

    Optional<Rulable> applyRule(Game game, int pit);

}
