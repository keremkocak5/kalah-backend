package com.kocak.kalah.rule;

import com.kocak.kalah.model.entity.Game;

import java.util.Optional;

public interface Ruleable {

    Optional<Ruleable> applyRule(Game game, int pit);

}
